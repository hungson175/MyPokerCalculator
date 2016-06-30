package calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.Card;
import calculator.poker.vn.mobile.dtd.mypokercalculator.CardNumber;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullySelectedHand;
import calculator.poker.vn.mobile.dtd.mypokercalculator.FullyVisibleHand;

/**
 * Created by hungson175 on 24/06/2016.
 */
public class FourOfKind extends  HandType {
    private CardNumber fokCardNumber;
    private Card highCard;

    public FourOfKind(FullyVisibleHand fullyVisibleHand) {
        super(fullyVisibleHand);
        for (Card card : fullyVisibleHand.getCards()) {
            FullySelectedHand fokHand = getFOKHandWith(card,fullyVisibleHand);
            if ( fokHand != null) {
                this.selectedHand = fokHand;
                break;
            }
        }
    }

    private FullySelectedHand getFOKHandWith(Card targetCard, FullyVisibleHand fullyVisibleHand) {
        CardNumber targetCardNumber = targetCard.getNumber();
        int cnt = 0;
        List<Card> choosenCards = new ArrayList<>();
        for (Card card : fullyVisibleHand.getCards()) {
            CardNumber cn = card.getNumber();
            if ( cn == targetCardNumber ) {
                cnt++;
                choosenCards.add(card);
            }
        }

        if ( cnt < 4 )
            return null;
        else this.fokCardNumber = targetCardNumber;
        Assert.assertEquals(4,cnt);
        Card highCard = null;
        for (Card card : fullyVisibleHand.getCards()) {
            if ( card.getNumber() != targetCardNumber ) {
                if (card.getNumber() == CardNumber._A) {
                    highCard = card;
                    break;
                } else {
                    if ( highCard == null || card.getNumber().ordinal() > highCard.getNumber().ordinal()) {
                        highCard = card;
                    }
                }
            }
        }
        this.highCard = highCard;
        choosenCards.add(highCard);

        return new FullySelectedHand(choosenCards);
    }

    @Override
    protected boolean isSatisfiedAsType() {
        return this.selectedHand != null;
    }

    @Override
    protected int compareSameRank(HandType t) {
        CardNumber thisFOKCN = this.getFokCardNumber();
        FourOfKind oppHand = (FourOfKind) t;
        CardNumber oppFOKCN = oppHand.getFokCardNumber();
        if (thisFOKCN == CardNumber._A) return 1;
        if (oppFOKCN == CardNumber._A) return -1;

        if (thisFOKCN.ordinal() < oppFOKCN.ordinal()) return -1;
        else if (thisFOKCN.ordinal() > oppFOKCN.ordinal()) return 1;
        return this.getHighCard().compareTo(oppHand.getHighCard());
    }

    @Override
    public HandRanking getRanking() {
        return HandRanking.FOUR_OF_KIND;
    }

    public CardNumber getFokCardNumber() {
        return fokCardNumber;
    }

    public Card getHighCard() {
        return highCard;
    }
}
