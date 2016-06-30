package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.Helpers;

/**
 * Created by hungson175 on 6/27/2016.
 */
public class ThreeOfKind extends  HandType{
    private Card tripleCard;
    private List<Card> minorCards;

    public ThreeOfKind(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        for (Card cardI : fullyVisibleHand.getCards()) {
            int cnt = 0;
            for (Card cardJ : fullyVisibleHand.getCards()) {
                if ( cardJ.getNumber() == cardI.getNumber()) {
                    cnt++;
                }
            }
            Assert.assertTrue(cnt <= 3);
            if ( cnt == 3 ) {
                this.tripleCard = cardI;
                this.minorCards = findMinorCards(cardI);
                this.selectedHand = findSelectedHand(tripleCard,minorCards);
                break;
            }
        }

    }

    private FullySelectedHand findSelectedHand(Card tripleCard, List<Card> minorCards) {
        List<Card> cards = new ArrayList<>();
        for (Card card : fullyVisibleHand.getCards()) {
            if ( card.getNumber() == tripleCard.getNumber() )
                cards.add(card);
        }
        Assert.assertEquals(3, cards.size());
        cards.addAll(minorCards);
        return new FullySelectedHand(cards);
    }

    private List<Card> findMinorCards(Card tripleCard) {
        List<Card> miCards = new ArrayList<>();
        List<Card> hiCards = new ArrayList<>();
        for (Card card : fullyVisibleHand.getCards()) {
            if ( card.getNumber() != tripleCard.getNumber() )
                hiCards.add(card);
        }
        Helpers.sortCardsByNumberInc(hiCards);
        Assert.assertTrue(hiCards.get(0).getNumber().compareTo(hiCards.get(1).getNumber()) < 0);
        int length = hiCards.size();
        miCards.add(hiCards.get(length-2));
        miCards.add(hiCards.get(length-1));
        return miCards;
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return this.selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        Assert.assertTrue(t instanceof ThreeOfKind);
        ThreeOfKind oppHand = (ThreeOfKind) t;
        ThreeOfKind myHand = (ThreeOfKind) this;

        Card oppTripleCard = oppHand.getTripleCard();
        Card myTripleCard = myHand.getTripleCard();
//        System.out.println("My triple card: "+ myTripleCard +" | Opp triple card: " + oppTripleCard);
        //so triple -> so 2 card con lai
        int compareTriple = myTripleCard.getNumber().compareTo(oppTripleCard.getNumber());
        if (compareTriple != 0)
            return compareTriple;
        else {
            List<Card> oppMinorCards = oppHand.getMinorCards();
            List<Card> myMinorCards = myHand.getMinorCards();

            Assert.assertEquals(2, oppMinorCards.size());
            Assert.assertEquals(2, myMinorCards.size());

            Helpers.sortCardsByNumberInc(oppMinorCards);
            Helpers.sortCardsByNumberInc(myMinorCards);
            for (int i = 1; i >= 0; i--) {
                int compareResult = myMinorCards.get(i).getNumber().compareTo(oppMinorCards.get(i).getNumber());
                if (compareResult != 0)
                    return compareResult;
            }
            return 0;
        }
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.THREE_OF_KIND;
    }

    public Card getTripleCard() {
        return tripleCard;
    }

    public List<Card> getMinorCards() {
        return minorCards;
    }
}
