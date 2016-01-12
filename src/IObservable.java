import java.beans.PropertyChangeListener;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public interface IObservable {
    void addObserver(PropertyChangeListener observer);

    void removeObserver(PropertyChangeListener observer);
}
