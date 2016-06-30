package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungson175 on 23/06/2016.
 */
public class Card {
    CardNumber number;
    CardType type;
    private int color;

    public CardNumber getNumber() {
        return number;
    }

    public CardType getType() {
        return type;
    }

    public Card(CardNumber number, CardType type) {
        this.number = number;
        this.type = type;
    }

    public static Card parse(char number, char type) throws InvalidParameterException {
        return new Card(CardNumber.parse(number), CardType.parse(type));
    }

    public static List<Card> findCardNum(CardNumber cardNum, List<Card> cards) {
        List<Card> list = new ArrayList<>();
        for (Card c : cards) {
            if (c.getNumber() == cardNum) list.add(c);
        }
        return list;
    }

    @Override
    public String toString() {
        return number.toString() + "" + type.toString().toLowerCase();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof  Card);
        if ( this == o ) return true;
        Card oppCard = (Card) o;
        return this.getNumber() == oppCard.getNumber() && this.getType() == oppCard.getType();
    }

    public int compareTo(Card oppCard) {
        return this.getNumber().compareTo(oppCard.getNumber());
    }

    public int getColor() {
        return color;
    }
}
