package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

/**
 * Created by hungson175 on 6/26/2016.
 */
public class Straight extends HandType {
    public Straight(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        List<FullySelectedHand> validHands = new ArrayList<>();
        for (Card card : fullyVisibleHand.getCards()) {
            List<FullySelectedHand> straightHands = StraightHelpers.straightStartWith(card, this.fullyVisibleHand);
            if ( straightHands != null)
                validHands.addAll(straightHands);
        }
        this.selectedHand = StraightHelpers.parseSelectedHand(validHands);
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        Assert.assertEquals(HandRanking.STRAIGHT, t.getRanking());
        return StraightHelpers.compareStraights(this, t);
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.STRAIGHT;
    }
}
