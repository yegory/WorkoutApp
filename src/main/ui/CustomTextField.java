package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/*
CUSTOM JTextField Border CODE Taken FROM
https://stackoverflow.com/questions/8515601/java-swing-rounded-border-for-jtextfield
Legend.
 */
public class CustomTextField extends JTextField {
    private Shape shape;
    private int arcWidth = 15;
    private int arcHeight = 15;

    public CustomTextField(int columns) {
        super(columns);
        setBackground(new Color(0xA8A8A8));
        setOpaque(false); // As suggested by @AVD in comment.
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        }
        return shape.contains(x, y);
    }
}
