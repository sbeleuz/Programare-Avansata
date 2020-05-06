package gui;

import app.GameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public BoardPanel boardPanel;
    public JTextField textField;

    public MainFrame() {
        super("Gomoku");
        init();
        this.setVisible(true);
    }

    private void init() {
        // create the components
        boardPanel = new BoardPanel(this);
        textField = new JTextField();
        textField.setEditable(false);
        textField.getCaret().setVisible(false);

        // arrange the components in the container (frame)
        add(boardPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);

        this.setSize(new Dimension(690, 725));

        // add close confirmation window
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Gomoku",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    GameClient.out.println("exit");
                    System.exit(0);
                }
            }
        });
    }
}