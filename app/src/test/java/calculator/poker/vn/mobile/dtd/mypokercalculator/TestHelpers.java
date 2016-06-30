package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class TestHelpers {
    @Test
    public void testMerge() {
        ArrayList<Integer> first = new ArrayList<>();
        first.add(Integer.valueOf(1));

        ArrayList<Integer> second = new ArrayList<>();
        second.add(Integer.valueOf(2));
        second.add(Integer.valueOf(3));

        int[] EXPECTED = {1,2,3};
        assertEqualsArrayVsList(EXPECTED, Helpers.merge(first, second));

        ArrayList<Integer> first01 = new ArrayList<>();
        int[] EXPECTED01 = {2,3};
        assertEqualsArrayVsList(EXPECTED01, Helpers.merge(first01, second));

    }

    private void assertEqualsArrayVsList(int[] EXPECTED, List<Integer> merge) {
        Assert.assertEquals(EXPECTED.length, merge.size());
        for(int i = 0 ; i < merge.size() ; i++) {
            Assert.assertEquals(EXPECTED[i],(int)merge.get(i));
        }
    }

    @Test
    public void testSortCardsByNumberInc() {
        List<Card> rvCards = new ArrayList<>();
        rvCards.add(new Card(CardNumber._T, CardType.S));
        rvCards.add(new Card(CardNumber._8, CardType.H));
        rvCards.add(new Card(CardNumber._K, CardType.H));
        rvCards.add(new Card(CardNumber._4, CardType.C));
        rvCards.add(new Card(CardNumber._J, CardType.D));
        Helpers.sortCardsByNumberInc(rvCards);
        Assert.assertEquals(CardNumber._4, rvCards.get(0).getNumber());
        Assert.assertEquals(CardNumber._8, rvCards.get(1).getNumber());
        Assert.assertEquals(CardNumber._T, rvCards.get(2).getNumber());
        Assert.assertEquals(CardNumber._J, rvCards.get(3).getNumber());
        Assert.assertEquals(CardNumber._K, rvCards.get(4).getNumber());
    }
}
