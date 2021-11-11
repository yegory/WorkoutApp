package ui;

import model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ChoiceList implements ItemListener {

    JFrame frame;
    TextArea textArea;
    JButton doneButton = new JButton("Done");
    JButton deleteButton = new JButton("Delete all");
    private List list;
    private static ArrayList<String> finalList = new ArrayList<String>();

    private static java.util.List<Exercise> exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();

    public ChoiceList() {
        frame = new JFrame("Exercise Selector");
        frame.setPreferredSize(new Dimension(800, 400));
        frame.pack();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        setUp();

        doneButton.addActionListener(event -> createListExercise());
        deleteButton.addActionListener(event -> deleteListExercise());
    }

    private void deleteListExercise() {
        finalList = new ArrayList<String>();
        frame.remove(list);
        frame.remove(textArea);
        setUp();
        textArea.setText("");
        int i = 1;
        for (String exercise : finalList) {
            textArea.append(i + ") " + exercise + "\n");
            i++;
        }
    }

    public static java.util.List<Exercise> createListExercise() {

        java.util.List<Exercise> exerciseList = new ArrayList<>();
        try {
            for (String exerciseName : finalList) {
                for (Exercise exercise : exercises) {
                    if (exercise.getExerciseName().equals(exerciseName)) {
                        exerciseList.add(exercise);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("error");
        }
        return exerciseList;
    }

    private void setUp() {

        list = new List(exercises.size(), false);
        for (Exercise exercise: exercises) {
            list.add(exercise.getExerciseName());
        }
        list.addItemListener(this);

        textArea = new TextArea(10, 30);
        textArea.setBackground(Color.WHITE);

        frame.add(list);
        frame.add(textArea);
        frame.add(doneButton);
        frame.add(deleteButton);
        frame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        frame.remove(list);
        frame.remove(textArea);
        finalList.add(list.getSelectedItem());
        setUp();
        textArea.setText("");
        int i = 1;
        for (String exercise : finalList) {
            textArea.append(i + ") " + exercise + "\n");
            i++;
        }
    }
}
