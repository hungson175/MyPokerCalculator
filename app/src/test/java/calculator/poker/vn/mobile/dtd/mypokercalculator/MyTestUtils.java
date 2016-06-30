package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

/**
 * Created by hungson175 on 25/06/2016.
 */
public class MyTestUtils {
    public static void assertSameHand(Card[] expectedCards, FullySelectedHand actualHand) {
        Assert.assertEquals(expectedCards.length, actualHand.getCards().size());
        for (Card card : actualHand.getCards()) {
            boolean found = false;
            for (Card ecard : expectedCards) {
                if (ecard.equals(card)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }



}
