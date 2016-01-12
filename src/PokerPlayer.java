import java.util.ArrayList;
import java.util.List;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerPlayer implements Player {
    final int MAX_CARDS_ON_HAND = 2;
    String name;
    List<Card> hand = new ArrayList<>();
    double cash;

    public PokerPlayer(String name, double cash) {
        this.name = name;
        this.cash = cash;
    }

    public void addMoney(double amount) {
        this.cash += amount;
    }
    public void deductMoney(double amount) {
        if (this.cash - amount < 0)
            throw new IllegalArgumentException();

        this.cash -= amount;
    }

    public void addCardToHand(Card c) {
        assert hand.size() < MAX_CARDS_ON_HAND;
        if (hand.size() >= MAX_CARDS_ON_HAND)
            throw new IllegalStateException("Player already has two cards");
        hand.add(c);
    }

    public void throwAllCards() {
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public double getCash() {
        return this.cash;
    }

    @Override
    public String toString() {
        return name + " - " + cash;
    }

}
