import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerDeck implements Deck {
    final public int NUMBER_OF_CARDS = 52;
    private Stack<Card> cards = new Stack<>();

    public PokerDeck() {
        this.fillDeck(NUMBER_OF_CARDS);
        this.shuffle();
    }

    public void burnCard() {
        this.cards.remove(0);
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(this.cards, new Random(seed));
    }

    public Card drawCard() {
        assert cards.size() > 0;
        if (cards.size() < 1)
            throw new EmptyStackException();
        return this.cards.pop();
    }

    public void fillDeck(int numberOfCards) {
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Denomination d : Card.Denomination.values()) {
                cards.add(new Card(d,s));
            }
        }
    }

    public Stack<Card> getCards() {
        return this.cards;
    }

}
