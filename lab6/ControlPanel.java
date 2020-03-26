import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager
        setLayout(new GridLayout(1, 4, 10, 0));

        // add all buttons
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        // configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        try {
            // select location to save
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/OneDrive/Desktop"); // open on Desktop

            // get selected location
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(frame.canvas.image, "PNG", new File(fileToSave.getAbsolutePath()));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
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
                BufferedImage img = ImageIO.read(new File(fileToLoad.getAbsolutePath()));
                frame.canvas.graphics.drawImage(img, null, 0, 0);
                frame.canvas.repaint();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void reset(ActionEvent e) {
        frame.canvas.graphics.setColor(Color.WHITE);
        frame.canvas.graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
        frame.canvas.repaint();
    }

    private void exit(ActionEvent e) {
        frame.dispose();
    }
}
