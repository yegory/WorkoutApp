package ui;

import javax.swing.*;
import java.awt.*;

public class WorkoutPanelPrototype extends JInternalFrame {

    public WorkoutPanelPrototype() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(false);
        this.setIconifiable(true);
        this.setVisible(true);
    }
}
