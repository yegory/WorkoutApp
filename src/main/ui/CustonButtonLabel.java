package ui;

import javax.swing.*;
import java.awt.*;

public class CustonButtonLabel extends JLabel {

    public CustonButtonLabel(String text) {
        super(text);
        setFont(new Font(Font.DIALOG, Font.BOLD, 14));
    }
}
