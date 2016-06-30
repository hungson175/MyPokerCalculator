package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import java.util.List;

/**
 * Created by hungson175 on 23/06/2016.
 */
public class FullyVisibleHand {
    public static final int NUM_FULL_CARDS = 7;

    private List<Card> cards;

    public FullyVisibleHand(List<Card> cards) {
        Assert.assertEquals(NUM_FULL_CARDS,cards.size());
        this.cards = cards;

    }

    public List<Card> getCards() {
        return cards;
    }


}
