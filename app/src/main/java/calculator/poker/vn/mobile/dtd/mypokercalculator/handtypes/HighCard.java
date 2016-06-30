package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.Helpers;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class HighCard extends  HandType{
    public HighCard(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        List<Card> cards = fullyVisibleHand.getCards();
        Helpers.sortCardsByNumberInc(cards);
        List<Card> selectedCards = cards.subList(cards.size() - 5, cards.size());
        Assert.assertEquals(5,selectedCards.size());
        this.selectedHand = new FullySelectedHand(selectedCards);
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return true;
    }

    @Override
    protected int compareSameRank(HandType t) {
        Assert.assertTrue(t instanceof  HighCard);
        List<Card> oppCards = t.getSelectedHand().getCards();
        List<Card> myCards = this.getSelectedHand().getCards();
        Helpers.sortCardsByNumberInc(oppCards);
        Helpers.sortCardsByNumberInc(myCards);
        for(int i = 4 ; i >= 0 ; i--) {
            int cmp = myCards.get(i).compareTo(oppCards.get(i));
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }

    @Override
    public FullySelectedHand getSelectedHand() {
        return this.selectedHand;
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.HIGH_CARD;
    }
}
