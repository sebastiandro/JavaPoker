import java.util.List;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class ActivePokerPlayer implements Player {
    private Player p;
    private PlayingHand hand = new PlayingHand();

    public ActivePokerPlayer(Player p) {
        this.p = p;
    }

    public PlayingHand getHand() {
        return hand;
    }

    public void addCard(Card c) {
        hand.addCard(c);
    }

    @Override
    public String getName() {
        return p.getName();
    }

    @Override
    public double getCash() {
        return p.getCash();
    }
}
