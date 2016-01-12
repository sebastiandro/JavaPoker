import java.util.Comparator;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class Card implements Comparable<Card> {
    public enum Denomination {
        ACE(1, 14),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13);

        private int value;
        private int secondValue;

        Denomination(int value) {
            this.value = value;
            this.secondValue = 0;
        }

        Denomination(int value, int secondValue) {
            this.value = value;
            this.secondValue = secondValue;
        }

        int getValue() {
            return value;
        }

        int getHighestValue() {
            if (this.hasSecondValue()) {
                return Math.max(this.value, this.secondValue);
            }
            return this.value;
        }

        int getSecondValue() {
            if (this.secondValue == 0)
                throw new IllegalStateException("Card does not have a second value");
            return this.secondValue;
        }

        boolean hasSecondValue() {
            return this.secondValue != 0;
        }
    }

    public enum Suit {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }

    private Denomination d;
    private Suit s;
    private boolean visible;

    public Card(Denomination d, Suit s) {
        this.d = d;
        this.s = s;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public Denomination getDenomination() {
        return this.d;
    }

    public Suit getSuit() {
        return this.s;
    }

    @Override
    public int compareTo(Card c) {
        if ( this.getDenomination().getHighestValue() > c.getDenomination().getHighestValue() ) {
            return 1;
        }
        if ( this.getDenomination().getHighestValue() < c.getDenomination().getHighestValue() ) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.d + " " + this.s;
    }
}
