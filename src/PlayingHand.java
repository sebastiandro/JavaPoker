import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PlayingHand implements Hand {

    private ArrayList<Card> cards;

    public PlayingHand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public ArrayList<Card> getCardsSortedByDenomination() {
        ArrayList<Card> newCards = new ArrayList<>();

        for (Card c : getCards()) {
            newCards.add(c);
        }

        Collections.sort(newCards, new PlayingHandValueComparator());

        return newCards;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
