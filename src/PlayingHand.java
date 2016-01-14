import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PlayingHand implements Hand, Cloneable {

    private ArrayList<Card> cards;

    public PlayingHand() {
        this.cards = new ArrayList<>();
    }

    public PlayingHand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public PlayingHand(Set<Card> cards) {
        this.cards = new ArrayList<>();
        for (Card c: cards) {
            this.cards.add(c);
        }
    }

    public void addCard(Card card) {
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

    private void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getHandValue() {
        PokerHandMatches phs = new PokerHandMatches(this);
        return phs.getHandValue();
    }

    public int getHighestCardValue() {
        int highestValue = 0;

        for ( Card c : this.cards ) {
            if (highestValue == 0) {
                highestValue = c.getValue();
            } else if ( c.getValue() > highestValue ) {
                highestValue = c.getValue();
            }
        }

        return highestValue;
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
                PokerHandMatches phsNewHand = new PokerHandMatches(newHand);
                PokerHandMatches phsBestHand = new PokerHandMatches(bestHand);
                if (phsNewHand.getHandValue() > phsBestHand.getHandValue()) {
                    bestHand = newHand;
                }
            }
        }

        return bestHand;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public ArrayList<Card> getCardsCloned() {
        ArrayList<Card> newCards = new ArrayList<>();

        for (Card c : getCards()) {
            newCards.add(c);
        }
        return newCards;
    }

}
