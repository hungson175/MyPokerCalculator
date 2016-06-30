package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

/**
 * Created by hungson175 on 24/06/2016.
 */
public abstract  class HandType {
    private Card tripleCard;

    public enum HandRanking {
        HIGH_CARD,
        PAIR,
        TWO_PAIRS,
        THREE_OF_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_KIND,
        STRAIGHT_FLUSH,
    }
    protected  FullySelectedHand selectedHand;
    //@condition
    private static final Class<?>[] HAND_TYPE_CLASSES =  {
            StraightFlush.class,
            FourOfKind.class,
            FullHouse.class,
            Flush.class,
            Straight.class,
            ThreeOfKind.class,
            TwoPairs.class,
            OnePair.class,
            HighCard.class,
    };
    protected FullyVisibleHand fullyVisibleHand;

    //TODO: how to change all constructor to protected ?
    //Every Sub-HandType constructor must be protected, only create instance using parse() is allowed
    protected HandType(FullyVisibleHand fullyVisibleHand) {
        this.fullyVisibleHand = fullyVisibleHand;
    }

    public static HandType parse(FullyVisibleHand visibleHand) {
        for (Class<?> htypeClass : HAND_TYPE_CLASSES) {
            HandType handType  = getHandType(htypeClass, visibleHand);
            if ( handType.isSatisfiedAsType() )
                return handType;
        }
        Assert.fail();
        return null;
    }

    private static HandType getHandType(Class<?> hr, FullyVisibleHand cards) {
        try {
            Constructor<?> cons = hr.getConstructor(FullyVisibleHand.class);
           return (HandType) (cons.newInstance(cards));
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            Assert.fail(targetException.toString());
            return null;
        } catch (Exception e) {
            Assert.fail(e.toString());
            return null;
        }
    }

    protected abstract boolean isSatisfiedAsType();
    public int compareTo(HandType t) {
        Assert.assertNotNull(t);
        Assert.assertNotNull(this.selectedHand);
        if ( this == t) return 0;
        int compareRank = this.getRanking().compareTo(t.getRanking());
        if ( compareRank != 0) return compareRank;
        return compareSameRank(t);
    }
    protected abstract int compareSameRank(HandType t);

    public FullySelectedHand getSelectedHand() {
        return this.selectedHand;
    }
    public abstract  HandRanking getRanking();

}
