import java.util.Stack;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public interface Deck {

    void shuffle();
    void fillDeck(int numberOfCards);
    Card drawCard();
    Stack<Card> getCards();

}
