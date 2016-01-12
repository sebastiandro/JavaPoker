import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerGameModel implements GameModel {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    Set<Player> players = new HashSet<>();
    PokerRound currentPokerRound;
    private boolean isRunning;


    public PokerGameModel() {
    }

    public void start() {
        this.isRunning = true;
    }

    public void beginNewRound() {
        this.currentPokerRound = new PokerRound(this.players);
    }

    public void addPlayer(String name, double cash) {
        this.players.add(new PokerPlayer(name, cash));
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void stop() {
        this.isRunning = false;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void startRound() {
        this.currentPokerRound = new PokerRound(this.players);
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

}
