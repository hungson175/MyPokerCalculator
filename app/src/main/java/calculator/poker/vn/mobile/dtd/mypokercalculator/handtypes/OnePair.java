package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.CardNumber;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.Helpers;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class OnePair extends HandType {


    private final CardNumber pairCardNumber;
    private List<Card> hiCards;

    public OnePair(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        this.pairCardNumber = findPairCardNumber();
        if ( pairCardNumber == null) return;
        this.selectedHand = findSelectedHand(pairCardNumber);
    }

    public List<Card> getHiCards() {
        return hiCards;
    }

    private FullySelectedHand findSelectedHand(CardNumber pairCardNumber) {
        List<Card> cards = new ArrayList<>();
        List<Card> restCards = new ArrayList<>();
        for (Card card : fullyVisibleHand.getCards()) {
            if ( card.getNumber() == pairCardNumber ) {
                cards.add(card);
            } else restCards.add(card);
        }
        Assert.assertEquals(2, cards.size());
        Helpers.sortCardsByNumberInc(restCards);
        int len = restCards.size();
        Assert.assertEquals(5, restCards.size());

        this.hiCards = new ArrayList<Card>();
        hiCards.add(restCards.get(len-1));
        hiCards.add(restCards.get(len-2));
        hiCards.add(restCards.get(len-3));

        cards.addAll(hiCards);
        return new FullySelectedHand(cards);
    }

    private CardNumber findPairCardNumber() {
        CardNumber pairCardNumber = null;
        for (Card cardI : fullyVisibleHand.getCards()) {
            int cnt = 0;
            for (Card cardJ : fullyVisibleHand.getCards()) {
                if (cardI.compareTo(cardJ) == 0)
                    cnt++;
            }
            Assert.assertTrue(cnt <= 2);
            if ( cnt == 2 ) {
                if ( pairCardNumber == null) {
                    pairCardNumber = cardI.getNumber();
                } else if ( pairCardNumber != cardI.getNumber() ) {
                    //2 pairs -> invalid pair ! return null
                    return null;
                }
            }

        }
        return pairCardNumber;
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        Assert.assertTrue(t instanceof OnePair);
        Assert.assertNotNull(this.getSelectedHand());

        OnePair myHand = (OnePair) this;
        OnePair oppHand = (OnePair) t;

        CardNumber myPairCardNumber = myHand.getPairCardNumber();
        CardNumber oppPairCardNumber = oppHand.getPairCardNumber();

        int cmpPairCard = myPairCardNumber.compareTo(oppPairCardNumber);
        if ( cmpPairCard != 0) return cmpPairCard;
        
        List<Card> myHiCards = myHand.getHiCards();
        List<Card> oppHiCards = oppHand.getHiCards();

        Helpers.sortCardsByNumberInc(myHiCards);
        Helpers.sortCardsByNumberInc(oppHiCards);

        Assert.assertEquals(3,myHiCards.size());
        Assert.assertEquals(3,oppHiCards.size());

        for(int i = 2 ; i >= 0 ; i--) {
            int cmp = myHiCards.get(i).compareTo(oppHiCards.get(i));
            if ( cmp != 0 ) return cmp;
        }
        return 0;
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.PAIR;
    }

    public CardNumber getPairCardNumber() {
        return pairCardNumber;
    }
}
