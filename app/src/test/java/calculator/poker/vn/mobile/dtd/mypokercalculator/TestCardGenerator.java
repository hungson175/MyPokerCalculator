package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;

/**
 * Created by hungson175 on 6/28/2016.
 */
public class TestCardGenerator {

    @Test
    public void testGen() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._2, CardType.H));
        rvCards.add(new Card(CardNumber._3, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.H));
        rvCards.add(new Card(CardNumber._Q, CardType.D));

        long startTime = System.currentTimeMillis();
        int NUM_GEN_TIMES = 100000;
        for(int i = 0; i < NUM_GEN_TIMES; i++) {
            CardsGenerator cgen = new CardsGenerator(rvCards);
            List<Card> cards = cgen.nRandomCards(5);
            Assert.assertEquals(5, cards.size());
            for (Card card : cards) {
                for (Card rcard : rvCards) {
                    Assert.assertFalse(card.equals(rcard));
                }
            }
        }
        System.out.println("Generated "+ NUM_GEN_TIMES +" test after "+ (System.currentTimeMillis()-startTime)+" millis");

    }

    @Test(timeout = 2000)
    public void testPerformance() {
        int NUM_GEN_TIMES = 100000;
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(CardNumber._Q, CardType.D));
        holeCards.add(new Card(CardNumber._A, CardType.D));

        List<Card> oppCards = new ArrayList<>();
        oppCards.add(new Card(CardNumber._4, CardType.S));
        oppCards.add(new Card(CardNumber._4, CardType.H));

        List<Card> playerCards = Helpers.merge(oppCards,holeCards);
        boolean wannaView = false;

        CardsGenerator generator = new CardsGenerator(playerCards);
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < NUM_GEN_TIMES; i++) {
            List<Card> rvCards = generator.nRandomCards(5);
            HandType myHand = HandType.parse(new FullyVisibleHand(Helpers.merge(holeCards, rvCards)));
            HandType oppHand = HandType.parse(new FullyVisibleHand(Helpers.merge(oppCards, rvCards)));
            if ( wannaView ) {
                System.out.println("====================");
                System.out.println("My cards: " + Helpers.printCards(holeCards) + " | Opp cards: " + Helpers.printCards(oppCards) + " | Revealed: " + Helpers.printCards(rvCards));
                System.out.println("My hand rank: " + myHand.getRanking() + " | Opp hand rank: " + oppHand.getRanking() + " Myhand-OppHand: " + myHand.compareTo(oppHand));
            }
        }
        System.out.println("Generated & Compare "+ NUM_GEN_TIMES +" test after "+ (System.currentTimeMillis()-startTime)+" millis");
    }

}
