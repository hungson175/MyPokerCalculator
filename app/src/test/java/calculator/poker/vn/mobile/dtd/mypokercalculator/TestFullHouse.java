package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.FourOfKind;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.FullHouse;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;

/**
 * Created by hungson175 on 25/06/2016.
 */
public class TestFullHouse {
    //TODO: test full house equals by revealed
    //TODO: test full house equals by revealed + hit cards

    @Test
    public void testFH01() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._8,CardType.H));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.H));
        rvCards.add(new Card(CardNumber._J,CardType.H));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._8,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType fhHT = HandType.parse(new FullyVisibleHand(cards));
        Assert.assertTrue(fhHT instanceof FullHouse);
        MyTestUtils.assertSameHand( new Card[] {
                new Card(CardNumber._8,CardType.H),
                new Card(CardNumber._8,CardType.S),
                new Card(CardNumber._8,CardType.C),
                new Card(CardNumber._A,CardType.D),
                new Card(CardNumber._A,CardType.H),
        }, fhHT.getSelectedHand());

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._8,CardType.D));
        oppCards.add(new Card(CardNumber._J,CardType.D));
        FullHouse oppHand = (FullHouse) HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertEquals(CardNumber._8, oppHand.getCardOfTriple().getNumber());
        Assert.assertEquals(CardNumber._J, oppHand.getCardOfPair().getNumber());

        Assert.assertTrue(fhHT.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(fhHT) < 0);

    }

    @Test
    public void testFH02() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._8,CardType.H));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.H));
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._8,CardType.C));

        HandType handType = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
        Assert.assertTrue(handType instanceof FullHouse);
        FullHouse myHand = (FullHouse) handType;
        Assert.assertEquals(CardNumber._A,myHand.getCardOfTriple().getNumber());
        Assert.assertEquals(CardNumber._8,myHand.getCardOfPair().getNumber());

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A,CardType.C));
        oppCards.add(new Card(CardNumber._Q,CardType.D));

        FullHouse oppHand = (FullHouse) HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
        Assert.assertEquals(CardNumber._A, oppHand.getCardOfTriple().getNumber());
        Assert.assertEquals(CardNumber._Q, oppHand.getCardOfPair().getNumber());

        Assert.assertTrue(oppHand.compareTo(handType) > 0);
        Assert.assertTrue(oppHand.compareTo(oppHand) == 0);
        Assert.assertTrue(myHand.compareTo(oppHand) < 0);

    }

    //test four of kind + full house at the same time, should parse out as FOK, but if new directly, still a full-house
    @Test
    public void testFH03() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.C));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.H));
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._8,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType handType = HandType.parse(new FullyVisibleHand(cards));
        Assert.assertTrue(handType instanceof FourOfKind);
        FullHouse fhHandType = new FullHouse(new FullyVisibleHand(cards));
        Assert.assertEquals(CardNumber._A,fhHandType.getCardOfTriple().getNumber());
        Assert.assertEquals(CardNumber._8,fhHandType.getCardOfPair().getNumber());

    }

    //test four of kind + full house at the same time, should parse out as FOK, but if new directly, still a full-house
    @Test
    public void testFH04() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.C));
        holeCards.add(new Card(CardNumber._A,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.H));
        rvCards.add(new Card(CardNumber._J,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._8,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType handType = HandType.parse(new FullyVisibleHand(cards));

        FullHouse fhHandType = new FullHouse(new FullyVisibleHand(cards));
        Assert.assertEquals(CardNumber._A,fhHandType.getCardOfTriple().getNumber());
        Assert.assertEquals(CardNumber._8,fhHandType.getCardOfPair().getNumber());

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._A,CardType.S));
        oppCards.add(new Card(CardNumber._8,CardType.H));

        FullHouse oppHand = (FullHouse) HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards, oppCards)));
        Assert.assertEquals(CardNumber._8,oppHand.getCardOfTriple().getNumber());
        Assert.assertEquals(CardNumber._A,oppHand.getCardOfPair().getNumber());

        Assert.assertTrue(handType.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(handType) < 0);
    }

    @Test
    public void testCompareFH2Flush() {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._A,CardType.C));
        holeCards.add(new Card(CardNumber._J,CardType.D));

        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._8,CardType.S));
        rvCards.add(new Card(CardNumber._A,CardType.H));
        rvCards.add(new Card(CardNumber._A,CardType.S));
        rvCards.add(new Card(CardNumber._Q,CardType.S));
        rvCards.add(new Card(CardNumber._J,CardType.C));
        List<Card> cards = Helpers.merge(holeCards, rvCards);

        HandType myHand= HandType.parse(new FullyVisibleHand(cards));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._K,CardType.S));
        oppCards.add(new Card(CardNumber._T,CardType.S));

        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(rvCards,oppCards)));

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);

    }
}
