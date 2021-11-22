package ui;

import model.Routine;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
    This is essentially an ExercisePanel however it does not contain any intractable items such as buttons or labels
        or text areas.

    This class is constructed when the user clicks on 'view [x]' button (included exercises) of a routine in
        RoutinePanel
 */

public class ExerciseTable extends JFrame implements WindowListener {

    String[] tableHeader = {"Name", "Description", "Reps", "Sets", "Rest time (s)", "Rating"};

    DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);
    NonEditableJTable table = new NonEditableJTable(defaultTableModel, 1);
    JScrollPane scrollPane = new JScrollPane(table);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    /*
        Constructs an exercise table with the included exercises found in the chosen routine
     */
    public ExerciseTable(Routine routine) {
        super();
        setTitle("Included exercises");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(350, 25, 825, 400);
        setPreferredSize(new Dimension(1000,300));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        updateExerciseTableLocal(routine);
        setAlwaysOnTop(true);
        setUpTable();
        add(scrollPane);
    }

    /*
        Sets up the main table
     */
    private void setUpTable() {
        scrollPane.setPreferredSize(new Dimension(500,300));
        scrollPane.setVisible(true);

        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(3);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        table.getColumnModel().getColumn(4).setPreferredWidth(25);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0xfca311));
        table.setBackground(new Color(0xe5e5e5));
        table.setRowHeight(25);
        table.setRowMargin(5);
    }

    /*
        MODIFIES: this
        EFFECTS: builds up the table with all the exercises in the included exercise holder inside the Routine
     */
    public void updateExerciseTableLocal(Routine routine) {
        defaultTableModel.setRowCount(0);

        for (int i = 0; i < routine.getIncludedExercises().size(); i++) {
            defaultTableModel.addRow((routine.getIncludedExercises().get(i)).exerciseToStringObject());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        WorkoutAppUI.setExerciseTable(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}