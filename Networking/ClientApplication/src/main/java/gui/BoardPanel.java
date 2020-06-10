package gui;

import app.GameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BoardPanel extends JPanel {
    final MainFrame frame;
    final static int BOARD_SIZE = 19;
    final static int SQUARE_SIZE = 35;
    final static int SIZE = BOARD_SIZE * SQUARE_SIZE + 10;
    BufferedImage image; // the offscreen image
    Graphics2D graphics; // the "tools" needed to draw in the image

    public BoardPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // smoother image
        createBoard();
    }

    private void createBoard() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, SIZE, SIZE);

        graphics.setColor(Color.BLACK);
        int xPos = 0;
        int yPos = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                Rectangle tile = new Rectangle(xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
                graphics.draw(tile);
                xPos += SQUARE_SIZE;
            }

            yPos += SQUARE_SIZE;
            xPos = 0;
        }
        repaint();
    }

    private void init() {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setBorder(BorderFactory.createEtchedBorder());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (e.getX() / SQUARE_SIZE);
                int y = (e.getY() / SQUARE_SIZE);
                GameClient.out.println("move " + x + " " + y);
            }
        });
    }

    public void drawCircle(int player, int x, int y) {
        if (player == 1)
            graphics.setColor(Color.BLUE);
        else
            graphics.setColor(Color.RED);

        x = x * SQUARE_SIZE + SQUARE_SIZE / 2;
        y = y * SQUARE_SIZE + SQUARE_SIZE / 2;

        int radius = 10;
        int diameter = radius * 2;

        graphics.fillOval(x - radius, y - radius, diameter, diameter);
        repaint();
    }

    public void gameOver(String message) { // win/loose/draw
        message += "\n Do you want to play again?";
        int answer = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION);

        if (answer == 0) { // Yes
            GameClient.out.println("restart");
            createBoard();
        } else { // No
            GameClient.out.println("exit");
            System.exit(0);
        }
    }

    public void opponentHasLeft() {
        JOptionPane.showMessageDialog(null, "Your opponent has left!");
        createBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
