package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class FullySelectedHand {
    public static final int NUM_CARDS = 5;
    List<Card> cards = new ArrayList<>();

    public FullySelectedHand(List<Card> cards) {
        this.cards = cards;
        Assert.assertEquals(5,cards.size());
    }

    public boolean isFlush() {
        CardType type = cards.get(0).getType();
        for (Card c : cards) {
            if ( c.getType() != type ) return false;
        }
        return true;
    }

    public List<Card> getCards() {
        return cards;
    }
}
