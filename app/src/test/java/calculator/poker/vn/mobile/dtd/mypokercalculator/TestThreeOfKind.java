package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.FullHouse;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.ThreeOfKind;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.TwoPairs;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class TestThreeOfKind {
    /**
     * TODO:
     * - Assert FullHouse, assert Three of kind, then compare
     * - No: Assert 2 three of kind, get the three of kind card ? Is there any case like that ? no ! It's full house
     * - Compare 2 TOK: revealed + no help/with high card help/both high card help
     * - compare 2 pairs
     */

    @Test
    public void testTOK01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._A, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.C));
        oppCards.add(new Card(CardNumber._8, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof FullHouse);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    @Test
    public void testRevealedTOKnoHelp() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.H));
        myCards.add(new Card(CardNumber._3, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.C));
        oppCards.add(new Card(CardNumber._7, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);

    }

    @Test
    public void testRevealedTOKOneHighCard() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._A, CardType.H));
        myCards.add(new Card(CardNumber._3, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.C));
        oppCards.add(new Card(CardNumber._7, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }
    @Test
    public void testRevealedTOKTwoHighCard() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.D));
        rvCards.add(new Card(CardNumber._8, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._A, CardType.H));
        myCards.add(new Card(CardNumber._3, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._K, CardType.C));
        oppCards.add(new Card(CardNumber._J, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }

    @Test
    public void testCompare2Triples01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.D));
        rvCards.add(new Card(CardNumber._9, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._8, CardType.H));
        myCards.add(new Card(CardNumber._8, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T, CardType.C));
        oppCards.add(new Card(CardNumber._T, CardType.D));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);

    }

    @Test
    public void testCompare2Triples02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._9, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._J, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.C));
        oppCards.add(new Card(CardNumber._8, CardType.D));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }

    @Test
    public void testCompare2Triples03() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._9, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._7, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.C));
        oppCards.add(new Card(CardNumber._8, CardType.D));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);

    }

    @Test
    public void testCompare2Triples04() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._9, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._K, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.C));
        oppCards.add(new Card(CardNumber._A, CardType.D));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof ThreeOfKind);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);

    }

    @Test
    public void testCompareTriples2TwoPairs() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.S));
        rvCards.add(new Card(CardNumber._5, CardType.D));
        rvCards.add(new Card(CardNumber._9, CardType.C));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._4, CardType.H));
        myCards.add(new Card(CardNumber._4, CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._9, CardType.H));
        oppCards.add(new Card(CardNumber._Q, CardType.D));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof ThreeOfKind);
        Assert.assertTrue(oppHand instanceof TwoPairs);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }
}
