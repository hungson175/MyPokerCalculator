package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.FourOfKind;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HighCard;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.Straight;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.StraightFlush;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class StraightFlushTest {


    //TODO: test isSatisfiedAsType

    //test My hand: AhQh, revealed: 2s2h3h4h5h against 2d2c
    //test My hand: AhQh, revealed: 2h2s3h4h5h against 2d2c
    @Test
    public void test2Of2() {
        //My hand: AhQh, revealed: 2s2h3h4h5h
        //My hand: AhQh, revealed: 2h2s3h4h5h
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.H));
        holeCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2, CardType.S));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.H));

        HandType sf1 = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        Assert.assertTrue(sf1 instanceof StraightFlush);
        MyTestUtils.assertSameHand(new Card[]{
                new Card(CardNumber._A, CardType.H),
                new Card(CardNumber._2, CardType.H),
                new Card(CardNumber._3, CardType.H),
                new Card(CardNumber._4, CardType.H),
                new Card(CardNumber._5, CardType.H),
        }, sf1.getSelectedHand());

        HandType sf2 = HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, holeCards)));
        Assert.assertTrue(sf2 instanceof StraightFlush);

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._2, CardType.D));
        oppCards.add(new Card(CardNumber._2, CardType.C));
        HandType fokHand = HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards)));
        Assert.assertTrue(fokHand instanceof FourOfKind);
        MyTestUtils.assertSameHand(new Card[]{
                new Card(CardNumber._2, CardType.H),
                new Card(CardNumber._2, CardType.D),
                new Card(CardNumber._2, CardType.S),
                new Card(CardNumber._2, CardType.C),
                new Card(CardNumber._5, CardType.H),

        }, fokHand.getSelectedHand());

        Assert.assertTrue(sf1.compareTo(fokHand) > 0);
        Assert.assertTrue(sf2.compareTo(fokHand) > 0);
        Assert.assertTrue(fokHand.compareTo(sf2) < 0);
        Assert.assertTrue(fokHand.compareTo(sf1) < 0);
        Assert.assertTrue(sf1.compareTo(sf2) == 0);
    }

    //test My hand: AhQh, revealed: 2h2s3h4h5h against 6h8d
    //test My hand: KhQh, revealed: 2h2s3h4h5h against 6h8d
    //test My hand: KhQh, revealed: 2s2h3h4h5h against 6h8d

    @Test
    public void testCompareSF01() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.H));
        holeCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2, CardType.S));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.H));
        oppCards.add(new Card(CardNumber._8, CardType.D));

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards))) instanceof StraightFlush);
        MyTestUtils.assertSameHand(new Card[]{
                new Card(CardNumber._2, CardType.H),
                new Card(CardNumber._3, CardType.H),
                new Card(CardNumber._4, CardType.H),
                new Card(CardNumber._5, CardType.H),
                new Card(CardNumber._6, CardType.H)


        }, HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards))).getSelectedHand());


//        System.out.println("" + HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
//                .compareTo(
//                        HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards)))
//                )
//        );
        Assert.assertTrue(
                HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                        .compareTo(
                                HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards))))
                        < 0);
        Assert.assertTrue(
                HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards)))
                        .compareTo(
                                HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                        )
                        > 0);


    }

    @Test
    public void testCompareSF02() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.H));
        holeCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._2, CardType.S));
        rvCards.add(new Card(CardNumber._3, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._5, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.H));
        oppCards.add(new Card(CardNumber._8, CardType.D));

        Assert.assertTrue(
                HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                        .compareTo(
                                HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards))))
                        < 0);
        Assert.assertTrue(
                HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards)))
                        .compareTo(
                                HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                        )
                        > 0);
    }


    //compare: A2345 23456 TJQKA,   lat xuoi/nguoc phep so sanh
    @Test
    public void testCompare2SF03() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._2, CardType.D));
        rvCards.add(new Card(CardNumber._3, CardType.D));
        rvCards.add(new Card(CardNumber._4, CardType.D));
        rvCards.add(new Card(CardNumber._5, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.S));

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.D));
        holeCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._6, CardType.D));
        oppCards.add(new Card(CardNumber._8, CardType.S));

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof StraightFlush);

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ) < 0);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))

                ) > 0);
    }

    //9TJQK TJQKA,   lat xuoi/nguoc phep so sanh
    @Test
    public void testCompare2SF04() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._T, CardType.D));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.D));
        rvCards.add(new Card(CardNumber._Q, CardType.C));

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.D));
        holeCards.add(new Card(CardNumber._Q, CardType.S));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._9, CardType.D));
        oppCards.add(new Card(CardNumber._8, CardType.S));

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof StraightFlush);

        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._T, CardType.D),
                new Card(CardNumber._J, CardType.D),
                new Card(CardNumber._Q, CardType.D),
                new Card(CardNumber._K, CardType.D),
                new Card(CardNumber._A, CardType.D),
        }, HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))).getSelectedHand());

        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._T, CardType.D),
                new Card(CardNumber._J, CardType.D),
                new Card(CardNumber._Q, CardType.D),
                new Card(CardNumber._K, CardType.D),
                new Card(CardNumber._9, CardType.D),
        }, HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))).getSelectedHand());

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ) > 0);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))

                ) < 0);


    }


    //test compare : revealed SF
    @Test
    public void testRevealedSF() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._7, CardType.H));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._9, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.H));
        rvCards.add(new Card(CardNumber._J, CardType.H));

        //equal no flush
        subTestEqualNoFlush(rvCards);

        //equal with flush
        subTestEqualWithFlush(rvCards);

        //equal with bottom connection flush
        subTestEqualWithConnection(rvCards);

        //not equal
        subTestNotEqual(rvCards);

    }

    private void subTestNotEqual(List<Card> rvCards) {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.H));
        holeCards.add(new Card(CardNumber._Q, CardType.D));
        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.S));
        oppCards.add(new Card(CardNumber._Q, CardType.H));
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ) < 0);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))

                ) > 0);
    }

    private void subTestEqualWithConnection(List<Card> rvCards) {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.H));
        holeCards.add(new Card(CardNumber._Q, CardType.D));
        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.S));
        oppCards.add(new Card(CardNumber._6, CardType.H));
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof StraightFlush);
        Assert.assertEquals(0, HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ));
    }

    private void subTestEqualWithFlush(List<Card> rvCards) {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.H));
        holeCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.S));
        oppCards.add(new Card(CardNumber._K, CardType.H));
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof StraightFlush);
        Assert.assertEquals(0, HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ));
    }

    private void subTestEqualNoFlush(List<Card> rvCards) {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A, CardType.D));
        holeCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._5, CardType.S));
        oppCards.add(new Card(CardNumber._8, CardType.S));

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof StraightFlush);
        Assert.assertEquals(0, HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ));
    }

    @Test
    public void testCompare2HighCard() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._5, CardType.H));
        holeCards.add(new Card(CardNumber._6, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._K, CardType.S));
        oppCards.add(new Card(CardNumber._A, CardType.S));

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))) instanceof StraightFlush);
        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))) instanceof HighCard);

        MyTestUtils.assertSameHand(new Card[] {
                new Card(CardNumber._2, CardType.H),
                new Card(CardNumber._3, CardType.H),
                new Card(CardNumber._4, CardType.H),
                new Card(CardNumber._5, CardType.H),
                new Card(CardNumber._6, CardType.H),
        }, HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards))).getSelectedHand());

        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._4, CardType.H),
                new Card(CardNumber._T, CardType.S),
                new Card(CardNumber._Q, CardType.D),
                new Card(CardNumber._K, CardType.S),
                new Card(CardNumber._A, CardType.S),

        }, HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards))).getSelectedHand());

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                ) > 0);

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)))
                .compareTo(
                        HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)))

                ) < 0);
    }

    @Test
    public void testStraightOnly() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._5, CardType.D));
        holeCards.add(new Card(CardNumber._6, CardType.D));

        Assert.assertTrue(HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, holeCards))) instanceof Straight);
        Assert.assertFalse(HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, holeCards))) instanceof StraightFlush);

    }

}
