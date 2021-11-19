package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/*
    CUSTOM JTextField Border code borrowed from Harry Joy:
    https://stackoverflow.com/questions/8515601/java-swing-rounded-border-for-jtextfield

    This class is responsible for creating TextFields with rounded off corners that look nice. Only for visual appeal.
    Since I just got this code from the stackoverflow post, I won't try to understand how all of this works.
 */

public class CustomTextField extends JTextField {
    private Shape shape;
    private final int arcWidth = 15;
    private final int arcHeight = 15;

    /*
        Constructs a textField with the selected color.
     */
    public CustomTextField(int columns) {
        super(columns);
        setBackground(new Color(0xBDBCBC));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        }
        return shape.contains(x, y);
    }
}
