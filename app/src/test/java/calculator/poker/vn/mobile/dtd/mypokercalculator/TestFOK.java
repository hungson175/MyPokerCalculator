package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.FourOfKind;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class TestFOK {

    @Test
    public void testFOK1() {

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._8,CardType.H));
        holeCards.add(new Card(CardNumber._8,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._T,CardType.D));
        rvCards.add(new Card(CardNumber._J,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._8,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType fokType = HandType.parse(new FullyVisibleHand(cards));
        Assert.assertTrue(fokType instanceof  FourOfKind);
        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._8,CardType.H),
                new Card(CardNumber._8,CardType.D),
                new Card(CardNumber._8,CardType.S),
                new Card(CardNumber._8,CardType.C),
                new Card(CardNumber._Q,CardType.S),
        }, fokType.getSelectedHand());
    }

    @Test
    public void testFOK2() {

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._8,CardType.H));
        holeCards.add(new Card(CardNumber._8,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._T,CardType.D));
        rvCards.add(new Card(CardNumber._J,CardType.H));
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._8,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType fokType = HandType.parse(new FullyVisibleHand(cards));
        Assert.assertTrue(fokType instanceof  FourOfKind);
        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._8,CardType.H),
                new Card(CardNumber._8,CardType.D),
                new Card(CardNumber._8,CardType.S),
                new Card(CardNumber._8,CardType.C),
                new Card(CardNumber._A,CardType.S),
        }, fokType.getSelectedHand());
    }

    @Test
    public void testFOK3() {

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.H));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._T,CardType.D));
        rvCards.add(new Card(CardNumber._J,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType fokType = HandType.parse(new FullyVisibleHand(cards));
        Assert.assertTrue(fokType instanceof  FourOfKind);
        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._A,CardType.H),
                new Card(CardNumber._A,CardType.D),
                new Card(CardNumber._A,CardType.S),
                new Card(CardNumber._A,CardType.C),
                new Card(CardNumber._Q,CardType.S),
        }, fokType.getSelectedHand());
    }

    @Test
    public void testFOK4() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._2,CardType.H));
        holeCards.add(new Card(CardNumber._2,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2,CardType.S));
        rvCards.add(new Card(CardNumber._T,CardType.D));
        rvCards.add(new Card(CardNumber._J,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._2,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType fokType = HandType.parse(new FullyVisibleHand(cards));
        Assert.assertTrue(fokType instanceof  FourOfKind);
        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._2,CardType.H),
                new Card(CardNumber._2,CardType.D),
                new Card(CardNumber._2,CardType.S),
                new Card(CardNumber._2,CardType.C),
                new Card(CardNumber._Q,CardType.S),
        }, fokType.getSelectedHand());
    }


    @Test
    public void testCompare2FOK1() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._2,CardType.H));
        holeCards.add(new Card(CardNumber._2,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2,CardType.S));
        rvCards.add(new Card(CardNumber._T,CardType.D));
        rvCards.add(new Card(CardNumber._T,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._2,CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T,CardType.S));
        oppCards.add(new Card(CardNumber._T,CardType.C));

        List<Card> myVisibleCards = Helpers.merge(holeCards, rvCards);
        List<Card> oppVisibleCards = Helpers.merge(oppCards, rvCards);

        HandType myHT = HandType.parse(new FullyVisibleHand(myVisibleCards));
        HandType oppHT = HandType.parse(new FullyVisibleHand(oppVisibleCards));

        Assert.assertTrue( myHT.compareTo(oppHT) < 0);
        Assert.assertTrue( oppHT.compareTo(myHT) > 0);
    }

    @Test
    public void testCompare2FOK2() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.H));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._T,CardType.D));
        rvCards.add(new Card(CardNumber._T,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._T,CardType.S));
        oppCards.add(new Card(CardNumber._T,CardType.C));

        List<Card> myVisibleCards = Helpers.merge(holeCards, rvCards);
        List<Card> oppVisibleCards = Helpers.merge(oppCards, rvCards);

        HandType myHT = HandType.parse(new FullyVisibleHand(myVisibleCards));
        HandType oppHT = HandType.parse(new FullyVisibleHand(oppVisibleCards));

        Assert.assertTrue( myHT.compareTo(oppHT) > 0);
        Assert.assertTrue( oppHT.compareTo(myHT) < 0);
    }

    @Test
    public void testCompare2FOK3() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.H));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._2,CardType.D));
        rvCards.add(new Card(CardNumber._2,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._2,CardType.S));
        oppCards.add(new Card(CardNumber._2,CardType.C));

        List<Card> myVisibleCards = Helpers.merge(holeCards, rvCards);
        List<Card> oppVisibleCards = Helpers.merge(oppCards, rvCards);

        HandType myHT = HandType.parse(new FullyVisibleHand(myVisibleCards));
        HandType oppHT = HandType.parse(new FullyVisibleHand(oppVisibleCards));

        Assert.assertTrue( myHT.compareTo(oppHT) > 0);
        Assert.assertTrue( oppHT.compareTo(myHT) < 0);
    }

    @Test
    public void testCompareFOK2FullHouse() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._2,CardType.H));
        holeCards.add(new Card(CardNumber._2,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.D));
        rvCards.add(new Card(CardNumber._A,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._2,CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A,CardType.S));
        oppCards.add(new Card(CardNumber._Q,CardType.C));

        List<Card> myVisibleCards = Helpers.merge(holeCards, rvCards);
        List<Card> oppVisibleCards = Helpers.merge(oppCards, rvCards);

        HandType myHT = HandType.parse(new FullyVisibleHand(myVisibleCards));
        HandType oppHT = HandType.parse(new FullyVisibleHand(oppVisibleCards));

        Assert.assertTrue( myHT.compareTo(oppHT) > 0);
        Assert.assertTrue( oppHT.compareTo(myHT) < 0);
    }

    @Test
    public void testCompareFOKBiggestRevealedHelp() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.H));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.D));
        rvCards.add(new Card(CardNumber._Q,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._J,CardType.S));
        oppCards.add(new Card(CardNumber._J,CardType.C));

        List<Card> myVisibleCards = Helpers.merge(holeCards, rvCards);
        List<Card> oppVisibleCards = Helpers.merge(oppCards, rvCards);

        HandType myHT = HandType.parse(new FullyVisibleHand(myVisibleCards));
        HandType oppHT = HandType.parse(new FullyVisibleHand(oppVisibleCards));
        Assert.assertTrue(myHT instanceof  FourOfKind);
        Assert.assertTrue(oppHT instanceof  FourOfKind);

        Assert.assertTrue( myHT.compareTo(oppHT) > 0);
        Assert.assertTrue( oppHT.compareTo(myHT) < 0);

    }

    @Test
    public void testCompareFOKBiggestRevealedNoHelp() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._3,CardType.H));
        holeCards.add(new Card(CardNumber._3,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.D));
        rvCards.add(new Card(CardNumber._Q,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.C));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._J,CardType.S));
        oppCards.add(new Card(CardNumber._J,CardType.C));

        List<Card> myVisibleCards = Helpers.merge(holeCards, rvCards);
        List<Card> oppVisibleCards = Helpers.merge(oppCards, rvCards);

        HandType myHT = HandType.parse(new FullyVisibleHand(myVisibleCards));
        HandType oppHT = HandType.parse(new FullyVisibleHand(oppVisibleCards));
        Assert.assertTrue(myHT instanceof  FourOfKind);
        Assert.assertTrue(oppHT instanceof  FourOfKind);


        Assert.assertTrue( myHT.compareTo(oppHT) == 0);
        Assert.assertTrue( oppHT.compareTo(myHT) == 0);

    }



}
