package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NonEditableJTable extends JTable {

    private int version;

    public NonEditableJTable(DefaultTableModel tableModel, int version) {
        super(tableModel);
        this.version = version;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (version == 1) {
            return false;
        } else if (version == 2) {
            return column == 2;
        }
        return false;
    }
}
