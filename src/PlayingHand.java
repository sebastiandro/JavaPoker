import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PlayingHand implements Cloneable {

    private ArrayList<Card> cards = new ArrayList<>();

    public PlayingHand() {

    }

    public PlayingHand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public PlayingHand(Set<Card> cards) {
        for (Card c: cards) {
            this.addCard(c);
        }
    }

    public void addCard(Card card) {
        if (this.cardAlreadyOnHand(card))
            throw new IllegalArgumentException("Card was already added to hand");
        this.cards.add(card);
    }

    public ArrayList<Card> getCardsSortedByDenomination() {
        ArrayList<Card> newCards = new ArrayList<>();

        for (Card c : getCards()) {
            newCards.add(c);
        }

        Collections.sort(newCards, new PlayingHandValueComparator());

        return newCards;
    }

    public ArrayList<Card> getCardsSortedByDenomination(boolean aceLow) {
        ArrayList<Card> newCards = new ArrayList<>();

        for (Card c : getCards()) {
            newCards.add(c);
        }

        if (aceLow) {
            Collections.sort(newCards, new PlayingHandValueComparator(aceLow));
        } else {
            Collections.sort(newCards, new PlayingHandValueComparator());
        }

        return newCards;
    }

    private void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getHandValue() {
        return PokerHandMatches.getHandValue(this);
    }

    public String getHandName() {

        if (PokerHandMatches.isRoyalFlush(this)) {
            return "Royal Straight Flush, - " + getLowCard().getReadableDenomination() +
                    " to " + getHighCard().getReadableDenomination() +
                    " of " + getHighCard().getReadableSuit();
        }

        if (PokerHandMatches.isStraightFlush(this)) {
            return "Royal Straight Flush, - " + getLowCard().getReadableDenomination() +
                    " to " + getHighCard().getReadableDenomination() +
                    " of " + getHighCard().getReadableSuit();
        }

        if (PokerHandMatches.isFourOfAKind(this)) {
            ArrayList<Card> affectedCards = PokerHandMatches.getAffectedCards(this);
            Card firstCard = affectedCards.get(0);
            return "Four of a Kind - " + firstCard.getReadableDenominationPlural();
        }

        if (PokerHandMatches.isFullHouse(this)) {
            ArrayList<Card> affectedCards = PokerHandMatches.getAffectedCards(this);
            ArrayList<String> denominations = new ArrayList<>();
            for (Card c : affectedCards) {
                if (denominations.indexOf(c.getReadableDenominationPlural()) == -1) {
                    denominations.add(c.getReadableDenominationPlural());
                }
            }
            return "Full House " + denominations.get(0) + " and " + denominations.get(1);
        }

        if (PokerHandMatches.isFlush(this)) {
            String cardsReadable = "";
            ArrayList<Card> cardsSorted = this.getCardsSortedByDenomination();
            for (Card c: cardsSorted) {

                if (cardsReadable.equals("")) {
                    cardsReadable += c.getReadableDenomination();
                    continue;
                }

                cardsReadable += ", " + c.getReadableDenomination();
            }
            cardsReadable += " of " + cardsSorted.get(0).getReadableSuit();
            return "Flush - " + cardsReadable;
        }

        if (PokerHandMatches.isStraight(this)) {
            return "Straight - " + getLowCard().getReadableDenomination() + " to " + getHighCard().getReadableDenomination();
        }

        if (PokerHandMatches.isThreeOfaKind(this) ) {
            ArrayList<Card> affectedCards = PokerHandMatches.getAffectedCards(this);
            return "Three of a Kind - " + affectedCards.get(0).getReadableDenominationPlural();
        }

        if (PokerHandMatches.isTwoPair(this)) {
            ArrayList<Card> affectedCards = PokerHandMatches.getAffectedCards(this);
            ArrayList<String> denominations = new ArrayList<>();
            for (Card c : affectedCards) {
                if (denominations.indexOf(c.getReadableDenominationPlural()) == -1) {
                    denominations.add(c.getReadableDenominationPlural());
                }
            }
            return "Two Pair - " + denominations.get(0) + " and " + denominations.get(1);
        }

        if (PokerHandMatches.isPair(this)) {
            ArrayList<Card> affectedCards = PokerHandMatches.getAffectedCards(this);

            return "Pair of " + affectedCards.get(0).getReadableDenominationPlural();
        }

        Card kicker = this.getHighCard();
        return "High Card " + kicker.getReadableDenomination() + " of " + kicker.getReadableSuit();
    }

    public int getHighestCardValue() {
        int highestValue = 0;

        for ( Card c : this.cards ) {
            if (highestValue == 0) {
                highestValue = c.getHighestValue();
            } else if ( c.getHighestValue() > highestValue ) {
                highestValue = c.getHighestValue();
            }
        }

        return highestValue;
    }

    public Card getHighCard() {
        ArrayList<Card> sortedCards = this.getCardsSortedByDenomination();
        return sortedCards.get(sortedCards.size() -1);
    }

    public Card getLowCard() {
        ArrayList<Card> sortedCards = this.getCardsSortedByDenomination();
        return sortedCards.get(0);
    }

    public int getTotalHandScore() {
        int totalScore = 0;
        for(Card c: this.cards) {
            totalScore += c.getHighestValue();
        }
        return totalScore;
    }

    public int numberOfAces() {
        int numberOfAces = 0;
        for (Card c : this.cards) {
            if ( c.getDenomination() == Card.Denomination.ACE ) {
                numberOfAces++;
            }
        }
        return numberOfAces;
    }

    public PlayingHand getBestHandFromCombination(ArrayList<Card> otherCards) {

        ArrayList<Card> allCards = new ArrayList<>(this.cards);
        allCards.addAll(otherCards);

        // if the sum of all cards is <=5 we return one hand
        if (allCards.size() <= 5) {
            return new PlayingHand(allCards);
        }

        // if not we try a combination for every remaining card
        Set<Card> cardSet = new HashSet<Card>();
        for (Card c: allCards) {
            cardSet.add(c);
        }

        Set<Set<Card>> combinationsOfCards = Utils.powerSetsOf(cardSet, 5);

        PlayingHand bestHand = null;

        for (Set<Card> combination : combinationsOfCards) {
            PlayingHand newHand = new PlayingHand(combination);
            if (bestHand == null) {
                bestHand = newHand;
            } else {
                if (PokerHandMatches.getHandValue(newHand) > PokerHandMatches.getHandValue(bestHand)) {
                    bestHand = newHand;
                }
            }
        }

        return bestHand;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    private boolean cardAlreadyOnHand(Card c) {
        return this.cards.indexOf(c) != -1;
    }

}
