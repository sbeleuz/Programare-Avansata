import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JTextField sizeField; // size of the shape
    JSpinner sidesField; // number of sides
    JComboBox<String> colorCombo; // the color of the shape
    JSpinner stroke; // stroke size

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init(DrawingPanel.REGULAR_POLYGON);
    }

    // adapt ConfigPanel based on selected shape
    public void init(String shape) {
        // create the label and the comboBox for colors, containing the values: Random and Black
        JLabel colorLabel = new JLabel("Color of shape");
        colorCombo = new JComboBox<>(new String[]{"Black", "Red", "Blue", "Yellow", "Random"});
        colorCombo.setSelectedIndex(0); // black by default

        // add elements to config panel
        add(colorLabel);
        add(colorCombo);

        switch (shape) {
            case DrawingPanel.REGULAR_POLYGON:
                // create the label and the textField for size
                JLabel sizeLabel = new JLabel("Size of shape:");
                sizeField = new JTextField(5);

                // create the label and the spinner for number of sides
                JLabel sidesLabel = new JLabel("Number of sides:");
                sidesField = new JSpinner(new SpinnerNumberModel(6, 3, 50, 1));

                // add elements to config panel
                add(sizeLabel);
                add(sizeField);
                add(sidesLabel);
                add(sidesField);
                break;

            case DrawingPanel.NODE:
                // create the label and the textField for size
                sizeLabel = new JLabel("Size of shape:");
                sizeField = new JTextField(5);

                // add elements to config panel
                add(sizeLabel);
                add(sizeField);
                break;

            case DrawingPanel.FREE_DRAWING:
            case DrawingPanel.EDGE:
                // create the label and the spinner for stroke
                JLabel strokeLabel = new JLabel("Stroke:");
                stroke = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));

                // add elements to config panel
                add(strokeLabel);
                add(stroke);
        }
    }
}
