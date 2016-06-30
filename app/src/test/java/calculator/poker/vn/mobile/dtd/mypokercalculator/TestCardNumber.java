package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class TestCardNumber {

    @Test
    public void testStartAStraight() {
        for (CardNumber cn : CardNumber.values()) {
            switch (cn) {
                case _A:
                case _2:
                case _3:
                case _4:
                case _5:
                case _6:
                case _7:
                case _8:
                case _9:
                case _T:
                    Assert.assertTrue(CardNumber.canStartAStraight(cn));
                    break;
                case _J:
                case _Q:
                case _K:
                    Assert.assertFalse(CardNumber.canStartAStraight(cn));
                    break;
            }
        }
    }

    @Test
    public void testfindNeededStraightCardNumbers() {
        verify(
                new CardNumber[]{CardNumber._2,CardNumber._3,CardNumber._4,CardNumber._5},
                CardNumber.findNeededStraightCardNumbers(CardNumber._A));

        verify(
                new CardNumber[]{CardNumber._J,CardNumber._Q,CardNumber._K,CardNumber._A},
                CardNumber.findNeededStraightCardNumbers(CardNumber._T));

        verify(
                new CardNumber[]{CardNumber._6,CardNumber._7,CardNumber._8,CardNumber._9},
                CardNumber.findNeededStraightCardNumbers(CardNumber._5));

        Assert.assertNull(CardNumber.findNeededStraightCardNumbers(CardNumber._J));


    }

    private void verify(CardNumber[] expected, List<CardNumber> actual) {
        Assert.assertEquals(expected.length,actual.size());
        for (CardNumber cn : expected) {
            if ( ! foundInList(cn,actual)) {
                Assert.fail("Cannot "+ cn +"be found in returned list: " + actual.toString());
            }
        }
    }

    private boolean foundInList(CardNumber cn, List<CardNumber> actual) {
        for (CardNumber acn : actual) {
            if ( acn == cn ) return true;
        }
        return false;
    }

    @Test
    public void testOrder() {
        //DONT EVER CHANGE THIS TEST, THIS WILL AFFECT ALL OTHER CODE BECAUSE OF THE ACE
        Assert.assertEquals(0,CardNumber._2.ordinal());
        Assert.assertEquals(1,CardNumber._3.ordinal());
        Assert.assertEquals(2,CardNumber._4.ordinal());
        Assert.assertEquals(3,CardNumber._5.ordinal());
        Assert.assertEquals(4,CardNumber._6.ordinal());
        Assert.assertEquals(5,CardNumber._7.ordinal());
        Assert.assertEquals(6,CardNumber._8.ordinal());
        Assert.assertEquals(7,CardNumber._9.ordinal());
        Assert.assertEquals(8,CardNumber._T.ordinal());
        Assert.assertEquals(9,CardNumber._J.ordinal());
        Assert.assertEquals(10,CardNumber._Q.ordinal());
        Assert.assertEquals(11,CardNumber._K.ordinal());
        Assert.assertEquals(12,CardNumber._A.ordinal());

        Assert.assertTrue(CardNumber._A.ordinal() > CardNumber._2.ordinal());
        Assert.assertTrue(CardNumber._A.ordinal() > CardNumber._J.ordinal());
    }

}
