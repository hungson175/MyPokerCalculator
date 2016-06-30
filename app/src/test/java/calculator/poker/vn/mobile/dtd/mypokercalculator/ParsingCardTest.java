package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import org.junit.Test;

import java.security.InvalidParameterException;

/**
 * Created by hungson175 on 23/06/2016.
 */
public class ParsingCardTest {
    @Test
    public void testParseAllValidCards() {
        String cardNumbers = "A23456789TJQK";
        String cardTypes = "HDSC";
        for(char cnc : cardNumbers.toCharArray()) {
            for(char ctc : cardTypes.toCharArray()) {
                CardNumber expectedCN = CardNumber.parse(cnc);
                CardType expectedCT = CardType.parse(ctc);

                Card card = Card.parse(cnc, ctc);
                Assert.assertEquals(expectedCN,card.getNumber());
                Assert.assertEquals(expectedCT,card.getType());

                Card card1 = Card.parse(cnc, Character.toLowerCase(ctc));
                Assert.assertEquals(expectedCN,card1.getNumber());
                Assert.assertEquals(expectedCT,card1.getType());

                Card card3 = Card.parse(Character.toLowerCase(cnc), ctc);
                Assert.assertEquals(expectedCN,card3.getNumber());
                Assert.assertEquals(expectedCT,card3.getType());


                Card card2 = Card.parse(Character.toLowerCase(cnc), Character.toLowerCase(ctc));
                Assert.assertEquals(expectedCN,card2.getNumber());
                Assert.assertEquals(expectedCT,card2.getType());
            }
        }


    }

    @Test
    public void testManual() {
        Assert.assertEquals(CardNumber._A, Card.parse('A', 'h').getNumber());
        Assert.assertEquals(CardType.H, Card.parse('A', 'h').getType());


        Assert.assertEquals(CardNumber._A, Card.parse('a', 'h').getNumber());
        Assert.assertEquals(CardType.H, Card.parse('a', 'h').getType());

        Assert.assertEquals(CardNumber._A, Card.parse('a', 'H').getNumber());
        Assert.assertEquals(CardType.H, Card.parse('a', 'H').getType());

        Assert.assertEquals(CardNumber._A, Card.parse('A', 'H').getNumber());
        Assert.assertEquals(CardType.H, Card.parse('A', 'H').getType());

        Assert.assertEquals(CardNumber._K, Card.parse('K','d').getNumber());
        Assert.assertEquals(CardType.D, Card.parse('K','d').getType());

        Assert.assertEquals(CardNumber._2, Card.parse('2','s').getNumber());
        Assert.assertEquals(CardType.S, Card.parse('2','s').getType());

        Assert.assertEquals(CardNumber._2, Card.parse('2','S').getNumber());
        Assert.assertEquals(CardType.S, Card.parse('2','S').getType());

        Assert.assertEquals(CardNumber._T, Card.parse('T','C').getNumber());
        Assert.assertEquals(CardType.C, Card.parse('T','c').getType());

    }



    @Test
    public void testInvalid() {
        testInvalidCard('L','d');
        testInvalidCard('L','D');
        testInvalidCard('T','G');
    }

    private void testInvalidCard(char numChar, char typeChar) {
        try {
            Card.parse(numChar, typeChar);
            Assert.fail();
        } catch (InvalidParameterException e) {
            //ok
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
