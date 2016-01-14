import org.junit.Test;

import static org.junit.Assert.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-12
 * Project: Poker
 */
public class PokerHandMatchesTest {

    public PokerHandMatchesTest() {

    }

    private PlayingHand getRoyalFlush() {
        PlayingHand newHand = new PlayingHand();
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.JACK, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));

        return newHand;
    }

    @Test
    public void testGetHandValue() throws Exception {
        PlayingHand royalFlush = getRoyalFlush();

        PokerHandMatches phm = new PokerHandMatches(getRoyalFlush());
        assertTrue(phm.getHandValue() == 10014);

    }

    @Test
    public void testIsRoyalFlush() throws Exception {
        PokerHandMatches phm = new PokerHandMatches(getRoyalFlush());
        assertTrue("Royal Flush test failed with correct Hand", phm.isRoyalFlush());

        PlayingHand anotherHand = new PlayingHand();
        anotherHand.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.JACK, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(anotherHand);

        assertFalse("Royal Flush check failed with incorrect hand", phm2.isRoyalFlush());

    }

    @Test
    public void testIsStraightFlush() throws Exception {
        PlayingHand anotherHand = new PlayingHand();

        anotherHand.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.JACK, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        anotherHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(anotherHand);

        assertTrue("Straight Flush check failed with correct hand", phm2.isStraightFlush());

        PlayingHand anotherHand2 = new PlayingHand();

        anotherHand2.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        anotherHand2.addCard(new Card(Card.Denomination.TWO, Card.Suit.HEARTS));
        anotherHand2.addCard(new Card(Card.Denomination.TWO, Card.Suit.HEARTS));
        anotherHand2.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        anotherHand2.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));

        PokerHandMatches phm3 = new PokerHandMatches(anotherHand2);

        assertFalse("Straight Flush check failed with incorrect hand", phm3.isStraightFlush());
    }

    @Test
    public void testIsFourOfAKind() throws Exception {

    }

    @Test
    public void testIsFullHouse() throws Exception {

    }

    @Test
    public void testIsFlush() throws Exception {
        PlayingHand newHand = new PlayingHand();
        newHand.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.JACK, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));

        PokerHandMatches phm = new PokerHandMatches(newHand);

        assertTrue("Flush check failed", phm.isFlush());

    }

    @Test
    public void testIsStraight() throws Exception {
        PlayingHand newHand = new PlayingHand();

        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.JACK, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));

        PokerHandMatches phm = new PokerHandMatches(newHand);

        assertTrue("Straight check failed for Ace High straight", phm.isStraight());

        PlayingHand newHand2 = new PlayingHand();

        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.TWO, Card.Suit.DIAMONDS));
        newHand.addCard(new Card(Card.Denomination.FIVE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.THREE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(newHand2);

        assertTrue("Straight check failed for Ace High straight", phm2.isStraight());
    }

    @Test
    public void testIsThreeOfaKind() throws Exception {
        PlayingHand newHand = new PlayingHand();

        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.DIAMONDS));
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.THREE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(newHand);

        assertTrue("Three of a Kind failed for correct hand", phm2.isThreeOfaKind());
    }

    @Test
    public void testIsTwoPair() throws Exception {
        PlayingHand newHand = new PlayingHand();

        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.DIAMONDS));
        newHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(newHand);

        assertTrue("Two Pair failed for correct hand", phm2.isTwoPair());
    }

    @Test
    public void testIsPair() throws Exception {
        PlayingHand newHand = new PlayingHand();

        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.DIAMONDS));
        newHand.addCard(new Card(Card.Denomination.EIGHT, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(newHand);

        assertTrue("Pair failed for correct hand", phm2.isPair());
    }

    @Test
    public void testMatchesForCard() throws Exception {
        PlayingHand newHand = new PlayingHand();

        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.DIAMONDS));
        newHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.NINE, Card.Suit.SPADES));
        newHand.addCard(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));

        PokerHandMatches phm2 = new PokerHandMatches(newHand);

        assertTrue("Pair failed for correct hand", phm2.isPair());
    }
}