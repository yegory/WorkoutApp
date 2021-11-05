package ui;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JInternalFrame {

    public HomePanel() {
        this.setLayout(new BorderLayout());
        this.setTitle("Home Panel");
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setBackground(new Color(0xED4245));
        this.setIconifiable(true);
        this.setBounds(25, 25, 300, 300);
        this.setVisible(true);
    }
}
