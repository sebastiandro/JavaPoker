import com.sun.org.apache.xml.internal.security.Init;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerRound implements Round{
    private boolean isRunning = false;
    final int HAND_CARD_AMOUNT = 2;
    private PokerDeck deck;
    private ArrayList<Card> communityCards = new ArrayList<>();
    private RoundState currentState;
    private boolean readyToAdvance = false;
    Set<ActivePokerPlayer> activePlayers = new HashSet<>();

    public enum RoundState {
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }

    ArrayDeque<RoundState> roundStates = new ArrayDeque<RoundState>(4) {
        {
            add(RoundState.PREFLOP);
            add(RoundState.FLOP);
            add(RoundState.TURN);
            add(RoundState.RIVER);
        }
    };


    public PokerRound(Set<Player> players) {
        addActivePlayers(players);
        this.deck = new PokerDeck();
    }

    public void deal() {
        for (int i = 0; i < HAND_CARD_AMOUNT; i++ ) {
            for (ActivePokerPlayer ap : this.activePlayers) {
                ap.addCard(deck.drawCard());
            }
        }
    }

    public void handleRound() {
        if ( currentState == RoundState.PREFLOP ) {
            deal();

            for(ActivePokerPlayer p : activePlayers) {
                System.out.println("\n");
                System.out.println(p.getName());
                System.out.println(p.getHand().getCards());
            }

            return;
        }

        if ( currentState == RoundState.FLOP ) {
            this.deck.burnCard();

            this.communityCards.add(this.deck.drawCard());
            this.communityCards.add(this.deck.drawCard());
            this.communityCards.add(this.deck.drawCard());

            System.out.println("\nOn the table: ");
            for(Card c : this.communityCards) {
                System.out.println(c);
            }

            return;
        }

        if ( currentState == RoundState.TURN ) {
            this.deck.burnCard();

            this.communityCards.add(this.deck.drawCard());

            System.out.println("\nOn the table: ");
            for(Card c : this.communityCards) {
                System.out.println(c);
            }

            return;
        }

        if ( currentState == RoundState.RIVER ) {
            this.deck.burnCard();

            this.communityCards.add(this.deck.drawCard());

            System.out.println("\nOn the table: ");
            for(Card c : this.communityCards) {
                System.out.println(c);
            }

            return;
        }

        end();
    }

    public boolean getReadyToAdvance() {
        return this.readyToAdvance;
    }

    public void advanceState() {
        if (roundStates.isEmpty()) {
            end();
        } else {
            currentState = roundStates.remove();
            System.out.println(currentState);
        }
    }

    private void addActivePlayers(Set<Player> players) {
        for (Player p : players) {
            assert !activePlayers.contains(p);
            activePlayers.add(new ActivePokerPlayer(p));
        }
    }

    public Set<ActivePokerPlayer> getActivePlayers() {
        return this.activePlayers;
    }

    public void start() {
        this.isRunning = true;
        this.advanceState();
        this.handleRound();
        this.advanceState();
        this.handleRound();
        this.advanceState();
        this.handleRound();
        this.advanceState();
        this.handleRound();
        this.advanceState();
    }

    public void end() {
        //this.isRunning = false;

        System.out.println("Round ended, time to check who won");

        ActivePokerPlayer winner = decideWinner();

    }

    private ActivePokerPlayer decideWinner() {

        ActivePokerPlayer winningPlayer = null;

        for (ActivePokerPlayer p : this.activePlayers) {
            if (winningPlayer == null) {
                winningPlayer = p;
            }

            PlayingHand currentBestHand = winningPlayer.getHand().getBestHandFromCombination(this.communityCards);
            PlayingHand playerBestHand = winningPlayer.getHand().getBestHandFromCombination(this.communityCards);

            if (playerBestHand.getHandValue() > currentBestHand.getHandValue()) {
                winningPlayer = p;
            }
        }

        if (winningPlayer != null) {
            PlayingHand playerBestHand = winningPlayer.getHand().getBestHandFromCombination(this.communityCards);
            System.out.println(winningPlayer.getName() + " vann med handen " + playerBestHand.getCards());
        }


        return winningPlayer;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

}
