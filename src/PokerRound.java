import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerRound implements Round{
    private boolean isRunning = false;
    final int HAND_CARD_AMOUNT = 2;
    private Deck deck;
    Set<ActivePokerPlayer> activePlayers = new HashSet<>();

    public PokerRound(Set<Player> players) {
        addActivePlayers(players);
        this.deck = new PokerDeck();
    }

    public void giveOutCards() {
        for (int i = 0; i < HAND_CARD_AMOUNT; i++ ) {
            for (ActivePokerPlayer ap : this.activePlayers) {
                ap.addCard(deck.drawCard());
            }
        }
    }

    private void addActivePlayers(Set<Player> players) {
        for (Player p : players) {
            assert !activePlayers.contains(p);
            activePlayers.add(new ActivePokerPlayer(p));
        }
    }

    public Set<ActivePokerPlayer> getActivePlayers() {
        return this.activePlayers;
    }

    public void start() {
        this.isRunning = true;
    }

    public void end() {
        this.isRunning = false;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

}
