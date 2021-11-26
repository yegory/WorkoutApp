package ui;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/*
    Represents the Exercise Panel that is responsible for all actions related to adding,editing, deleting,
    and printing each exercise onto a table.
 */

public class ExercisePanel extends AbstractInternalFrame {

    private boolean favoriteView = false;
    JButton toggleFavoriteExercisesButton;
    JButton addExerciseButton;
    JButton deleteExerciseButton;
    JButton editExerciseButton;

    CustomButtonLabel exerciseNameLabel;
    CustomButtonLabel exerciseDescriptionLabel;
    CustomButtonLabel exerciseNumberOfRepsLabel;
    CustomButtonLabel exerciseNumberOfSetsLabel;
    CustomButtonLabel exerciseRestTimeLabel;
    CustomButtonLabel exerciseRatingLabel;
    CustomButtonLabel exerciseSearchLabel;

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

    static DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);

    NonEditableJTable table = new NonEditableJTable(defaultTableModel, 1);
    JScrollPane scrollPane = new JScrollPane(table);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    private static List<Exercise> exercises;
    private static Workout workout = WorkoutAppUI.getWorkout();

    /*
        EFFECTS: Constructs an Exercise panel
     */
    public ExercisePanel() {
        super("Exercise Panel");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(350, 0, 825, 425);

        setUp();
    }

    /*
        MODIFIES: this
        EFFECTS: initializes all the UI elements inside the Exercise Panel
     */
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

    /*
        EFFECTS: adds action listeners to each button
     */
    private void setUpActionListeners() {
        addExerciseButton.addActionListener(event -> addExercise());
        deleteExerciseButton.addActionListener(event -> deleteExercise());
        editExerciseButton.addActionListener(event -> editExercise());
        toggleFavoriteExercisesButton.addActionListener(event -> repopulateWithFavorites(!favoriteView));
    }

    /*
        MODIFIES: this
        EFFECTS: sets up the table
     */
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

    /*
        EFFECTS: initializes flow panels
     */
    private void initializeUpFlowPanels() {
        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topFlowPanel.setBackground(new Color(0x696969));
        centerFlowPanel.setBackground(new Color(0x868686));
        bottomFlowPanel.setBackground(new Color(0x696969));
    }

    /*
        EFFECTS: adds elements to the flow panels
     */
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

    /*
        EFFECTS: initializes text fields and Labels
     */
    private void setUpJLabelsAndTextFields() {
        exerciseNameLabel = new CustomButtonLabel("Name: ");
        exerciseDescriptionLabel = new CustomButtonLabel("Description: ");
        exerciseNumberOfRepsLabel = new CustomButtonLabel("Number of reps: ");
        exerciseNumberOfSetsLabel = new CustomButtonLabel("Number of sets: ");
        exerciseRestTimeLabel = new CustomButtonLabel("Rest time (sec): ");
        exerciseRatingLabel = new CustomButtonLabel("Rating: ");
        exerciseSearchLabel = new CustomButtonLabel("Modify w/ name: ");

        exerciseNameTextField = new CustomTextField(10);
        exerciseDescriptionTextField = new CustomTextField(40);
        exerciseNumberOfRepsTextField = new CustomTextField(2);
        exerciseNumberOfSetsTextField = new CustomTextField(2);
        exerciseRestTimeTextField = new CustomTextField(3);
        exerciseRatingTextField = new CustomTextField(1);
        exerciseSearchTextField = new CustomTextField(10);
    }

    /*
        EFFECTS: sets up buttons and adds action listeners to each
     */
    private void setUpButton() {
        addExerciseButton = new JButton("+");
        deleteExerciseButton = new JButton("-");
        editExerciseButton = new JButton("Edit");
        toggleFavoriteExercisesButton = new JButton("View favorite exercises");
        toggleFavoriteExercisesButton.setPreferredSize(new Dimension(200, 25));
    }

    /*
        MODIFIES: this, workout
        EFFECTS: adds exercise to the list of exercises in the workout file.
                 Adds the exercise to the table
     */
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
            repopulateWithFavorites(favoriteView);
            resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    /*
        MODIFIES: this, workout
        EFFECTS: removes an exercise from the list of exercises in the workout file.
                Deletes the exercise from the table.
     */
    public void deleteExercise() {
        try {
            String exerciseName = exerciseSearchTextField.getText();
            workout = WorkoutAppUI.getWorkout();
            exercises = workout.getExercises();
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exercises.remove(exercise);
                    repopulateWithFavorites(favoriteView);
                    resetTextFields();
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    /*
        MODIFIES: this, exercise
        EFFECTS: edits an exercise from the list of exercises in the workout file. and reflects the changes made
        to the exercise in the table.
     */
    private void editExercise() {
        try {
            String exerciseName = exerciseSearchTextField.getText();

            Workout workout = WorkoutAppUI.getWorkout();
            exercises = workout.getExercises();
            for (Exercise exercise : exercises) {
                if (exercise.getExerciseName().equals(exerciseName)) {
                    exercise.setExerciseName(exerciseNameTextField.getText());
                    handleEditDescription(exercise, exerciseDescriptionTextField.getText());
                    exercise.setExerciseNumOfReps(Integer.parseInt(exerciseNumberOfRepsTextField.getText()));
                    exercise.setExerciseNumOfSets(Integer.parseInt(exerciseNumberOfSetsTextField.getText()));
                    exercise.setExerciseRestTime(Integer.parseInt(exerciseRestTimeTextField.getText()));
                    exercise.setExerciseRating(Integer.parseInt(exerciseRatingTextField.getText()));
                    repopulateWithFavorites(favoriteView);
                    resetTextFields();
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    /*
        MODIFIES: exercise
        EFFECTS: if the user's description input is "keep", this function does not alter the description
        (stays the same). Otherwise, exercise's description is set to the input.
     */
    private void handleEditDescription(Exercise exercise, String exerciseDescriptionInput) {
        if (!exerciseDescriptionInput.equals("keep")) {
            exercise.setExerciseDescription(exerciseDescriptionInput);
        }
    }

    /*
        MODIFIES: this
        EFFECTS: depending on the favoriteView parameter, the function will flip the text on the favoriteButton
        to the polar text, and build up the table with the favoriteView in mind. If the table was in favoriteView before
        the call to this function, the table's rows will get repopulated with all the current exercises.
        Otherwise, only exercises with rating of '5' will be displayed.
     */
    private void repopulateWithFavorites(boolean favoriteView) {

        workout = WorkoutAppUI.getWorkout();

        if (favoriteView) {
            defaultTableModel.setRowCount(0);

            for (int i = 0; i < workout.exercisesSize(); i++) {
                if (workout.getExercise(i).getExerciseRating() == 5) {
                    defaultTableModel.addRow((workout.getExercise(i)).exerciseToStringObject());
                }
            }
            toggleFavoriteExercisesButton.setText("View all exercises");
        } else {
            updateExerciseTable(workout);
            toggleFavoriteExercisesButton.setText("View favorite exercises");
        }
        this.favoriteView = favoriteView;
    }

    /*
        MODIFIES: this
        EFFECTS: builds up the table without favorite view in mind. All exercises will be displayed in the table.
     */
    public static void updateExerciseTable(Workout workout) {
        defaultTableModel.setRowCount(0);

        for (int i = 0; i < workout.exercisesSize(); i++) {
            defaultTableModel.addRow((workout.getExercise(i).exerciseToStringObject()));
        }
    }

    /*
        MODIFIES: this
        EFFECTS: resets the Text fields to empty (used to clear input boxes after doing some operation)
     */
    private void resetTextFields() {
        exerciseNameTextField.setText("");
        exerciseDescriptionTextField.setText("");
        exerciseNumberOfRepsTextField.setText("");
        exerciseNumberOfSetsTextField.setText("");
        exerciseRestTimeTextField.setText("");
        exerciseRatingTextField.setText("");
        exerciseSearchTextField.setText("");
    }
}