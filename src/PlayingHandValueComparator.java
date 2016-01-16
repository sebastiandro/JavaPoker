import java.util.Comparator;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PlayingHandValueComparator implements Comparator<Card> {

    private boolean useLowAce = false;

    PlayingHandValueComparator() {

    }

    PlayingHandValueComparator(boolean useLowAce) {
        this.useLowAce = useLowAce;
    }

    @Override
    public int compare(Card c1, Card c2) {
        if (this.useLowAce) {
            return c1.compareTo(c2, this.useLowAce);
        }
        return c1.compareTo(c2);
    }
}
