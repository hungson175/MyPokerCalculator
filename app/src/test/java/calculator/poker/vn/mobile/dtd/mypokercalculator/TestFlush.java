package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.Flush;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.StraightFlush;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.ThreeOfKind;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class TestFlush {



    //TODO: compare to straight flush
    //TODO: compare to high-card

    //test flush revealed, nothing else
    @Test
    public void testFlushRevealed() {

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._8, CardType.S));
        myCards.add(new Card(CardNumber._T, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A, CardType.S));
        oppCards.add(new Card(CardNumber._A, CardType.D));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof Flush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof Flush);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
    }


    //test flush revealed, 2 connected cards of 2 opps doesn't matter
    @Test
    public void testFlushRevealedWithConnectionNoHelp() {

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._T, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._3, CardType.H));
        oppCards.add(new Card(CardNumber._A, CardType.D));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof Flush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof Flush);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
    }

    @Test
    public void testFlushRevealedWithConnectionWithHelp() {

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._6, CardType.H));
        myCards.add(new Card(CardNumber._T, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._K, CardType.S));
        oppCards.add(new Card(CardNumber._K, CardType.D));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof Flush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof Flush);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    //test flush with highest card the same
    @Test
    public void testFlushHighestCardSame() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.H));
        myCards.add(new Card(CardNumber._T, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._Q, CardType.H));
        oppCards.add(new Card(CardNumber._K, CardType.D));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof Flush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof Flush);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    //test flush with highest card the same
    @Test
    public void testFlushHighestCardSame02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.H));
        myCards.add(new Card(CardNumber._J, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._Q, CardType.H));
        oppCards.add(new Card(CardNumber._2, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof Flush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof Flush);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    @Test
    public void testFlushCompareStraightFlush() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.H));
        myCards.add(new Card(CardNumber._J, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A, CardType.H));
        oppCards.add(new Card(CardNumber._2, CardType.H));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof StraightFlush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof Flush);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

    @Test
    public void testFlushCompareThreeOfKind() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.H));
        myCards.add(new Card(CardNumber._2, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._Q, CardType.S));
        oppCards.add(new Card(CardNumber._Q, CardType.D));

        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        Assert.assertTrue(myHand instanceof Flush);
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }

}
