package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;
import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HighCard;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class TestHighCard {

    @Test
    public void testHighCard01() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.C));
        myCards.add(new Card(CardNumber._A, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._7, CardType.C));
        oppCards.add(new Card(CardNumber._A, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof HighCard);
        Assert.assertTrue(oppHand instanceof HighCard);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);
    }

    @Test
    public void testHighCard02() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._9, CardType.C));
        myCards.add(new Card(CardNumber._A, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._7, CardType.C));
        oppCards.add(new Card(CardNumber._A, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof HighCard);
        Assert.assertTrue(oppHand instanceof HighCard);

        Assert.assertTrue(myHand.compareTo(oppHand) > 0);
        Assert.assertTrue(oppHand.compareTo(myHand) < 0);
    }
    @Test
    public void testHighCard03() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._7, CardType.C));
        myCards.add(new Card(CardNumber._Q, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._2, CardType.C));
        oppCards.add(new Card(CardNumber._A, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof HighCard);
        Assert.assertTrue(oppHand instanceof HighCard);

        Assert.assertTrue(myHand.compareTo(oppHand) < 0);
        Assert.assertTrue(oppHand.compareTo(myHand) > 0);
    }

    @Test
    public void testHighCard04() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._7, CardType.C));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        rvCards.add(new Card(CardNumber._K, CardType.H));

        List<Card> myCards = new ArrayList<>();
        myCards.add(new Card(CardNumber._2, CardType.C));
        myCards.add(new Card(CardNumber._3, CardType.H));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.C));
        oppCards.add(new Card(CardNumber._6, CardType.H));


        HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(myCards, rvCards)));
        HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));

        Assert.assertTrue(myHand instanceof HighCard);
        Assert.assertTrue(oppHand instanceof HighCard);

        Assert.assertTrue(myHand.compareTo(oppHand) == 0);
        Assert.assertTrue(oppHand.compareTo(myHand) == 0);
    }
}
