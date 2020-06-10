import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JTextField textField = new JTextField(20);
    JTextField xField = new JTextField(10);
    JTextField yField = new JTextField(10);
    JTextField widthField = new JTextField(10);
    JTextField heightField = new JTextField(10);
    JButton modifyBtn = new JButton("Modify");

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        JLabel textLabel = new JLabel("Text");
        JLabel xLabel = new JLabel("X");
        JLabel yLabel = new JLabel("Y");
        JLabel widthLabel = new JLabel("Width");
        JLabel heightLabel = new JLabel("Height");

        add(textLabel);
        add(textField);
        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(widthLabel);
        add(widthField);
        add(heightLabel);
        add(heightField);

        add(modifyBtn);
        modifyBtn.addActionListener(this::modify);
    }


    public void setFields(Component component) {
        // check if component has text
        Class<?> clazz;
        try {
            clazz = component.getClass();
            Method method = clazz.getMethod("getText");
            String text = (String) method.invoke(component);
            textField.setText(text);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchMethodException exception) { // no text for this component
            textField.setText("-");
        }

        xField.setText(String.valueOf(component.getX()));
        yField.setText(String.valueOf(component.getY()));
        widthField.setText(String.valueOf(component.getWidth()));
        heightField.setText(String.valueOf(component.getHeight()));
    }

    private void modify(ActionEvent e) {
        Component component = frame.designPanel.getClickedComponent();

        String text = textField.getText();
        Class<?> clazz;
        try {
            clazz = component.getClass();
            Method method = clazz.getMethod("setText", String.class);
            method.invoke(component, text);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchMethodException exception) {
            // no text for this component
        }

        int x = Integer.parseInt(xField.getText());
        int y = Integer.parseInt(yField.getText());
        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());
        component.setBounds(x, y, width, height);
    }
}
