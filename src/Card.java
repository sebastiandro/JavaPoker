import java.util.Comparator;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class Card implements Comparable<Card> {
    public enum Denomination {

        ACE(1, 14, "Ace", "Aces"),
        TWO(2, "Two", "Twos"),
        THREE(3, "Three", "Threes"),
        FOUR(4, "Four", "Fours"),
        FIVE(5, "Five", "Fives"),
        SIX(6, "Six", "Sixes"),
        SEVEN(7, "Seven", "Sevens"),
        EIGHT(8, "Eight", "Eights"),
        NINE(9, "Nine", "Nines"),
        TEN(10, "Ten", "Tens"),
        JACK(11, "Jack", "Jacks"),
        QUEEN(12, "Queen", "Queens"),
        KING(13, "King", "Kings");

        private int value;
        private int secondValue;
        private String readingName;
        private String pluralName;

        Denomination(int value, String readingName, String pluralName) {
            this.value = value;
            this.readingName = readingName;
            this.pluralName = pluralName;
            this.secondValue = 0;
        }

        Denomination(int value, int secondValue, String readingName, String pluralName) {
            this.value = value;
            this.secondValue = secondValue;
            this.readingName = readingName;
            this.pluralName = pluralName;
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

        String getReadingName() {
            return this.readingName;
        }

        String getPluralName() {
            return this.pluralName;
        }

        boolean hasSecondValue() {
            return this.secondValue != 0;
        }
    }

    public enum Suit {
        SPADES("Spades"),
        HEARTS("Hearts"),
        DIAMONDS("Diamonds"),
        CLUBS("Clubs");

        private String readingName;

        Suit(String readingName) {
            this.readingName = readingName;
        }

        public String getReadingName() {
            return this.readingName;
        }

    }

    private Denomination d;
    private Suit s;
    private boolean visible;

    public Card(Denomination d, Suit s) {
        this.d = d;
        this.s = s;
    }

    public String getReadableDenomination() {
        return this.d.getReadingName();
    }

    public String getReadableDenominationPlural() {
        return this.d.getPluralName();
    }

    public String getReadableSuit() {
        return this.s.getReadingName();
    }

    public int getValue() {
        return this.d.getValue();
    }

    public int getSecondValue() {
        return this.getDenomination().secondValue;
    }

    public int getHighestValue() {
        return this.getDenomination().getHighestValue();
    }

    public boolean hasSecondValue() {
        return this.getDenomination().hasSecondValue();
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
        if ( this.getHighestValue() > c.getHighestValue() ) {
            return 1;
        }
        if ( this.getHighestValue() < c.getHighestValue() ) {
            return -1;
        }
        return 0;
    }

    public int compareTo(Card c, boolean useLowAce) {

        if (useLowAce) {

            if ( this.getValue() > c.getValue()) {
                return 1;
            }
            if ( this.getValue() < c.getValue() ) {
                return -1;
            }
            return 0;

        } else {
            return compareTo(c);
        }

    }

    @Override
    public String toString() {
        return this.d + " " + this.s;
    }
}
