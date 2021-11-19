package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
    Represents a non-editableJTable.
    Used in Exercise Panel, Routine Panel, and Exercise Table.
    In Exercise Panel, 1 is passed as version as the table is not editable anywhere.
    In Routine Panel, 2 is passed as version as the table has to be editable on column 2 (where the button is located)
*/
public class NonEditableJTable extends JTable {

    private int version;

    /*
        Constructs a NonEditableJTable with tableModel parameter (which contains the header for the table)
     */
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
