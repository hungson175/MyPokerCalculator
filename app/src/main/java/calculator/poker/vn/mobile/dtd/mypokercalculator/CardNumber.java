package calculator.poker.vn.mobile.dtd.mypokercalculator;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungson175 on 20/06/2016.
 */
public enum CardNumber {
    //Implicit assumption: if nothing otherwise stated, A should be assumed to be better than all other cards
    _2,_3,_4,_5,_6,_7,_8,_9,_T,_J,_Q,_K,_A;
    static final String CARD_NUMBERS = "23456789TJQKA";
    private int drawText;

    public static CardNumber parse(char cardNumberChar) throws InvalidParameterException {
        cardNumberChar = Character.toUpperCase(cardNumberChar);
        if ( CARD_NUMBERS.indexOf(""+cardNumberChar) == -1)
            throw new InvalidParameterException("No such card number: " + cardNumberChar);
        return CardNumber.valueOf("_"+Character.toUpperCase(cardNumberChar));
    }


    public static boolean canStartAStraight(CardNumber cn) {
        return cn == _A || cn.ordinal() <= _T.ordinal();
    }

    public static List<CardNumber> findNeededStraightCardNumbers(CardNumber startNumber) {
        List<CardNumber> list = new ArrayList<>();
        if ( startNumber == _A) {
            list.add(_2);
            list.add(_3);
            list.add(_4);
            list.add(_5);
        } else if ( startNumber == _T) {
            list.add(_J);
            list.add(_Q);
            list.add(_K);
            list.add(_A);
        } else {
            if (startNumber.ordinal() >= _J.ordinal()) return null;
            int cnt = 0;
            for (CardNumber cn : values()) {
                if ( cnt > 0 ) {
                    cnt--;
                    list.add(cn);
                }
                if ( cn == startNumber) {
                    cnt = FullySelectedHand.NUM_CARDS-1;
                }
            }
        }
        return list;
    }

    public static List<CardNumber> getNumbers(List<Card> cards) {
        List<CardNumber> ln = new ArrayList<>();
        for (Card c : cards) {
            ln.add(c.getNumber());
        }

        return ln;
    }

    public String getDrawText() {
        if ( this != _T) {
            return ""+CARD_NUMBERS.charAt(this.ordinal());
        } else return "10";
    }

    public class CardNumberComparator implements java.util.Comparator<CardNumber> {
        @Override
        public int compare(CardNumber lhs, CardNumber rhs) {
            return lhs.ordinal() - rhs.ordinal();
        }
    }
}
