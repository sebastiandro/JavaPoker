import java.util.ArrayList;
import java.util.List;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PokerHandMatches {

    public static int getHandValue(PlayingHand hand) {
        int score = hand.getTotalHandScore();

        if (isRoyalFlush(hand)) {
            return score + 10000;
        }

        if (isStraightFlush(hand)) {
            return score + 9000;
        }

        if (isFourOfAKind(hand)) {
            return score + 8000;
        }

        if (isFullHouse(hand)) {
            return score + 7000;
        }

        if (isFlush(hand)) {
            return score + 6000;
        }

        if (isStraight(hand)) {
            return score + 5000;
        }

        if (isThreeOfaKind(hand) ) {
            return score + 4000;
        }

        if (isTwoPair(hand)) {
            return score + 3000;
        }

        if (isPair(hand)) {
            return score + 2000;
        }

        return score;
    }

    public static boolean isRoyalFlush(PlayingHand hand) {
        return isStraightFlush(hand) && hand.getHighestCardValue() == 14;
    }

    public static boolean isStraightFlush(PlayingHand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    public static boolean isFourOfAKind(PlayingHand hand) {
        for(Card c : hand.getCards()) {
            int cardMatches = PokerHandMatches.matchesForCard(c, hand.getCards());
            if (cardMatches == 4) {
                return true;
            }
        }

        return false;
    }

    public static boolean isFullHouse(PlayingHand hand) {
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for ( Card c : hand.getCards() ) {

            int matches = PokerHandMatches.matchesForCard(c, hand.getCards());

            if ( matches == 3 ) {
                hasThreeOfAKind = true;
            }

            if ( matches == 2 ) {
                hasPair = true;
            }

        }

        return hasThreeOfAKind && hasPair;
    }

    public static boolean isFlush(PlayingHand hand) {
        Card.Suit currentSuit = null;

        for(Card c : hand.getCards()) {
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

    public static boolean isStraight(PlayingHand hand) {
        List<Card> cardsSortedLowAce = hand.getCardsSortedByDenomination(true);

        // If we have an Ace, we'll try straight with the ace value of 1
        if ( hand.numberOfAces() == 1 ) {

            int lastValue = 0;
            boolean isStraight = true;

            for (Card c : cardsSortedLowAce) {

                if(lastValue == 0) {
                    lastValue = c.getValue(); // get value returns 1 for ace
                    continue;
                }

                if ( c.getValue() - lastValue != 1 ) {
                    isStraight = false;
                    break;
                }
                lastValue = c.getValue();
            }

            if (isStraight) return true;

        }


        List<Card> cardsSortedHighAce = hand.getCardsSortedByDenomination();
        int lastValue = 0;

        for ( Card c : cardsSortedHighAce ) {
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

    public static boolean isThreeOfaKind(PlayingHand hand) {
        for ( Card c : hand.getCards() ) {
            if ( PokerHandMatches.matchesForCard(c, hand.getCards()) == 3 ) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTwoPair(PlayingHand hand) {
        int numberOfCardsWithMatches = 0;

        for ( Card c : hand.getCards() ) {
            if ( PokerHandMatches.matchesForCard(c, hand.getCards()) == 2 ) {
                numberOfCardsWithMatches++;
            }
        }

        if ( numberOfCardsWithMatches == 4 ) {
            return true;
        }

        return false;
    }

    public static boolean isPair(PlayingHand hand) {
        int numberOfCardsWithMatches = 0;

        for ( Card c : hand.getCards() ) {
            if ( PokerHandMatches.matchesForCard(c, hand.getCards()) == 2 ) {
                numberOfCardsWithMatches++;
            }
        }

        if ( numberOfCardsWithMatches == 2 ) {
            return true;
        }

        return false;
    }

    public static int matchesForCard(Card card, List<Card> cards) {

        int matches = 0;

        for ( Card c : cards ) {
            if ( card.compareTo(c) == 0 ) {
                matches++;
            }
        }

        return matches;
    }

}
