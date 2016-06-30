package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.CardType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.Helpers;

/**
 * Created by hungson175 on 6/25/2016.
 */
public class Flush extends HandType {

    public Flush(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        //find at least 5 cards with the same type, return the 5 largest, pay attention to Ace
        for (CardType targetCardType : CardType.values()) {
            int cnt = 0;
            for (Card card : fullyVisibleHand.getCards())
            if ( card.getType() == targetCardType ){
                cnt++;
            }
            if ( cnt >= 5 ) {
                this.selectedHand = findSelectedHand(targetCardType);
                break;
            }
        }
    }

    private FullySelectedHand findSelectedHand(CardType targetCardType) {
        List<Card> cards = new ArrayList<>();
        for (Card card : fullyVisibleHand.getCards()) {
            if ( card.getType() == targetCardType) {
                cards.add(card);
            }
        }
        Assert.assertTrue(cards.size() >= 5);
        Helpers.sortCardsByNumberInc(cards);
        Assert.assertTrue(cards.get(0).getNumber().compareTo(cards.get(4).getNumber()) < 0);
        List<Card> bestCards = cards.subList(cards.size() - 5, cards.size());
        Assert.assertEquals(5, bestCards.size());
        return new FullySelectedHand(bestCards);
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return this.selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        //both are flush
        List<Card> myCards  = this.getSelectedHand().getCards();
        List<Card> oppCards = t.getSelectedHand().getCards();
        Helpers.sortCardsByNumberInc(myCards);
        Helpers.sortCardsByNumberInc(oppCards);
        for(int i = 4 ; i >= 0 ; i--) {
            int cmp = myCards.get(i).getNumber().compareTo(oppCards.get(i).getNumber());
            if ( cmp != 0)
                return cmp;
        }
        return 0;
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.FLUSH;
    }

}
