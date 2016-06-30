package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.CardNumber;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

/**
 * Created by hungson175 on 6/26/2016.
 */
public class StraightHelpers {


    private static List<FullySelectedHand> createAllStraightHands(HashMap<CardNumber, List<Card>> mapNum2Cards) {
        Assert.assertEquals(FullySelectedHand.NUM_CARDS,mapNum2Cards.size());

        List<FullySelectedHand> hands = new ArrayList<>();

        ArrayList<CardNumber> cardNumbers = new ArrayList<>(mapNum2Cards.keySet());
        for(Card c0 : mapNum2Cards.get(cardNumbers.get(0)))
            for(Card c1 : mapNum2Cards.get(cardNumbers.get(1)))
                for(Card c2 : mapNum2Cards.get(cardNumbers.get(2)))
                    for(Card c3 : mapNum2Cards.get(cardNumbers.get(3)))
                        for(Card c4 : mapNum2Cards.get(cardNumbers.get(4))) {
                            List<Card> fullSelectedHand = new ArrayList<>();
                            fullSelectedHand.add(c0);
                            fullSelectedHand.add(c1);
                            fullSelectedHand.add(c2);
                            fullSelectedHand.add(c3);
                            fullSelectedHand.add(c4);
                            hands.add(new FullySelectedHand(fullSelectedHand));
                        }

        return hands;
    }

    /**
     * @param startCard
     * @param visibleHand
     * @return null if there is no straight start with card
     */
    public static List<FullySelectedHand> straightStartWith(Card startCard, FullyVisibleHand visibleHand) {
        //not Ace
        if (!CardNumber.canStartAStraight(startCard.getNumber())) return null;
        List<CardNumber> needCardNumbers = CardNumber.findNeededStraightCardNumbers(startCard.getNumber()); //the rest
        HashMap<CardNumber, List<Card>> mapNum2Cards = new HashMap<>();
        ArrayList<Card> firstCard = new ArrayList<>();
        firstCard.add(startCard);
        mapNum2Cards.put(startCard.getNumber(), firstCard);
        for (CardNumber cardNum : needCardNumbers) {
            List<Card> targetCards = Card.findCardNum(cardNum, visibleHand.getCards()); //what if 2 of 2, but different
            if (targetCards.size() == 0) {
                //no target card found
                return null;
            }
            mapNum2Cards.put(cardNum, targetCards);
        }
        return createAllStraightHands(mapNum2Cards);
    }

    protected static Card getHighestCardInStraight(FullySelectedHand validStraightHand) {
        List<Card> cards = validStraightHand.getCards();
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return lhs.getNumber().ordinal() - rhs.getNumber().ordinal();
            }
        });
        if (cards.get(4).getNumber() == CardNumber._A) {
            if (cards.get(3).getNumber() == CardNumber._5) {
                return cards.get(3);
            } else {
                return cards.get(4);
            }
        } else {
            return cards.get(4);
        }
    }

    public static int compareStraights(HandType mine, HandType opp) {
        Assert.assertTrue(mine instanceof Straight  || mine instanceof StraightFlush);
        Assert.assertTrue(opp instanceof Straight   || opp instanceof StraightFlush);
        Card oppHiCard = getHighestCardInStraight(opp.getSelectedHand());
        Card myHiCard = getHighestCardInStraight(mine.getSelectedHand());
//        System.out.println("This high card: " + thisHiCard + " | opp high card: " + oppHiCard);
        if (myHiCard.getNumber() == CardNumber._A) {
            if (oppHiCard.getNumber() != CardNumber._A) return 1;
            return 0;
        } else {
            if (oppHiCard.getNumber() == CardNumber._A) return -1;
            return myHiCard.getNumber().ordinal() - oppHiCard.getNumber().ordinal();
        }

    }

    protected static FullySelectedHand parseSelectedHand(List<FullySelectedHand> validHands) {
        if (validHands.size() == 0) return null;
        if (validHands.size() == 1) return validHands.get(0);
        Card hcard = null;
        FullySelectedHand bestHand = null;
        for (FullySelectedHand vh : validHands) {
            Card tcard = StraightHelpers.getHighestCardInStraight(vh);
            if ( tcard.getNumber()==CardNumber._A) {
                return vh;
            } else {
                if ( hcard == null || tcard.getNumber().ordinal() > hcard.getNumber().ordinal()) {
                    hcard = tcard;
                    bestHand = vh;
                }
            }
        }
        Assert.assertNotNull(bestHand);
        return bestHand;
    }
}
