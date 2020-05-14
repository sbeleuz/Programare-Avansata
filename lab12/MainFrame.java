import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

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

        //arrange the components in the container (frame)
        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);

        //invoke the layout manager
        pack();
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
