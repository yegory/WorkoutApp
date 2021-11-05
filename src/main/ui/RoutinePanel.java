package ui;

import javax.swing.*;
import java.awt.*;

public class RoutinePanel extends JInternalFrame {

    public RoutinePanel() {
        this.setLayout(new BorderLayout());
        this.setTitle("Routine Panel");
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setBounds(775, 25, 400, 750);
        //exercisePanel.setSize(300,500);
        this.setVisible(true);
    }
}
