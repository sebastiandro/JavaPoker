import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-16
 * Project: Poker
 */
public class PlayingHandTest {

    @Test
    public void testGetBestHandFromCombination() throws Exception {
        ArrayList<Card> communityCards = new ArrayList<>();
        
        communityCards.add(new Card(Card.Denomination.ACE, Card.Suit.HEARTS));
        communityCards.add(new Card(Card.Denomination.TWO, Card.Suit.HEARTS));
        communityCards.add(new Card(Card.Denomination.KING, Card.Suit.SPADES));
        communityCards.add(new Card(Card.Denomination.FOUR, Card.Suit.HEARTS));
        communityCards.add(new Card(Card.Denomination.SIX, Card.Suit.HEARTS));

        PlayingHand playerHand = new PlayingHand();

        playerHand.addCard(new Card(Card.Denomination.ACE, Card.Suit.SPADES));
        playerHand.addCard(new Card(Card.Denomination.SIX, Card.Suit.DIAMONDS));

        PlayingHand bestHand = playerHand.getBestHandFromCombination(communityCards);

        System.out.println(bestHand.getHandName());
    }
}