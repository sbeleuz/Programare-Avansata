import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1024, H = 720;
    BufferedImage image; // the offscreen image
    Graphics2D graphics; // the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // smother image
        // fill the image with white
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        Random random = new Random();

        // get size from ConfigPanel (value or random if it's not set)
        int radius;
        String radiusSelected = frame.configPanel.sizeField.getText();
        if (radiusSelected.equals(""))
            radius = random.nextInt(150);
        else
            radius = Integer.parseInt(radiusSelected);

        // get no of sides from ConfigPanel
        int sides = (int) frame.configPanel.sidesField.getValue();

        // get color from ConfigPanel (black or random)
        Color color;
        String colorSelected = Objects.requireNonNull(frame.configPanel.colorCombo.getSelectedItem()).toString();
        if (colorSelected.equals("Black"))
            color = Color.BLACK;
        else
            color = new Color(random.nextInt(0xFFFFFF));

        //draw shape
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}