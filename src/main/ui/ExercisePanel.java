package ui;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExercisePanel extends WorkoutPanelPrototype implements ActionListener {

    private static JTextArea exerciseTextArea;
    private JScrollPane exerciseScrollPane;

    private JButton toggleFavoriteExercisesButton;
    private JButton addExerciseButton;
    private JButton deleteExerciseButton;
    private JButton editExerciseButton;

    private JLabel exerciseNameLabel;
    private JLabel exerciseDescriptionLabel;
    private JLabel exerciseNumberOfRepsLabel;
    private JLabel exerciseNumberOfSetsLabel;
    private JLabel exerciseRestTimeLabel;
    private JLabel exerciseRatingLabel;

    private JTextField exerciseNameTextField;
    private JTextField exerciseDescriptionTextField;
    private JTextField exerciseNumberOfRepsTextField;
    private JTextField exerciseNumberOfSetsTextField;
    private JTextField exerciseRestTimeTextField;
    private JTextField exerciseRatingTextField;

    private JPanel topFlowPanel;
    private JPanel centerFlowPanel;
    private JPanel bottomFlowPanel;
    private JPanel gridPanel;

    private static List<Exercise> exercises;
    private Workout workout;

    public ExercisePanel() {
        super();
        this.setTitle("Exercise Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 25, 825, 400);

        exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
        workout = WorkoutAppUI.getWorkoutApp().getWorkout();

        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();

        gridPanel.add(topFlowPanel);
        gridPanel.add(centerFlowPanel);
        gridPanel.add(bottomFlowPanel);

        exerciseScrollPane = new JScrollPane(exerciseTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(exerciseScrollPane, BorderLayout.CENTER);
        this.add(gridPanel, BorderLayout.SOUTH);

        addExerciseButton.addActionListener(event -> addExercise());
        deleteExerciseButton.addActionListener(event -> deleteExercise());
//        editExerciseButton.addActionListener(event -> exitApplication ());
//        toggleFavoriteExercisesButton.addActionListener(event -> deleteStudent ());
    }

    private void setUpFlowPanels() {
        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridPanel = new JPanel(new GridLayout(3, 1));
        exerciseTextArea = new JTextArea();
        exerciseTextArea.setEditable(false);

        topFlowPanel.add(exerciseNameLabel);
        topFlowPanel.add(exerciseNameTextField);
        topFlowPanel.add(exerciseDescriptionLabel);
        topFlowPanel.add(exerciseDescriptionTextField);

        centerFlowPanel.add(exerciseNumberOfRepsLabel);
        centerFlowPanel.add(exerciseNumberOfRepsTextField);
        centerFlowPanel.add(exerciseNumberOfSetsLabel);
        centerFlowPanel.add(exerciseNumberOfSetsTextField);
        centerFlowPanel.add(exerciseRestTimeLabel);
        centerFlowPanel.add(exerciseRestTimeTextField);
        centerFlowPanel.add(exerciseRatingLabel);
        centerFlowPanel.add(exerciseRatingTextField);

        bottomFlowPanel.add(addExerciseButton);
        bottomFlowPanel.add(deleteExerciseButton);
        bottomFlowPanel.add(editExerciseButton);
        bottomFlowPanel.add(toggleFavoriteExercisesButton);
    }

    private void setUpJLabelsAndTextFields() {
        exerciseNameLabel = new JLabel("Name: ");
        exerciseDescriptionLabel = new JLabel("Description: ");
        exerciseNumberOfRepsLabel = new JLabel("Number of reps: ");
        exerciseNumberOfSetsLabel = new JLabel("Number of sets: ");
        exerciseRestTimeLabel = new JLabel("Rest time (sec): ");
        exerciseRatingLabel = new JLabel("Rating: ");

        exerciseNameTextField = new JTextField(10);
        exerciseDescriptionTextField = new JTextField(40);
        exerciseNumberOfRepsTextField = new JTextField(2);
        exerciseNumberOfSetsTextField = new JTextField(2);
        exerciseRestTimeTextField = new JTextField(3);
        exerciseRatingTextField = new JTextField(1);
    }

    private void setUpButton() {
        addExerciseButton = new JButton("+");
        deleteExerciseButton = new JButton("-");
        editExerciseButton = new JButton("Edit");
        toggleFavoriteExercisesButton = new JButton("View favorite exercises");
    }

    private void addExercise() {
        try {
            Exercise exercise = new Exercise(exerciseNameTextField.getText(), exerciseDescriptionTextField.getText(),
                    Integer.parseInt(exerciseNumberOfRepsTextField.getText()),
                    Integer.parseInt(exerciseNumberOfSetsTextField.getText()),
                    Integer.parseInt(exerciseRestTimeTextField.getText()),
                    Integer.parseInt(exerciseRatingTextField.getText()));
            exercises.add(exercise);
            displayAllExercises();
            resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    private void deleteExercise() {
        if (exercises.size() == 0) {
            JOptionPane.showMessageDialog(null, "Error: Database is empty.");
        } else if (workout.findExercise(exerciseNameTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Error: this exercise is not in the database.");
        } else {
            for (int i = 0; i < exercises.size(); i++) {
                String exerciseName = exercises.get(i).getExerciseName();
                if (exerciseName.compareToIgnoreCase(exerciseNameTextField.getText()) == 0) {
                    exercises.remove(i);
                    break;
                }
            }
            displayAllExercises();
            resetTextFields();
        }
    }

    private void resetTextFields() {
        exerciseNameTextField.setText("");
        exerciseDescriptionTextField.setText("");
        exerciseNumberOfRepsTextField.setText("");
        exerciseNumberOfSetsTextField.setText("");
        exerciseRestTimeTextField.setText("");
        exerciseRatingTextField.setText("");
    }

    public static void displayAllExercises() {
        exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
        exerciseTextArea.setText("");
        for (Exercise exercise : exercises) {
            exerciseTextArea.append(exercise.getExerciseName() + " - "
                    + "Info: " + exercise.getExerciseDescription() + " - "
                    + "Reps: " + exercise.getExerciseNumOfReps() + " - "
                    + "Sets: " + exercise.getExerciseNumOfSets() + " - "
                    + "Rest (s): " + exercise.getExerciseRestTime() + " - "
                    + "Rating: " + exercise.returnDefinedRating() + "\n\n");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
