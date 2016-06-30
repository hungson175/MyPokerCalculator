package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

public class StraightFlush extends HandType {


    public StraightFlush(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        List<FullySelectedHand> validHands = new ArrayList<>();
        for (Card card : this.fullyVisibleHand.getCards()) {
            List<FullySelectedHand> straightHands = StraightHelpers.straightStartWith(card, this.fullyVisibleHand);
            if (straightHands != null) {
                for (FullySelectedHand hand : straightHands) {
                    if (hand.isFlush()) {
                        validHands.add(hand);
                    }
                }
            }
        }
        this.selectedHand = StraightHelpers.parseSelectedHand(validHands);
    }


    /**
     * In 7 fullyVisibleHand, there are 5 fullyVisibleHand that continuous and same card type
     *
     * @return
     */
    @Override
    protected boolean isSatisfiedAsType() {
        return selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        return StraightHelpers.compareStraights(this, t);
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.STRAIGHT_FLUSH;
    }

    public Card getHighestCard() {
        Assert.assertNotNull(selectedHand);
        return StraightHelpers.getHighestCardInStraight(selectedHand);
    }



}
