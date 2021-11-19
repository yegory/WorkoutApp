package ui;

import javax.swing.*;
import java.awt.*;

/*
    This class is essentially JLabel except with custom font. This saves lots of lines of code and makes
    the labels more visually appealing.
 */

public class CustomButtonLabel extends JLabel {

    public CustomButtonLabel(String text) {
        super(text);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
    }
}
