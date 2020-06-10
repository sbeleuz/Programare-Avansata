import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class DesignPanel extends JPanel implements Serializable {
    final static int W = 1024, H = 720;
    MainFrame frame;
    private Component clickedComponent = null;

    public DesignPanel() {

    }

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public Component getClickedComponent() {
        return clickedComponent;
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        this.setLayout(null); // absolute positioning
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void addComponent(Component component) {
        Random random = new Random();
        int w = component.getPreferredSize().width;
        int h = component.getPreferredSize().height;
        int x = random.nextInt(W - w);
        int y = random.nextInt(H - h);
        component.setBounds(x, y, w, h);

        add(component);
        repaint();
        addListener(component);
    }

    private void addListener(Component component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.configPanel.setFields(component);
                clickedComponent = component;

                DefaultTableModel defaultTableModel = new DefaultTableModel();
                defaultTableModel.addColumn("Attribute name");
                defaultTableModel.addColumn("Attribute value");
                try {
                    Class<?> beanClass = component.getClass();
                    BeanInfo info = Introspector.getBeanInfo(beanClass);
                    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                        Method getter = pd.getReadMethod();
                        if (getter != null) {
                            String attrName = pd.getName();
                            String attrValue = String.valueOf(getter.invoke(component));
                            defaultTableModel.addRow(new String[]{attrName, attrValue});
                        }
                    }
                    frame.propertiesPanel.propertiesTable.setModel(defaultTableModel);
                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException introspectionException) {
                    introspectionException.printStackTrace();
                }
            }
        });
    }
}
