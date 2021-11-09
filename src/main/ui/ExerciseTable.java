package ui;

import model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseTable extends JPanel {

    private static String[] columnNames;
    private Object[][] data;
    private JTable table;
    private JScrollPane scrollPane;

    public ExerciseTable() {
        super(new GridLayout(1, 0));

        columnNames = new String[]
                {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

        data = new Object[][] {{"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"}};

        table = new JTable(data, columnNames);
        

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    public ExerciseTable(Object[][] newData) {

        super(new GridLayout(2, 0));

        columnNames = new String[]
                {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

        table = new JTable(newData, columnNames);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    public static Object[][] update(List<Exercise> exercises) {
        columnNames = new String[]
                {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

        Object[][] data = new Object[exercises.size() + 1][];
        data[0] = new Object[]{"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

        int i = 1;
        for (Exercise exercise : exercises) {
            data[i] = new Object[6];
            data[i][0] = exercise.getExerciseName();
            data[i][1] = exercise.getExerciseDescription();
            data[i][2] = exercise.getExerciseNumOfReps();
            data[i][3] = exercise.getExerciseNumOfSets();
            data[i][4] = exercise.getExerciseRestTime();
            data[i][5] = exercise.getExerciseRating();
            i++;
        }
        System.out.println(Arrays.deepToString(data));
        return data;
    }
}