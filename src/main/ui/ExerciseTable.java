package ui;

import model.Exercise;
import model.Routine;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExerciseTable extends JFrame {

    String[] tableHeader = {"Name", "Description", "Reps", "Sets", "Rest time (s)", "Rating"};

    String[] exerciseEntries = {};

    DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);
    NonEditableJTable table = new NonEditableJTable(defaultTableModel, 1);
    JScrollPane scrollPane = new JScrollPane(table);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    public ExerciseTable(Routine routine) {
        super();
        setTitle("Included exercises");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(350, 25, 825, 400);
        setPreferredSize(new Dimension(1000,300));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        updateExerciseTableLocal(routine);

        setUpTable();
        add(scrollPane);
    }

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

        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0xFF9026));

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    }

    public void updateExerciseTableLocal(Routine routine) {
        defaultTableModel.setRowCount(0);

        for (int i = 0; i < routine.getIncludedExercises().size(); i++) {
            defaultTableModel.addRow(exerciseToStringObject(routine.getIncludedExercises().get(i)));
        }
    }

    public String[] exerciseToStringObject(Exercise exercise) {
        String[] data = new String[6];

        data[0] = exercise.getExerciseName();
        data[1] = exercise.getExerciseDescription();
        data[2] = Integer.toString(exercise.getExerciseNumOfReps());
        data[3] = Integer.toString(exercise.getExerciseNumOfSets());
        data[4] = Integer.toString(exercise.getExerciseRestTime());
        data[5] = exercise.returnDefinedRating();

        return data;
    }
}