package ui;

import model.Exercise;
import model.Routine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExerciseTable extends JFrame {

    String[] tableHeader =
            {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

    String[] exerciseEntries =
            {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

    DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);

    NonEditableJTable mainJTable = new NonEditableJTable(defaultTableModel);

    JScrollPane scrollPane = new JScrollPane(mainJTable);

    public ExerciseTable(Routine routine) {
        super();
        this.setTitle("Included exercises");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 25, 825, 400);
        this.setPreferredSize(new Dimension(1000,300));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        updateExerciseTableLocal(routine);
        setUpTable();

        this.add(scrollPane);
    }

    private void setUpTable() {
        scrollPane.setPreferredSize(new Dimension(500,300));
        scrollPane.setVisible(true);

        mainJTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        mainJTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        mainJTable.getColumnModel().getColumn(2).setPreferredWidth(15);
        mainJTable.getColumnModel().getColumn(3).setPreferredWidth(15);
        mainJTable.getColumnModel().getColumn(4).setPreferredWidth(25);
        mainJTable.getColumnModel().getColumn(5).setPreferredWidth(20);
    }

    public void updateExerciseTableLocal(Routine routine) {
        defaultTableModel.setRowCount(0);

        defaultTableModel.addRow(exerciseEntries);
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