import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShapesPanel extends JPanel {
    final MainFrame frame;
    JComboBox<String> availableShapes; // list of shapes to draw
    JComboBox<Shape> usedShapes; // list of drawn shape (able to delete)
    JButton deleteBtn;

    public ShapesPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        setPreferredSize(new Dimension(150, DrawingPanel.H));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // create first panel (available shapes to draw)
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1, 0, 10));

        JLabel availableShapesLabel = new JLabel("Choose a shape to draw");
        availableShapes = new JComboBox<>(new String[]{DrawingPanel.REGULAR_POLYGON, DrawingPanel.NODE, DrawingPanel.EDGE, DrawingPanel.FREE_DRAWING});
        availableShapes.setSelectedIndex(0);

        panel1.add(availableShapesLabel);
        panel1.add(availableShapes);

        // create second panel (list of shapes drawn)
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1, 0, 10));

        JLabel usedShapesLabel = new JLabel("Choose a shape to delete");
        usedShapes = new JComboBox<>();

        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(this::delete);

        panel2.add(usedShapesLabel);
        panel2.add(usedShapes);
        panel2.add(deleteBtn);

        // add elements to panel
        add(panel1);
        add(Box.createVerticalStrut(500)); // space between panels
        add(panel2);

        // update ConfigPanel based on selected shape to draw
        availableShapes.addActionListener(e -> {
            frame.configPanel.removeAll();
            frame.configPanel.revalidate();
            frame.configPanel.repaint();

            String shape = (String) availableShapes.getSelectedItem();
            if (shape != null)
                frame.configPanel.init(shape);
        });
    }

    private void delete(ActionEvent e) {
        // get selected shape
        Shape shapeSelected = (Shape) usedShapes.getSelectedItem();
        if (shapeSelected != null) {
            // remove from ComboBox
            usedShapes.removeItem(shapeSelected);
            // remove from list of shapes
            frame.canvas.shapes.remove(shapeSelected);

            // delete shape
            frame.canvas.graphics.setColor(Color.WHITE);
            frame.canvas.graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
            frame.canvas.repaint();
            // paint all shapes, except the one selected
            for (Shape shape : frame.canvas.shapes) {
                if (shape instanceof RegularPolygon) {
                    frame.canvas.graphics.setColor(((RegularPolygon) shape).getColor());
                    frame.canvas.graphics.fill(shape);
                    frame.canvas.repaint();
                } else if (shape instanceof NodeShape) {
                    frame.canvas.graphics.setColor(((NodeShape) shape).getColor());
                    frame.canvas.graphics.fill(shape);
                    frame.canvas.repaint();
                } else if (shape instanceof Edge) {
                    frame.canvas.graphics.setColor(((Edge) shape).getColor());
                    frame.canvas.graphics.draw(shape);
                    frame.canvas.repaint();
                }
            }
        }
    }
}
