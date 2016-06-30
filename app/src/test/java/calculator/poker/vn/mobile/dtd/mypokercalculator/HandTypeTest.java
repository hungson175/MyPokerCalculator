package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class HandTypeTest {

    @Test
    public void testFlush() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumber._A,CardType.D));
        cards.add(new Card(CardNumber._2,CardType.D));
        cards.add(new Card(CardNumber._6,CardType.D));
        cards.add(new Card(CardNumber._7,CardType.D));
        cards.add(new Card(CardNumber._J,CardType.D));
        Assert.assertTrue(new FullySelectedHand(cards).isFlush());

        cards = new ArrayList<>();
        cards.add(new Card(CardNumber._A,CardType.S));
        cards.add(new Card(CardNumber._2,CardType.S));
        cards.add(new Card(CardNumber._6,CardType.S));
        cards.add(new Card(CardNumber._7,CardType.S));
        cards.add(new Card(CardNumber._J,CardType.S));
        Assert.assertTrue(new FullySelectedHand(cards).isFlush());

        cards = new ArrayList<>();
        cards.add(new Card(CardNumber._A,CardType.D));
        cards.add(new Card(CardNumber._2,CardType.C));
        cards.add(new Card(CardNumber._6,CardType.S));
        cards.add(new Card(CardNumber._7,CardType.S));
        cards.add(new Card(CardNumber._J,CardType.S));
        Assert.assertFalse(new FullySelectedHand(cards).isFlush());

    }

}
