package ui;

import javax.swing.*;
import java.awt.*;

public class FilePanel extends JInternalFrame {

    public FilePanel() {
        this.setLayout(new BorderLayout());
        this.setTitle("File Panel");
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setBackground(new Color(0xFEE75C));
        this.setIconifiable(true);
        this.setBounds(25, 350, 300, 300);
        this.setVisible(true);
    }
}