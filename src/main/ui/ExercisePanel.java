package ui;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExercisePanel extends WorkoutPanelPrototype implements ActionListener {

    private boolean favoriteView = false;
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

    static String[] tableHeader =
            {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

    static String[] exerciseEntries =
            {"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"};

    static DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);

    NonEditableJTable mainJTable = new NonEditableJTable(defaultTableModel);
    JScrollPane scrollPane = new JScrollPane(mainJTable);


    private static List<Exercise> exercises;
    private Workout workout = WorkoutAppUI.getWorkoutApp().getWorkout();

    public ExercisePanel() {
        super();
        this.setTitle("Exercise Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 25, 825, 400);

        updateExerciseTable(workout);

        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();

        setUpTable();

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(gridPanel, BorderLayout.SOUTH);

        addExerciseButton.addActionListener(event -> addExercise());
        deleteExerciseButton.addActionListener(event -> deleteExercise());
        editExerciseButton.addActionListener(event -> editExercise());
        toggleFavoriteExercisesButton.addActionListener(event -> repopulateWithFavorites(favoriteView));
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

    private void setUpFlowPanels() {
        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridPanel = new JPanel(new GridLayout(3, 1));

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

        gridPanel.add(topFlowPanel);
        gridPanel.add(centerFlowPanel);
        gridPanel.add(bottomFlowPanel);
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
        toggleFavoriteExercisesButton.setPreferredSize(new Dimension(200,25));
    }

    private void addExercise() {
        workout = WorkoutAppUI.getWorkoutApp().getWorkout();
        try {
            Exercise exercise = new Exercise(exerciseNameTextField.getText(),
                    exerciseDescriptionTextField.getText(),
                    Integer.parseInt(exerciseNumberOfRepsTextField.getText()),
                    Integer.parseInt(exerciseNumberOfSetsTextField.getText()),
                    Integer.parseInt(exerciseRestTimeTextField.getText()),
                    Integer.parseInt(exerciseRatingTextField.getText()));
            workout.addExercise(exercise);
            //updateExerciseTable(workout);
            defaultTableModel.addRow(exerciseToStringObject(exercise));
            resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    public void deleteExercise() {
        try {
            String exerciseName = exerciseNameTextField.getText();
            exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exercises.remove(exercise);
                    updateExerciseTable(WorkoutAppUI.getWorkoutApp().getWorkout());
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    private void editExercise() {
        try {
            String exerciseName = exerciseNameTextField.getText();

            exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exercise.setExerciseDescription(exerciseDescriptionTextField.getText());
                    exercise.setExerciseNumOfReps(Integer.parseInt(exerciseNumberOfRepsTextField.getText()));
                    exercise.setExerciseNumOfSets(Integer.parseInt(exerciseNumberOfSetsTextField.getText()));
                    exercise.setExerciseRestTime(Integer.parseInt(exerciseRestTimeTextField.getText()));
                    exercise.setExerciseRating(Integer.parseInt(exerciseRatingTextField.getText()));

                    updateExerciseTable(WorkoutAppUI.getWorkoutApp().getWorkout());
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }


    public void repopulateWithFavorites(boolean isFavoriteView) {
        if (!isFavoriteView) {
            workout = WorkoutAppUI.getWorkoutApp().getWorkout();
            defaultTableModel.setRowCount(0);

            defaultTableModel.addRow(exerciseEntries);
            for (int i = 0; i < workout.exercisesSize(); i++) {
                if (workout.getExercise(i).getExerciseRating() == 5) {
                    defaultTableModel.addRow(exerciseToStringObject(workout.getExercise(i)));
                }
            }
            favoriteView = true;
            toggleFavoriteExercisesButton.setText("View all exercises");
        } else {
            workout = WorkoutAppUI.getWorkoutApp().getWorkout();
            updateExerciseTable(workout);
            favoriteView = false;
            toggleFavoriteExercisesButton.setText("View favorite exercises");
        }
    }

    public static void updateExerciseTable(Workout workout) {
        defaultTableModel.setRowCount(0);

        defaultTableModel.addRow(exerciseEntries);
        for (int i = 0; i < workout.exercisesSize(); i++) {
            defaultTableModel.addRow(exerciseToStringObject(workout.getExercise(i)));
        }
    }

    public static String[] exerciseToStringObject(Exercise exercise) {
        String[] data = new String[6];

        data[0] = exercise.getExerciseName();
        data[1] = exercise.getExerciseDescription();
        data[2] = Integer.toString(exercise.getExerciseNumOfReps());
        data[3] = Integer.toString(exercise.getExerciseNumOfSets());
        data[4] = Integer.toString(exercise.getExerciseRestTime());
        data[5] = exercise.returnDefinedRating();

        return data;
    }

    private void resetTextFields() {
        exerciseNameTextField.setText("");
        exerciseDescriptionTextField.setText("");
        exerciseNumberOfRepsTextField.setText("");
        exerciseNumberOfSetsTextField.setText("");
        exerciseRestTimeTextField.setText("");
        exerciseRatingTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}