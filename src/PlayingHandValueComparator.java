import java.util.Comparator;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PlayingHandValueComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        return c1.compareTo(c2);
    }
}
