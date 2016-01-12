import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PokerHandValues implements HandValueCalculator {

    PlayingHand hand;


    public PokerHandValues(PlayingHand hand) {
        this.hand = hand;
    }

    public int getHandValue(Hand hand) {
        List<Card> cards = hand.getCards();
        return 0;
    }

    private boolean isRoyalFlush() {
        for ( Card c : this.hand.getCards() ) {

        }
        return true;
    }

    private boolean isFlush() {

        Card.Denomination currentDenomination = null;

        for(Card c : this.hand.getCards()) {
            if (currentDenomination == null) {
                currentDenomination = c.getDenomination();
            } else {
                if ( c.getDenomination() != currentDenomination ) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isStraight() {
        List<Card> cardsSorted = this.hand.getCardsSortedByDenomination();
        return false;
    }
}
