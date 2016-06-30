package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.CardNumber;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

/**
 * Created by hungson175 on 25/06/2016.
 */
public class FullHouse extends  HandType {
    private Card cardOfTriple = null;
    private Card cardOfPair = null;


    public FullHouse(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        //1.find the high triple card (may be more than 1,e.g: A8, AA88J)
        //2.find the pair card: != the found triple card
        this.cardOfTriple = findTripleCard();
        if ( cardOfTriple == null ) return;
        this.cardOfPair = findPairCard(cardOfTriple);
        if ( cardOfPair == null ) return;
        this.selectedHand = findSelectedHand(cardOfTriple,cardOfPair);
    }

    private FullySelectedHand findSelectedHand(Card cardOfTriple, Card cardOfPair) {
        List<Card> cards = new ArrayList<>();
        addCards(cardOfTriple, cards, 3);
        addCards(cardOfPair, cards, 2);
        Assert.assertEquals(5, cards.size());
        return new FullySelectedHand(cards);
    }

    private void addCards(Card cardOfTriple, List<Card> cards, int cnt) {
        for (Card card : fullyVisibleHand.getCards()) {
            if ( card.getNumber() == cardOfTriple.getNumber() && cnt > 0) {
                cards.add(card);
                cnt--;
            }
        }
        Assert.assertEquals(0,cnt);
    }

    private Card findPairCard(Card cardTrip) {
        Assert.assertNotNull(cardTrip);
        Card cardPair = null;
        for (Card cardI : fullyVisibleHand.getCards())
            if ( cardI.getNumber() != cardTrip.getNumber() ){
                int cnt = 0;
                for (Card cardJ : fullyVisibleHand.getCards())
                if ( cardJ.getNumber() == cardI.getNumber() ){
                    cnt++;
                }
                if ( cnt >= 2 ) {
                    if ( cardPair == null
                            || cardI.getNumber() == CardNumber._A
                            || cardI.getNumber().ordinal() > cardPair.getNumber().ordinal()) {
                        cardPair = cardI;
                    }
                }
            }
        return cardPair;
    }

    private Card findTripleCard() {
        Card cardTrip = null;
        for (Card cardI : fullyVisibleHand.getCards()) {
            int cnt = 0;
            for (Card cardJ : fullyVisibleHand.getCards()) {
                if ( cardJ.getNumber() == cardI.getNumber()) {
                    cnt++;
                }
            }
            if ( cnt >= 3 ) {
                if ( cardI.getNumber() == CardNumber._A) return cardI;
                if ( cardTrip == null || cardI.getNumber().ordinal() > cardTrip.getNumber().ordinal() ) {
                    cardTrip = cardI;
                }
            }
        }
        return cardTrip;
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return this.selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        FullHouse oppHT = (FullHouse) t;
        Assert.assertNotNull(this.selectedHand);
        Assert.assertNotNull(oppHT.selectedHand);
        CardNumber thisCN3 = this.getCardOfTriple().getNumber();
        CardNumber thisCN2 = this.getCardOfPair().getNumber();

        CardNumber oppCN3 = oppHT.getCardOfTriple().getNumber();
        CardNumber oppCN2 = oppHT.getCardOfPair().getNumber();

        if (thisCN3 == oppCN3) {
            if (thisCN2 == oppCN2) return 0;
            if (thisCN2 == CardNumber._A) return 1;
            if (oppCN2 == CardNumber._A) return -1;
            return thisCN2.ordinal() - oppCN2.ordinal();
        } else {
            if (thisCN3 == CardNumber._A) return 1;
            if (oppCN3 == CardNumber._A) return -1;
            return thisCN3.ordinal() - oppCN3.ordinal();
        }
    }

    @Override
    public HandRanking getRanking() {
        return  HandRanking.FULL_HOUSE;
    }

    public Card getCardOfTriple() {
        return cardOfTriple;
    }

    public Card getCardOfPair() {
        return cardOfPair;
    }
}
