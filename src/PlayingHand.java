import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PlayingHand implements Hand, Cloneable {

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

    public int getHighestCardValue() {
        int highestValue = 0;

        for ( Card c : this.cards ) {
            if (highestValue == 0) {
                highestValue = c.getValue();
            } else if ( c.getValue() > highestValue ) {
                highestValue = c.getValue();
            }
        }

        return highestValue;
    }

    public int numberOfAces() {
        int numberOfAces = 0;
        for (Card c : this.cards) {
            if ( c.getDenomination() == Card.Denomination.ACE ) {
                numberOfAces++;
            }
        }
        return numberOfAces;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public ArrayList<Card> getCardsCloned() {
        ArrayList<Card> newCards = new ArrayList<>();

        for (Card c : getCards()) {
            newCards.add(c);
        }
        return newCards;
    }

}
