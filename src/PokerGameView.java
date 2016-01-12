import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-10
 * Project: Poker
 */
public class PokerGameView extends JPanel implements GameView, MouseListener, PropertyChangeListener {

    final int PLAYER_POSITION_BOX_WIDTH = 125;
    final int PLAYER_POSITION_BOX_HEIGHT = 65;


    public enum PlayerPosition {
        POSITION_1 (105, 70),
        POSITION_2 (440, 70),
        POSITION_3 (765, 70),
        POSITION_4 (105, 590),
        POSITION_5 (440, 590),
        POSITION_6 (765, 590);

        private final int x;
        private final int y;

        PlayerPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    private GameController gameController;

    static class ImagePanel extends JComponent {
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    public PokerGameView(GameController gameController) {
        this.gameController = gameController;

        setSize(new Dimension(1000, 715));
        addMouseListener(this);
    }

    private void addPlayerPositions(Graphics g) {
        for (PokerGameView.PlayerPosition pos : PokerGameView.PlayerPosition.values()) {
            g.setColor(new Color(0, 0, 0, 80));
            g.fillRect(pos.getX(), pos.getY(), PLAYER_POSITION_BOX_WIDTH, PLAYER_POSITION_BOX_HEIGHT);
        }
    }

    private void setBackGroundImage(Graphics g) {
        try {
            BufferedImage myImage = ImageIO.read(new File("resources/images/greenbyrequest.png"));
            g.drawImage(myImage, 0, 0, null);
        } catch (IOException e) {
            System.out.println("Background file does not exist");
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackGroundImage(g);
        addPlayerPositions(g);
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
