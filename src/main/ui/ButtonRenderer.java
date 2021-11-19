package ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/*
    Credit:
    http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm

    Don't really understand this (which is why I include a source)
        but I think this just creates a JButton and changes the background color
        depending on whether the button is clicked or not.
 */

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}