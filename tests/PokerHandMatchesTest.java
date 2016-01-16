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

    private PlayingHand getStraightFlush() {
        PlayingHand straightFlush = new PlayingHand();
        straightFlush.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        straightFlush.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));
        straightFlush.addCard(new Card(Card.Denomination.JACK, Card.Suit.HEARTS));
        straightFlush.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        straightFlush.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));

        return straightFlush;
    }

    private PlayingHand getFourOfAKind() {
        PlayingHand fourOfKind = new PlayingHand();
        fourOfKind.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        fourOfKind.addCard(new Card(Card.Denomination.KING, Card.Suit.SPADES));
        fourOfKind.addCard(new Card(Card.Denomination.KING, Card.Suit.DIAMONDS));
        fourOfKind.addCard(new Card(Card.Denomination.KING, Card.Suit.CLUBS));
        fourOfKind.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));

        return fourOfKind;
    }

    private PlayingHand getFullHouse() {
        PlayingHand fullHouse = new PlayingHand();
        fullHouse.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        fullHouse.addCard(new Card(Card.Denomination.KING, Card.Suit.SPADES));
        fullHouse.addCard(new Card(Card.Denomination.KING, Card.Suit.DIAMONDS));
        fullHouse.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.CLUBS));
        fullHouse.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));

        return fullHouse;
    }

    private PlayingHand getFlush() {
        PlayingHand flush = new PlayingHand();
        flush.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        flush.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.HEARTS));
        flush.addCard(new Card(Card.Denomination.NINE, Card.Suit.HEARTS));
        flush.addCard(new Card(Card.Denomination.TWO, Card.Suit.HEARTS));
        flush.addCard(new Card(Card.Denomination.FIVE, Card.Suit.HEARTS));

        return flush;
    }

    private PlayingHand getStraightLowAce() {
        PlayingHand straight = new PlayingHand();
        straight.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        straight.addCard(new Card(Card.Denomination.TWO, Card.Suit.SPADES));
        straight.addCard(new Card(Card.Denomination.THREE, Card.Suit.DIAMONDS));
        straight.addCard(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));
        straight.addCard(new Card(Card.Denomination.FIVE, Card.Suit.HEARTS));

        return straight;
    }

    private PlayingHand getStraightHighAce() {
        PlayingHand straight = new PlayingHand();
        straight.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        straight.addCard(new Card(Card.Denomination.JACK, Card.Suit.SPADES));
        straight.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.DIAMONDS));
        straight.addCard(new Card(Card.Denomination.KING, Card.Suit.HEARTS));
        straight.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));

        return straight;
    }

    private PlayingHand getThreeOfAKind() {
        PlayingHand threeOfAKind = new PlayingHand();
        threeOfAKind.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        threeOfAKind.addCard(new Card(Card.Denomination.TEN, Card.Suit.CLUBS));
        threeOfAKind.addCard(new Card(Card.Denomination.TEN, Card.Suit.DIAMONDS));
        threeOfAKind.addCard(new Card(Card.Denomination.KING, Card.Suit.SPADES));
        threeOfAKind.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));

        return threeOfAKind;
    }

    private PlayingHand getTwoPair() {
        PlayingHand twoPair = new PlayingHand();
        twoPair.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        twoPair.addCard(new Card(Card.Denomination.TEN, Card.Suit.CLUBS));
        twoPair.addCard(new Card(Card.Denomination.KING, Card.Suit.DIAMONDS));
        twoPair.addCard(new Card(Card.Denomination.KING, Card.Suit.SPADES));
        twoPair.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));

        return twoPair;
    }

    private PlayingHand getPair() {
        PlayingHand pair = new PlayingHand();
        pair.addCard(new Card(Card.Denomination.TEN, Card.Suit.HEARTS));
        pair.addCard(new Card(Card.Denomination.TEN, Card.Suit.CLUBS));
        pair.addCard(new Card(Card.Denomination.QUEEN, Card.Suit.DIAMONDS));
        pair.addCard(new Card(Card.Denomination.KING, Card.Suit.SPADES));
        pair.addCard(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));

        return pair;
    }


    @Test
    public void testIsRoyalFlush() throws Exception {
        assertTrue("Royal Flush test failed with correct Hand", PokerHandMatches.isRoyalFlush(getRoyalFlush()));
        assertFalse("Royal Flush check failed with incorrect hand", PokerHandMatches.isRoyalFlush(getStraightFlush()));
    }

    @Test
    public void testIsStraightFlush() throws Exception {
        PlayingHand straightFlush = getStraightFlush();
        assertTrue("Straight Flush check failed with correct hand", PokerHandMatches.isStraightFlush(straightFlush));

        PlayingHand straight = getStraightLowAce();
        assertFalse("Straight Flush check failed with incorrect hand", PokerHandMatches.isStraightFlush(straight));
    }

    @Test
    public void testIsFourOfAKind() throws Exception {
        PlayingHand fourOfAKind = getFourOfAKind();
        assertTrue("Four of a kind check failed with correct hand", PokerHandMatches.isFourOfAKind(fourOfAKind));

        PlayingHand threeOfAKind = getThreeOfAKind();
        assertFalse("Four of a kind check failed with incorrect hand", PokerHandMatches.isFourOfAKind(threeOfAKind));
    }

    @Test
    public void testIsFullHouse() throws Exception {
        PlayingHand fullHouse = getFullHouse();
        assertTrue("Full house check failed with correct hand", PokerHandMatches.isFullHouse(fullHouse));

        PlayingHand threeOfAKind = getThreeOfAKind();
        assertFalse("Full house check failed with incorrect hand", PokerHandMatches.isFullHouse(threeOfAKind));
    }

    @Test
    public void testIsFlush() throws Exception {
        PlayingHand flush = getFlush();
        assertTrue("Flush check failed", PokerHandMatches.isFlush(flush));

        PlayingHand straight = getStraightHighAce();
        assertFalse("Flush check failed for incorrect hand", PokerHandMatches.isFlush(straight));
    }

    @Test
    public void testIsStraight() throws Exception {
        PlayingHand straightLowAce = getStraightLowAce();
        assertTrue("Straight check failed for Ace Low straight", PokerHandMatches.isStraight(straightLowAce));

        PlayingHand straightHighAce = getStraightHighAce();
        assertTrue("Straight check failed for Ace High straight", PokerHandMatches.isStraight(straightHighAce));
    }

    @Test
    public void testIsThreeOfaKind() throws Exception {
        PlayingHand threeOfAKind = getThreeOfAKind();
        assertTrue("Three of a Kind failed for correct hand", PokerHandMatches.isThreeOfaKind(threeOfAKind));
    }

    @Test
    public void testIsTwoPair() throws Exception {
        PlayingHand twoPair = getTwoPair();
        assertTrue("Two Pair failed for correct hand", PokerHandMatches.isTwoPair(twoPair));
        assertFalse("Two Pair failed for incorrect hand", PokerHandMatches.isTwoPair(getThreeOfAKind()));
    }

    @Test
    public void testIsPair() throws Exception {
        PlayingHand pair = getPair();
        assertTrue("Pair failed for correct hand", PokerHandMatches.isPair(pair));
        assertFalse("Pair failed for correct hand", PokerHandMatches.isPair(getStraightHighAce()));
    }

    @Test
    public void testGetHandValue() throws Exception {
        assertTrue(getRoyalFlush().getHandValue() == 10000 + getRoyalFlush().getHighestCardValue());
        assertTrue(getStraightFlush().getHandValue() == 9000 + getStraightFlush().getHighestCardValue());
        assertTrue(getFourOfAKind().getHandValue() == 8000 + getFourOfAKind().getHighestCardValue());
        assertTrue(getFullHouse().getHandValue() == 7000 + getFullHouse().getHighestCardValue());
        assertTrue(getFlush().getHandValue() == 6000 + getFlush().getHighestCardValue());
        assertTrue(getStraightHighAce().getHandValue() == 5000 + getStraightHighAce().getHighestCardValue());
        assertTrue(getThreeOfAKind().getHandValue() == 4000 + getThreeOfAKind().getHighestCardValue());
        assertTrue(getTwoPair().getHandValue() == 3000 + getTwoPair().getHighestCardValue());
        assertTrue(getPair().getHandValue() == 2000 + getPair().getHighestCardValue());
    }
}