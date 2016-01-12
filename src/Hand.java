import java.util.List;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public interface Hand {
    void addCard(Card c);
    List<Card> getCards();

}
