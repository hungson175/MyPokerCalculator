package calculator.poker.vn.mobile.dtd.mypokercalculator;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hungson175 on 6/28/2016.
 */
public class KnownCards {
    private List<Card> allCards;
    List<Card> handX;
    List<Card> handY;
    List<Card> rCards;

    public KnownCards(List<Card> handX, List<Card> handY, List<Card> rCards) {
        this.handX = handX;
        this.handY = handY;
        this.rCards = rCards;
        if ( handX.size() != 0 && handX.size() != 2) throw new InvalidParameterException("First hand must contain 0 or 2 cards");
        if ( handY.size() != 0 && handY.size() != 2) throw new InvalidParameterException("Second hand must contain 0 or 2 cards");
        if ( handX.size() + handY.size() == 0 )  throw new InvalidParameterException("Must know one of 2 player cards");
        if ( rCards.size() > 5) throw new InvalidParameterException("Pls dont select more than 5 community cards");
        this.allCards = Helpers.merge(handX, Helpers.merge(handY, rCards));
        HashSet<Card> temp = new HashSet<>(allCards);
        if (temp.size() != allCards.size())
            throw new InvalidParameterException("Duplicated cards");
    }

    public List<Card> getXCards() {
        return handX;
    }

    public List<Card> getYCards() {
        return handY;
    }

    public List<Card> getRevealedByUserCards() {
        return rCards;
    }

    public List<Card> getAllCards() {
        return this.allCards;
    }

    public int size() {
        return allCards.size();
    }
}
