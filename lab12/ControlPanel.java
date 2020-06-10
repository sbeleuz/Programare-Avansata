import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JTextField componentField = new JTextField(25);
    JTextField defaultTextField = new JTextField(25);
    JButton createBtn = new JButton("Create");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        JLabel componentLabel = new JLabel("Class name:");
        JLabel defaultTextLabel = new JLabel("Default text:");

        createBtn.addActionListener(this::createComponent);

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
}
