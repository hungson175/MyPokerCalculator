package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.OnePair;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.Straight;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.TwoPairs;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class TestPair {
    /**
     * TODO:
     * - test assert pair, 2 pairs
     * - test revealed 1 pairs 0/1/2 helped cards
     * - not revealed: toppair vs second pair, top vs top kicker,
     * - compare to straight
     */

    @Test
    public void testAssertPairs() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A, CardType.C));
        oppCards.add(new Card(CardNumber._8, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof OnePair);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testRevealedOnePairNoHelp() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._3, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.C));
        oppCards.add(new Card(CardNumber._7, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof OnePair);
        Assert.assertTrue(oppHand instanceof OnePair);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);
    }

    @Test
    public void testRevealedOnePair2Help() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._A, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.C));
        oppCards.add(new Card(CardNumber._K, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof OnePair);
        Assert.assertTrue(oppHand instanceof OnePair);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testRevealedOnePair2Straight() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._A, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._9, CardType.C));
        oppCards.add(new Card(CardNumber._K, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof OnePair);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    @Test
    public void testComparePair2Pair01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._J, CardType.H));
        myCards.add(new Card(CardNumber._A, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._K, CardType.C));
        oppCards.add(new Card(CardNumber._2, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof OnePair);
        Assert.assertTrue(oppHand instanceof OnePair);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);

    }

    @Test
    public void testComparePair2Pair02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._J, CardType.H));
        myCards.add(new Card(CardNumber._A, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._9, CardType.C));
        oppCards.add(new Card(CardNumber._9, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof OnePair);
        Assert.assertTrue(oppHand instanceof OnePair);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }

    @Test
    public void testComparePair2Pair03() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.C));
        oppCards.add(new Card(CardNumber._9, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof OnePair);
        Assert.assertTrue(oppHand instanceof OnePair);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }
}
