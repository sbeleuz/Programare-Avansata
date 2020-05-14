import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignPanel extends JPanel {
    final static int W = 1280, H = 720;
    final MainFrame frame;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        this.setLayout(null); // absolute positioning
    }

    public void addComponent(Component component) {
        Random random = new Random();
        int x = random.nextInt(DesignPanel.W);
        int y = random.nextInt(DesignPanel.H);
        int w = component.getPreferredSize().width;
        int h = component.getPreferredSize().height;
        component.setBounds(x, y, w, h);

        add(component);
        repaint();
    }
}
