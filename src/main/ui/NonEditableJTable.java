package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NonEditableJTable extends JTable {

    public NonEditableJTable(DefaultTableModel tableModel) {
        super(tableModel);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
