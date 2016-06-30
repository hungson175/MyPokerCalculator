package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import java.security.InvalidParameterException;

/**
 * Created by hungson175 on 20/06/2016.
 */
public enum CardType {
    H, D, S, C;

    private int swi;

    public static CardType parse(char typeChar) throws InvalidParameterException {
        try {
            return CardType.valueOf("" + Character.toUpperCase(typeChar));
        } catch (Exception e) {
            throw new InvalidParameterException("No such card type: " + typeChar);
        }

    }

    public int getImageResourceId() {
        switch (this) {
            case H:
                return R.drawable.heart;
            case D:
                return R.drawable.diamond;
            case S:
                return R.drawable.spade;
            case C:
                return R.drawable.club;
        }
        Assert.fail();
        return -1;
    }

    public int getColorResourceId() {
        switch (this) {
            case H:
            case D:
                return android.R.color.holo_red_dark;
            case S:
            case C:
                return android.R.color.black;
        }
        Assert.fail();
        return -1;
    }
}
