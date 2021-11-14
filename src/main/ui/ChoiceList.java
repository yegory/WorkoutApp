package ui;

import model.Exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ChoiceList extends JFrame implements ItemListener {

    JPanel buttonPanel;
    JPanel gridPanel;
    TextArea textArea;
    JButton doneButton;
    JButton deleteButton;

    private List list;
    private static ArrayList<String> finalList = new ArrayList<>();
    private static java.util.List<Exercise> exercises = WorkoutAppUI.getWorkout().getExercises();

    public ChoiceList() {
        setTitle("Exercise Selector");
        setPreferredSize(new Dimension(800, 400));
        pack();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(0x8D8D93));
        setUp();
    }

    private void deleteListExercise() {
        finalList = new ArrayList<String>();
        remove(list);
        remove(textArea);
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
        list.setBackground(new Color(0xD2D2D2));
        list.setPreferredSize(new Dimension(150,300));
        for (Exercise exercise: exercises) {
            list.add(exercise.getExerciseName());
        }
        list.addItemListener(this);

        textArea = new TextArea(10, 30);
        textArea.setBackground(Color.WHITE);

        doneButton = new JButton("Done");
        deleteButton = new JButton("Delete all");
        doneButton.addActionListener(event -> createListExercise());
        deleteButton.addActionListener(event -> deleteListExercise());

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridPanel = new JPanel(new GridLayout(2,2));

        buttonPanel.add(doneButton);
        buttonPanel.add(deleteButton);

        gridPanel.add(list);
        gridPanel.add(textArea);
        gridPanel.add(buttonPanel);

        add(gridPanel);
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        remove(gridPanel);
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
