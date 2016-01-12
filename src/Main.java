import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // new GUIView(new GameFactory)

        frame.setResizable(false);

        GameModel gameModel = new PokerGameModel();
        GameController gameController = new PokerGameController(gameModel);
        Component gameView = new PokerGameView(gameController);

        frame.add(gameView);
        frame.setSize(1000, 715);

        frame.setTitle("Texas Hold'em");

        // add guiView
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.requestFocus();
    }
}
