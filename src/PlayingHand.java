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
        System.out.println("Adding card: " + card);
    }

    public ArrayList<Card> getCardsSortedByDenomination() {
        ArrayList<Card> newCards = new ArrayList<>();
        Collections.sort(newCards, new PlayingHandValueComparator());

        for (Card c : getCards()) {
            newCards.add(c);
        }
        return newCards;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
