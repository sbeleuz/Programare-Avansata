import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.beans.XMLEncoder;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JTextField componentField = new JTextField(25);
    JTextField defaultTextField = new JTextField(25);
    JButton createBtn = new JButton("Create");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        componentField.setText("javax.swing.JButton");
        JLabel componentLabel = new JLabel("Class name:");
        JLabel defaultTextLabel = new JLabel("Default text:");

        createBtn.addActionListener(this::createComponent);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);

        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(componentLabel);
        add(componentField);
        add(defaultTextLabel);
        add(defaultTextField);
        add(createBtn);
    }

    private void createComponent(ActionEvent e) {
        Class<?> clazz;
        Component component = null;
        try {
            clazz = Class.forName(componentField.getText());
            component = (Component) clazz.getConstructor().newInstance();
            // try to set default text
            Method method = clazz.getMethod("setText", String.class);
            method.invoke(component, defaultTextField.getText());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchMethodException exception) { // no default text for this component
            try {
                clazz = Class.forName(componentField.getText());
                component = (Component) clazz.getConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (component != null) {
            frame.designPanel.addComponent(component);
        }
    }

    private void save(ActionEvent e) {
        try {
            // select location to save
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/OneDrive/Desktop"); // open on Desktop

            // get selected location
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                XMLEncoder xmlEncoder = new XMLEncoder(new ObjectOutputStream(new FileOutputStream(fileToSave)));
                xmlEncoder.writeObject(frame.designPanel);
                xmlEncoder.close();
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void load(ActionEvent e) {
        try {
            // chose file to load
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/OneDrive/Desktop"); // open on Desktop

            // get selected file
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                XMLDecoder xmlDecoder = new XMLDecoder(new ObjectInputStream((new FileInputStream(fileToLoad))));
                frame.updateDesignPanel((DesignPanel) xmlDecoder.readObject());
                xmlDecoder.close();
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void reset(ActionEvent e) {
        frame.designPanel.removeAll();
        frame.designPanel.repaint();
    }
}