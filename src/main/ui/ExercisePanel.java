package ui;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ExercisePanel extends AbstractInternalFrame implements ActionListener {

    private boolean favoriteView = false;
    JButton toggleFavoriteExercisesButton;
    JButton addExerciseButton;
    JButton deleteExerciseButton;
    JButton editExerciseButton;

    CustonButtonLabel exerciseNameLabel;
    CustonButtonLabel exerciseDescriptionLabel;
    CustonButtonLabel exerciseNumberOfRepsLabel;
    CustonButtonLabel exerciseNumberOfSetsLabel;
    CustonButtonLabel exerciseRestTimeLabel;
    CustonButtonLabel exerciseRatingLabel;
    CustonButtonLabel exerciseSearchLabel;

    CustomTextField exerciseNameTextField;
    CustomTextField exerciseDescriptionTextField;
    CustomTextField exerciseNumberOfRepsTextField;
    CustomTextField exerciseNumberOfSetsTextField;
    CustomTextField exerciseRestTimeTextField;
    CustomTextField exerciseRatingTextField;
    CustomTextField exerciseSearchTextField;

    JPanel topFlowPanel;
    JPanel centerFlowPanel;
    JPanel bottomFlowPanel;
    JPanel gridPanel;

    static String[] tableHeader = {"Name", "Description", "Reps", "Sets", "Rest time (s)", "Rating"};

    static String[] exerciseEntries = {};

    static DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);

    NonEditableJTable table = new NonEditableJTable(defaultTableModel, 1);
    JScrollPane scrollPane = new JScrollPane(table);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    private static List<Exercise> exercises;
    private Workout workout = WorkoutAppUI.getWorkout();


    public ExercisePanel() {
        super("Exercise Panel");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(350, 0, 825, 425);

        setUp();
    }

    private void setUp() {
        updateExerciseTable(workout);

        setUpJLabelsAndTextFields();
        setUpButton();
        initializeUpFlowPanels();
        setUpFlowPanels();
        setUpTable();
        setUpActionListeners();

        gridPanel = new JPanel(new GridLayout(3, 1));
        gridPanel.add(topFlowPanel);
        gridPanel.add(centerFlowPanel);
        gridPanel.add(bottomFlowPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);
    }

    private void setUpActionListeners() {
        addExerciseButton.addActionListener(event -> addExercise());
        deleteExerciseButton.addActionListener(event -> deleteExercise());
        editExerciseButton.addActionListener(event -> editExercise());
        toggleFavoriteExercisesButton.addActionListener(event -> repopulateWithFavorites(favoriteView));
    }

    private void setUpTable() {
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVisible(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(550);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);

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

    private void initializeUpFlowPanels() {
        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topFlowPanel.setBackground(new Color(0x696969));
        centerFlowPanel.setBackground(new Color(0x868686));
        bottomFlowPanel.setBackground(new Color(0x696969));
    }

    private void setUpFlowPanels() {
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

        bottomFlowPanel.add(exerciseSearchLabel);
        bottomFlowPanel.add(exerciseSearchTextField);
        bottomFlowPanel.add(addExerciseButton);
        bottomFlowPanel.add(deleteExerciseButton);
        bottomFlowPanel.add(editExerciseButton);
        bottomFlowPanel.add(toggleFavoriteExercisesButton);
    }

    private void setUpJLabelsAndTextFields() {
        exerciseNameLabel = new CustonButtonLabel("Name: ");
        exerciseDescriptionLabel = new CustonButtonLabel("Description: ");
        exerciseNumberOfRepsLabel = new CustonButtonLabel("Number of reps: ");
        exerciseNumberOfSetsLabel = new CustonButtonLabel("Number of sets: ");
        exerciseRestTimeLabel = new CustonButtonLabel("Rest time (sec): ");
        exerciseRatingLabel = new CustonButtonLabel("Rating: ");
        exerciseSearchLabel = new CustonButtonLabel("Modify w/ name: ");

        exerciseNameTextField = new CustomTextField(10);
        exerciseDescriptionTextField = new CustomTextField(40);
        exerciseNumberOfRepsTextField = new CustomTextField(2);
        exerciseNumberOfSetsTextField = new CustomTextField(2);
        exerciseRestTimeTextField = new CustomTextField(3);
        exerciseRatingTextField = new CustomTextField(1);
        exerciseSearchTextField = new CustomTextField(10);
    }

    private void setUpButton() {
        addExerciseButton = new JButton("+");
        deleteExerciseButton = new JButton("-");
        editExerciseButton = new JButton("Edit");
        toggleFavoriteExercisesButton = new JButton("View favorite exercises");
        toggleFavoriteExercisesButton.setPreferredSize(new Dimension(200, 25));
    }

    private void addExercise() {
        workout = WorkoutAppUI.getWorkout();
        try {
            Exercise exercise = new Exercise(exerciseNameTextField.getText(),
                    exerciseDescriptionTextField.getText(),
                    Integer.parseInt(exerciseNumberOfRepsTextField.getText()),
                    Integer.parseInt(exerciseNumberOfSetsTextField.getText()),
                    Integer.parseInt(exerciseRestTimeTextField.getText()),
                    Integer.parseInt(exerciseRatingTextField.getText()));
            workout.addExercise(exercise);
            defaultTableModel.addRow(exerciseToStringObject(exercise));
            resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    public void deleteExercise() {
        try {
            String exerciseName = exerciseNameTextField.getText();
            workout = WorkoutAppUI.getWorkout();
            exercises = workout.getExercises();
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exercises.remove(exercise);
                    updateExerciseTable(workout);
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    private void editExercise() {
        try {
            String exerciseName = exerciseSearchTextField.getText();

            exercises = WorkoutAppUI.getWorkout().getExercises();
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exercise.setExerciseName(exerciseNameTextField.getText());
                    handleEditDescription(exercise, exerciseDescriptionTextField.getText());
                    exercise.setExerciseNumOfReps(Integer.parseInt(exerciseNumberOfRepsTextField.getText()));
                    exercise.setExerciseNumOfSets(Integer.parseInt(exerciseNumberOfSetsTextField.getText()));
                    exercise.setExerciseRestTime(Integer.parseInt(exerciseRestTimeTextField.getText()));
                    exercise.setExerciseRating(Integer.parseInt(exerciseRatingTextField.getText()));

                    updateExerciseTable(WorkoutAppUI.getWorkout());
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    private void handleEditDescription(Exercise exercise, String exerciseDescriptionInput) {
        if (!exerciseDescriptionInput.equals("keep")) {
            exercise.setExerciseDescription(exerciseDescriptionInput);
        }
    }


    private void repopulateWithFavorites(boolean isFavoriteView) {
        if (!isFavoriteView) {
            workout = WorkoutAppUI.getWorkout();
            defaultTableModel.setRowCount(0);

            for (int i = 0; i < workout.exercisesSize(); i++) {
                if (workout.getExercise(i).getExerciseRating() == 5) {
                    defaultTableModel.addRow(exerciseToStringObject(workout.getExercise(i)));
                }
            }
            favoriteView = true;
            toggleFavoriteExercisesButton.setText("View all exercises");
        } else {
            workout = WorkoutAppUI.getWorkout();
            updateExerciseTable(workout);
            favoriteView = false;
            toggleFavoriteExercisesButton.setText("View favorite exercises");
        }
    }

    public static void updateExerciseTable(Workout workout) {
        defaultTableModel.setRowCount(0);

        for (int i = 0; i < workout.exercisesSize(); i++) {
            defaultTableModel.addRow(exerciseToStringObject(workout.getExercise(i)));
        }
    }

    private static String[] exerciseToStringObject(Exercise exercise) {
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