import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerGameController implements GameController {

    GameModel gameModel;


    public PokerGameController(GameModel gameModel) {

        this.gameModel = gameModel;

        PokerGameModel game = new PokerGameModel();

        game.addPlayer("Sebastian", 5000.00);
        game.addPlayer("Olle", 3000.00);
        game.addPlayer("Leo", 2000.00);

        Set<Player> players = game.getPlayers();

        for(Player p : players) {
            System.out.println(p);
        }

        game.start();

    }

}
