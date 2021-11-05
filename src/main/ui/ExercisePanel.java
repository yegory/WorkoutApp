package ui;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JInternalFrame {

    public ExercisePanel() {
        this.setLayout(new BorderLayout());
        this.setTitle("Exercise Panel");
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setBounds(350, 25, 400, 750);
        //exercisePanel.setSize(300,500);
        this.setVisible(true);
    }
}
