import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PropertiesPanel extends JPanel {
    final MainFrame frame;
    JTable propertiesTable;

    public PropertiesPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Attribute name");
        defaultTableModel.addColumn("Attribute value");
        propertiesTable = new JTable(defaultTableModel);

        JScrollPane scrollPane = new JScrollPane(propertiesTable);
        add(scrollPane);
    }
}
