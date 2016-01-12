/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public interface GameModel extends IObservable {

    void start();
    void stop();
    boolean isRunning();
    void startRound();
    void endRound();

}
