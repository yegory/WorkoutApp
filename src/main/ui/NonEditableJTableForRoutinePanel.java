package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NonEditableJTableForRoutinePanel extends JTable {

    public NonEditableJTableForRoutinePanel(DefaultTableModel tableModel) {
        super(tableModel);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 2 && row != 0;
    }
}
