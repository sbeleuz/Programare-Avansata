import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JTextField sizeField; // size of the shape
    JSpinner sidesField; // number of sides
    JComboBox<String> colorCombo; // the color of the shape

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the textField for size
        JLabel sizeLabel = new JLabel("Size of shape:");
        sizeField = new JTextField(5);

        //create the label and the spinner for number of sides
        JLabel sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(6, 3, 50, 1));

        //create the label and the comboBox for colors, containing the values: Random and Black
        JLabel colorLabel = new JLabel("Color of shape");
        colorCombo = new JComboBox<>(new String[]{"Black", "Random"});
        colorCombo.setSelectedIndex(0); // black by default

        //add elements to config panel
        add(sizeLabel);
        add(sizeField);
        add(sidesLabel);
        add(sidesField);
        add(colorLabel);
        add(colorCombo);
    }
}
