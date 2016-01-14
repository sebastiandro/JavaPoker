import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerGameModel extends Thread implements GameModel {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    Set<Player> players = new HashSet<>();
    PokerRound currentPokerRound;
    private boolean isRunning;

    public void beginNewRound() {
        this.currentPokerRound = new PokerRound(this.players);
    }

    public void addPlayer(String name, double cash) {
        this.players.add(new PokerPlayer(name, cash));
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void startRound() {
        this.currentPokerRound = new PokerRound(this.players);
    }

    public PokerRound getCurrentPokerRound() {
        if ( this.currentPokerRound == null )
            throw new IllegalStateException();
        return this.currentPokerRound;
    }

    public void endRound() {
        this.currentPokerRound.end();
    }

    @Override
    public void addObserver(PropertyChangeListener observer) {
        this.pcs.addPropertyChangeListener(observer);
    }

    @Override
    public void removeObserver(PropertyChangeListener observer) {
        this.pcs.removePropertyChangeListener(observer);
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            startRound();
            this.currentPokerRound.start();

            while (this.currentPokerRound.isRunning()) {

            }
        }
    }
}
