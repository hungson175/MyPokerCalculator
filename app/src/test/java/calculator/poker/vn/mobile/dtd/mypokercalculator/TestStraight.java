package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.Flush;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.Straight;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.TwoPairs;

/**
 * Created by hungson175 on 6/26/2016.
 */
public class TestStraight {
    @Test
    public void testStraight01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._7, CardType.D));
        holeCards.add(new Card(CardNumber._9, CardType.D));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        Assert.assertTrue(myHand instanceof Straight);
    }

    @Test
    public void testRevealedStraight() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._7, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._8, CardType.D));
        holeCards.add(new Card(CardNumber._A, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._Q, CardType.D));
        oppCards.add(new Card(CardNumber._Q, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);

    }

    @Test
    public void testRevealedStraightConnectedNoHelp() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._7, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._2, CardType.D));
        holeCards.add(new Card(CardNumber._3, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._3, CardType.S));
        oppCards.add(new Card(CardNumber._Q, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);

    }

    @Test
    public void testRevealedStraightConnectedWithHelp01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._7, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._9, CardType.D));
        holeCards.add(new Card(CardNumber._3, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._3, CardType.S));
        oppCards.add(new Card(CardNumber._Q, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }


    @Test
    public void testRevealedStraightConnectedWithHelp02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._7, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._9, CardType.D));
        holeCards.add(new Card(CardNumber._T, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._9, CardType.S));
        oppCards.add(new Card(CardNumber._J, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }

    @Test
    public void testCompareStraight2Straight01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._A, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._Q, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._7, CardType.D));
        holeCards.add(new Card(CardNumber._9, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.S));
        oppCards.add(new Card(CardNumber._7, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }

    @Test
    public void testCompareStraight2Straight02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._A, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._6, CardType.C));
        rvCards.add(new Card(CardNumber._7, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._4, CardType.D));
        holeCards.add(new Card(CardNumber._3, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A, CardType.H));
        oppCards.add(new Card(CardNumber._9, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);

    }

    @Test
    public void testCompareStraight2Straight03() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._Q, CardType.S));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.C));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.D));
        holeCards.add(new Card(CardNumber._3, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.C));
        oppCards.add(new Card(CardNumber._T, CardType.S));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Straight);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);

    }

    @Test
    public void testCompareStraight2Flush() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._Q, CardType.H));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.C));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._8, CardType.H));
        holeCards.add(new Card(CardNumber._T, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.C));
        oppCards.add(new Card(CardNumber._A, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof Flush);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }

    @Test
    public void testCompareStraight2Pair() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._Q, CardType.H));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.C));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.D));


        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._Q, CardType.D));
        holeCards.add(new Card(CardNumber._5, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.C));
        oppCards.add(new Card(CardNumber._T, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof TwoPairs);
        Assert.assertTrue(oppHand instanceof Straight);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);

    }

}
