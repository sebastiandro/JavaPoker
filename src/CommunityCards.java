import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class CommunityCards  {

    final int MAX_ALLOWED_CARDS = 5;
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        if ( cards.size() >= MAX_ALLOWED_CARDS )
            throw new IllegalStateException("Community cards can be max" + MAX_ALLOWED_CARDS);
        cards.add(card);
    }

    public List<Card> getCards() {
        if(cards.size() == 0)
            throw new IllegalStateException("You must add cards before getting any");
        return cards;
    }

}
