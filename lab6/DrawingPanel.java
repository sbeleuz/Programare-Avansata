import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1280, H = 720;
    BufferedImage image; // the offscreen image
    Graphics2D graphics; // the "tools" needed to draw in the image
    List<Shape> shapes = new ArrayList<>(); // list of used shapes

    static final String REGULAR_POLYGON = "Regular polygon";
    static final String NODE = "Node";
    static final String EDGE = "Edge";
    static final String FREE_DRAWING = "Free drawing";

    Point pointStart, pointEnd; // points used for listeners

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // smoother image
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
                pointStart = e.getPoint(); // get start point for drawing an endge

                // draw a shape on click
                String shape = (String) frame.shapesPanel.availableShapes.getSelectedItem();
                if (shape != null && !shape.equals(EDGE)) {
                    drawShape(e.getX(), e.getY(), shape);
                    repaint();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                pointEnd = e.getPoint(); // get end point for drawing an edge

                String shape = (String) frame.shapesPanel.availableShapes.getSelectedItem();
                if (shape != null && shape.equals(EDGE)) {
                    drawLine(pointStart, pointEnd);
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint(); // get end point for free drawing

                String shape = (String) frame.shapesPanel.availableShapes.getSelectedItem();
                if (shape != null && shape.equals(FREE_DRAWING)) {
                    drawLine(pointStart, pointEnd);
                    repaint();
                    pointStart = pointEnd;
                }
            }
        });
    }

    private void drawLine(Point pointStart, Point pointEnd) {
        // get color from ConfigPanel (black or random)
        Color color = getColor();

        // get stroke from ConfigPanel
        int stroke = (int) frame.configPanel.stroke.getValue();

        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(stroke));
        Edge e = new Edge(pointStart, pointEnd, color);
        graphics.draw(e); // draw edge
        shapes.add(e); // add to list of shapes
        frame.shapesPanel.usedShapes.addItem(e); // add to used shape ComboBox
    }

    private void drawShape(int x, int y, String shape) {
        Random random = new Random();

        // get size from ConfigPanel (value or random if it's not set)
        int radius;
        String radiusSelected = frame.configPanel.sizeField.getText();
        if (radiusSelected.equals(""))
            radius = random.nextInt(100);
        else
            radius = Integer.parseInt(radiusSelected);

        // get no of sides from ConfigPanel
        int sides = (int) frame.configPanel.sidesField.getValue();

        // get color from ConfigPanel (black or random)
        Color color = getColor();

        graphics.setColor(color);
        if (shape.equals(REGULAR_POLYGON)) {
            RegularPolygon p = new RegularPolygon(x, y, radius, sides, color);
            graphics.fill(p); // draw shape
            shapes.add(p); // add to list of shapes
            frame.shapesPanel.usedShapes.addItem(p); // add to used shape ComboBox
        } else if (shape.equals(NODE)) {
            NodeShape n = new NodeShape(x, y, radius, color);
            graphics.fill(n); // draw shape
            shapes.add(n); // add to list of shapes
            frame.shapesPanel.usedShapes.addItem(n); // add to used shape ComboBox
        }
    }

    private Color getColor() {
        Random random = new Random();

        String colorSelected = (String) frame.configPanel.colorCombo.getSelectedItem();
        if (colorSelected != null)
            switch (colorSelected) {
                case "Black":
                    return Color.BLACK;
                case "Red":
                    return Color.RED;
                case "Blue":
                    return Color.BLUE;
                case "Yellow":
                    return Color.YELLOW;
            }
        return new Color(random.nextInt(0xFFFFFF));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}