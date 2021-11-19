package ui;

import javax.swing.*;
import java.awt.*;

/*
    An abstract class to help remove some code redundancy when creating JInternalFrames in WorkoutAppUI
 */

public abstract class AbstractInternalFrame extends JInternalFrame {

    public AbstractInternalFrame(String title) {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setClosable(true);
        setMaximizable(false);
        setIconifiable(true);
        setVisible(true);
        setTitle(title);
    }
}
