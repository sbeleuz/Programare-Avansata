import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;
    PropertiesPanel propertiesPanel;
    ConfigPanel configPanel;

    public MainFrame() {
        super("Dynamic Swing Designer");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //create the components
        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);
        propertiesPanel = new PropertiesPanel(this);
        configPanel = new ConfigPanel(this);

        //arrange the components in the container (frame)
        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);
        add(propertiesPanel, BorderLayout.WEST);
        add(configPanel, BorderLayout.SOUTH);

        //invoke the layout manager
        pack();
    }

    public void updateDesignPanel(DesignPanel designPanel) {
        getContentPane().remove(this.designPanel);
        this.designPanel = designPanel;
        getContentPane().add(this.designPanel);
        validate();
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
