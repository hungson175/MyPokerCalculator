package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.CardNumber;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class TwoPairs extends  HandType{
    private List<CardNumber> pairCardNumbers;
    private Card highCard;

    public TwoPairs(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);

        this.pairCardNumbers = find2HighPairsNumbers();
        if ( pairCardNumbers == null) return;

        this.highCard = findHighCard(pairCardNumbers);
        Assert.assertNotNull(this.highCard);

        this.selectedHand = findSelectedHand(pairCardNumbers, highCard);
    }

    private FullySelectedHand findSelectedHand(List<CardNumber> pairCardNumbers, Card highCard) {
        List<Card> cards = new ArrayList<>();
        Set<CardNumber> set = new HashSet<>(pairCardNumbers);
        for (Card card : fullyVisibleHand.getCards()) {
            if ( set.contains(card.getNumber())) {
                cards.add(card);
            }
        }
        cards.add(highCard);
        return new FullySelectedHand(cards);
    }

    private Card findHighCard(List<CardNumber> pairCardNumbers) {
        Assert.assertEquals(2, pairCardNumbers.size());
        Set<CardNumber> pairCardNumberSet = new HashSet<>(pairCardNumbers);
        Card hiCard = null;
        for (Card card : fullyVisibleHand.getCards()) {
            if ( ! pairCardNumberSet.contains(card.getNumber())) {
                if ( hiCard == null || hiCard.compareTo(card) < 0) {
                    hiCard = card;
                }
            }
        }
        return hiCard;
    }

    private List<CardNumber> find2HighPairsNumbers() {
        Set<CardNumber> setPairNumbers = new HashSet<>();
        for (Card pairCard : fullyVisibleHand.getCards()) {
            int cnt = 0;
            for (Card card : fullyVisibleHand.getCards()) {
                if (card.getNumber() == pairCard.getNumber()) cnt++;
            }
            Assert.assertTrue( cnt <= 2 );
            if ( cnt == 2 ) setPairNumbers.add(pairCard.getNumber());
        }
        if ( setPairNumbers.size() < 2) return null;
        ArrayList<CardNumber> tlist = new ArrayList<>(setPairNumbers);
        Collections.sort(tlist);
        List<CardNumber> list2HighNumbers = new ArrayList<>();
        int len = tlist.size();
        list2HighNumbers.add(tlist.get(len-2));
        list2HighNumbers.add(tlist.get(len-1));
        return list2HighNumbers;
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return this.selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        Assert.assertTrue(t instanceof TwoPairs);
        TwoPairs myHand = (TwoPairs) this;
        TwoPairs oppHand = (TwoPairs) t;

        List<CardNumber> myPairCardNumbers = myHand.getPairCardNumbers();
        List<CardNumber> oppPairCardNumbers = oppHand.getPairCardNumbers();

        Assert.assertEquals(2, myPairCardNumbers.size());
        Assert.assertEquals(2, oppPairCardNumbers.size());

        Collections.sort(myPairCardNumbers);
        Collections.sort(oppPairCardNumbers);
        for (int i = 1; i >= 0; i--) {
            int cmp = myPairCardNumbers.get(i).compareTo(oppPairCardNumbers.get(i));
            if (cmp != 0) return cmp;
        }
        return myHand.getHighCard().compareTo(oppHand.getHighCard());
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.TWO_PAIRS;
    }

    public List<CardNumber> getPairCardNumbers() {
        return pairCardNumbers;
    }

    public Card getHighCard() {
        return highCard;
    }
}
