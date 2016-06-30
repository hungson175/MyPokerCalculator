package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.FullHouse;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HighCard;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.TwoPairs;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class TestTwoPairs {
    /**
     * TODO:
     * - Test 2 pairs vs full house
     * - revealed 2 pair, no help/1 help/2 help
     * - first pair = , second pair !=
     * - first pair = , second pair = , hicard = / !=
     * - test 3 pairs
     * - test compare with highcard Ace
     */
    @Test
    public void testFullHouse() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
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

        Assert.assertTrue(myHand instanceof FullHouse);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testRevealedNoHelp() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._7, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._3, CardType.C));
        oppCards.add(new Card(CardNumber._6, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);
    }


    @Test
    public void testRevealedOneHelp() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._3, CardType.C));
        oppCards.add(new Card(CardNumber._6, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testRevealedTwoHelp() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.H));
        myCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._2, CardType.C));
        oppCards.add(new Card(CardNumber._Q, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);
    }

    @Test
    public void testRevealedTwoHelp02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.H));
        myCards.add(new Card(CardNumber._7, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._2, CardType.C));
        oppCards.add(new Card(CardNumber._Q, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }


    @Test
    public void testCompare2TwoPairs01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._8, CardType.H));
        myCards.add(new Card(CardNumber._4, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._8, CardType.S));
        oppCards.add(new Card(CardNumber._5, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }


    @Test
    public void testCompare2TwoPairs02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._T, CardType.D));
        myCards.add(new Card(CardNumber._5, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T, CardType.S));
        oppCards.add(new Card(CardNumber._4, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testCompare2TwoPairs03() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._T, CardType.D));
        myCards.add(new Card(CardNumber._K, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T, CardType.S));
        oppCards.add(new Card(CardNumber._A, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    @Test
    public void testCompare2TwoPairs04() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._T, CardType.D));
        myCards.add(new Card(CardNumber._K, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T, CardType.S));
        oppCards.add(new Card(CardNumber._J, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testCompare2TwoPairs05() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._T, CardType.D));
        myCards.add(new Card(CardNumber._4, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T, CardType.S));
        oppCards.add(new Card(CardNumber._A, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    @Test
    public void testCompare2HighCard() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.D));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.D));
        myCards.add(new Card(CardNumber._6, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._K, CardType.S));
        oppCards.add(new Card(CardNumber._A, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof HighCard);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }


}
