package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExerciseJTable extends JTable {

    public ExerciseJTable(DefaultTableModel tableModel) {
        super(tableModel);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
