package ui;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractInternalFrame extends JInternalFrame {

    public AbstractInternalFrame() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setClosable(true);
        setMaximizable(false);
        setIconifiable(true);
        setVisible(true);
    }
}
