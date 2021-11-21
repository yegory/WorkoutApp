package ui;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/*
    Got the idea from https://www.youtube.com/watch?v=1EN2lA__xV0
    "Create a pop-up list of items using choice, multiple-select scrolling selection with List"
    However I modified a significant portion to work with my project idea.

    This class creates a list of exercise name entries in a small window from which I can select any exercise
    any number of times and in any order. Also, it has a "done" button that saves this list in case I want to include
    the exercises in my routine, as well as a "delete all" button which deletes all exercises from the final list.

    Since in the video, a java.awt.List is used, I had to define List<Exercise> as java.util.List<Exercise>
    (can't have both import in the same file)

 */

public class ChoiceList extends JFrame implements ItemListener {

    JPanel buttonPanel;
    JPanel textPanel;
    JPanel gridPanel;
    TextArea textArea;
    JButton doneButton;
    JButton deleteButton;

    private List list;
    private static ArrayList<String> finalList = new ArrayList<>();
    private static java.util.List<Exercise> exercises = WorkoutAppUI.getWorkout().getExercises();

    /*
        Constructs a Choice list, adds all the components and populates the text area
     */
    public ChoiceList() {
        setTitle("Exercise Selector");
        setPreferredSize(new Dimension(500, 350));
        pack();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0x8D8D93));
        setUp();
        updateTextArea();
    }

    /*
        MODIFIES: this
        EFFECTS: Set up the list, panels, buttons
     */

    private void setUp() {
        list = new List(exercises.size(), false);
        list.setBackground(new Color(0xD2D2D2));
        for (Exercise exercise: exercises) {
            list.add(exercise.getExerciseName());
        }
        list.addItemListener(this);

        gridPanel = new JPanel(new GridLayout(2,2));
        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        textArea = new TextArea(10, 30);
        textArea.setBackground(new Color(0x7AAB7A));

        textPanel.add(list);
        textPanel.add(textArea);

        doneButton = new JButton("Done");
        deleteButton = new JButton("Delete all");
        doneButton.addActionListener(event -> createListExercise());
        deleteButton.addActionListener(event -> deleteListExercise());

        buttonPanel.add(doneButton);
        buttonPanel.add(deleteButton);

        gridPanel.add(textPanel);
        gridPanel.add(buttonPanel);

        add(gridPanel);
        setVisible(true);
    }

    /*
        MODIFIES: this
        EFFECTS: for each exerciseName chosen in the TextArea, finds the corresponding Exercise Object and adds
            it to a List of Exercise
     */
    public static java.util.List<Exercise> createListExercise() {
        java.util.List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = choiceListHelper(finalList);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    "Could not create a list of exercises", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return exercises;
    }

    public static java.util.List<Exercise> choiceListHelper(ArrayList<String> finalList) throws NullPointerException {

        java.util.List<Exercise> exerciseList = new ArrayList<>();

        for (String exerciseName : finalList) {
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exerciseList.add(exercise);
                }
            }
        }

        return exerciseList;

    }

    /*
        MODIFIES: this
        EFFECTS: Sets the list of current added exercises to empty and updates the panel to reflect this in the
        text area
     */
    private void deleteListExercise() {
        finalList = new ArrayList<>();
        remove(gridPanel);
        setUp();
        textArea.setText("");
        int i = 1;
        for (String exercise : finalList) {
            textArea.append(i + ") " + exercise + "\n");
            i++;
        }
    }

    /*
        MODIFIES: this
        EFFECTS: populates the textArea with exercisePosition followed by exerciseName "1) ExerciseName"
     */
    public void updateTextArea() {
        textArea.setText("");
        int i = 1;
        for (String exercise : finalList) {
            textArea.append(i + ") " + exercise + "\n");
            i++;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        remove(gridPanel);
        finalList.add(list.getSelectedItem());
        setUp();
        updateTextArea();
    }
}
