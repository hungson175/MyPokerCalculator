package calculator.poker.vn.mobile.dtd.mypokercalculator;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hungson175 on 6/28/2016.
 */
public class CardsGenerator {
    private List<Card> inDeckCards = new ArrayList<>();
    private Set<Card> dealtCards = new HashSet<>();

    public CardsGenerator(List<Card> dealtCards) {
        this.dealtCards = new HashSet<>(dealtCards);
        this.inDeckCards = findDeckCards();

    }

    private List<Card> findDeckCards() {
        List<Card> deckCards = new ArrayList<>();
        for (CardNumber cn : CardNumber.values()) {
            for (CardType ct : CardType.values()) {
                Card c = new Card(cn,ct);
                if ( ! dealtCards.contains(c))
                    deckCards.add(c);
            }
        }
        Assert.assertEquals(52-this.dealtCards.size(),deckCards.size());
        return deckCards;
    }

    public List<Card> nRandomCards(int n) {
        Assert.assertTrue( n <= inDeckCards.size());
        Collections.shuffle(inDeckCards);
        List<Card> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
            list.add(inDeckCards.get(i));
        return list;
    }

}
