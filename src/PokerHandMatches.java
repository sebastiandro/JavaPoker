import java.util.List;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PokerHandMatches {

    PlayingHand hand;


    public PokerHandMatches(PlayingHand hand) {
        this.hand = hand;
    }

    public int getHandValue() {

        int score = hand.getHighestCardValue();

        if ( isRoyalFlush() ) {
            return score + 10000;
        }

        if ( isStraightFlush() ) {
            return score + 9000;
        }

        if ( isFourOfAKind() ) {
            return score + 8000;
        }

        if ( isFullHouse() ) {
            return score + 7000;
        }

        if ( isFlush() ) {
            return score + 6000;
        }

        if ( isStraight() ) {
            return score + 5000;
        }

        if ( isThreeOfaKind() ) {
            return score + 4000;
        }

        if ( isTwoPair() ) {
            return score + 3000;
        }

        if ( isPair() ) {
            return score + 2000;
        }

        return score;
    }

    public boolean isRoyalFlush() {
        return isStraightFlush() && this.hand.getHighestCardValue() == 14;
    }

    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    public boolean isFourOfAKind() {

        int differentDenominations = 0;
        Card lastCard = null;

        for(Card c : this.hand.getCards()) {
            if (lastCard == null) {
                lastCard = c;
            } else {
                if (c.getValue() != lastCard.getValue()) {
                    differentDenominations++;
                }
            }
        }

        if (differentDenominations > 2) {
            return false;
        }

        return true;
    }

    public boolean isFullHouse() {

        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for ( Card c : this.hand.getCards() ) {

            int matches = this.matchesForCard(c, this.hand.getCards());

            if ( matches == 3 ) {
                hasThreeOfAKind = true;
            }

            if ( matches == 2 ) {
                hasPair = true;
            }

        }

        return hasThreeOfAKind && hasPair;
    }

    public boolean isFlush() {

        Card.Suit currentSuit = null;

        for(Card c : this.hand.getCards()) {
            if (currentSuit == null) {
                currentSuit = c.getSuit();
            } else {
                if ( c.getSuit() != currentSuit  ) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isStraight() {

        List<Card> cardsSorted = this.hand.getCardsSortedByDenomination();

        // If we have an Ace, we'll try straight with the ace value of 1
        if ( this.hand.numberOfAces() == 1 ) {

            int lastValue = 0;


            for (Card c : cardsSorted) {
                if(lastValue == 0) {
                    lastValue = c.getDenomination().getValue(); // get value returns 1 for ace
                    continue;
                }

                if ( c.getDenomination().getValue() - lastValue != 1 ) {
                    break;
                }
                lastValue = c.getDenomination().getValue();
            }

        }

        int lastValue = 0;

        for ( Card c : cardsSorted ) {
            if(lastValue == 0) {
                lastValue = c.getDenomination().getHighestValue();
                continue;
            }
            if ( c.getDenomination().getHighestValue() - lastValue != 1 ) {
                return false;
            }
            lastValue = c.getDenomination().getHighestValue();
        }


        return true;
    }

    public boolean isThreeOfaKind() {
        for ( Card c : this.hand.getCards() ) {
            if ( this.matchesForCard(c, this.hand.getCards()) == 3 ) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair() {

        int numberOfCardsWithMatches = 0;

        for ( Card c : this.hand.getCards() ) {
            if ( this.matchesForCard(c, this.hand.getCards()) == 2 ) {
                numberOfCardsWithMatches++;
            }
        }

        if ( numberOfCardsWithMatches == 4 ) {
            return true;
        }

        return false;
    }

    public boolean isPair() {

        int numberOfCardsWithMatches = 0;

        for ( Card c : this.hand.getCards() ) {
            if ( this.matchesForCard(c, this.hand.getCards()) == 2 ) {
                numberOfCardsWithMatches++;
            }
        }

        if ( numberOfCardsWithMatches == 2 ) {
            return true;
        }

        return false;
    }

    public int matchesForCard(Card card, List<Card> cards) {

        int matches = 0;

        for ( Card c : cards ) {
            if ( card.compareTo(c) == 0 ) {
                matches++;
            }
        }

        return matches;
    }
}
