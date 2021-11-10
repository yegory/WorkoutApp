//package ui;
//
//import model.Exercise;
//import model.Workout;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class ExercisePanel extends WorkoutPanelPrototype implements ActionListener {
//
//    private static JTextArea exerciseTextArea;
//    private JScrollPane exerciseScrollPane;
//
//    private JButton toggleFavoriteExercisesButton;
//    private JButton addExerciseButton;
//    private JButton deleteExerciseButton;
//    private JButton editExerciseButton;
//
//    private JLabel exerciseNameLabel;
//    private JLabel exerciseDescriptionLabel;
//    private JLabel exerciseNumberOfRepsLabel;
//    private JLabel exerciseNumberOfSetsLabel;
//    private JLabel exerciseRestTimeLabel;
//    private JLabel exerciseRatingLabel;
//
//    private JTextField exerciseNameTextField;
//    private JTextField exerciseDescriptionTextField;
//    private JTextField exerciseNumberOfRepsTextField;
//    private JTextField exerciseNumberOfSetsTextField;
//    private JTextField exerciseRestTimeTextField;
//    private JTextField exerciseRatingTextField;
//
//    private JPanel topFlowPanel;
//    private JPanel centerFlowPanel;
//    private JPanel bottomFlowPanel;
//    private JPanel gridPanel;
//
//    private static List<Exercise> exercises;
//    private Workout workout;
//
//    public ExercisePanel() {
//        super();
//        this.setTitle("Exercise Panel");
//        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
//        this.setBounds(350, 25, 825, 400);
//
//        exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
//        workout = WorkoutAppUI.getWorkoutApp().getWorkout();
//
//        setUpJLabelsAndTextFields();
//        setUpButton();
//        setUpFlowPanels();
//
//        gridPanel.add(topFlowPanel);
//        gridPanel.add(centerFlowPanel);
//        gridPanel.add(bottomFlowPanel);
//
//        exerciseScrollPane = new JScrollPane(exerciseTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//        this.add(exerciseScrollPane, BorderLayout.CENTER);
//        this.add(gridPanel, BorderLayout.SOUTH);
//
//        addExerciseButton.addActionListener(event -> addExercise());
//        deleteExerciseButton.addActionListener(event -> deleteExercise());
////        editExerciseButton.addActionListener(event -> exitApplication ());
////        toggleFavoriteExercisesButton.addActionListener(event -> deleteStudent ());
//    }
//
//    private void setUpFlowPanels() {
//        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        gridPanel = new JPanel(new GridLayout(3, 1));
//        exerciseTextArea = new JTextArea();
//        exerciseTextArea.setEditable(false);
//
//        topFlowPanel.add(exerciseNameLabel);
//        topFlowPanel.add(exerciseNameTextField);
//        topFlowPanel.add(exerciseDescriptionLabel);
//        topFlowPanel.add(exerciseDescriptionTextField);
//
//        centerFlowPanel.add(exerciseNumberOfRepsLabel);
//        centerFlowPanel.add(exerciseNumberOfRepsTextField);
//        centerFlowPanel.add(exerciseNumberOfSetsLabel);
//        centerFlowPanel.add(exerciseNumberOfSetsTextField);
//        centerFlowPanel.add(exerciseRestTimeLabel);
//        centerFlowPanel.add(exerciseRestTimeTextField);
//        centerFlowPanel.add(exerciseRatingLabel);
//        centerFlowPanel.add(exerciseRatingTextField);
//
//        bottomFlowPanel.add(addExerciseButton);
//        bottomFlowPanel.add(deleteExerciseButton);
//        bottomFlowPanel.add(editExerciseButton);
//        bottomFlowPanel.add(toggleFavoriteExercisesButton);
//    }
//
//    private void setUpJLabelsAndTextFields() {
//        exerciseNameLabel = new JLabel("Name: ");
//        exerciseDescriptionLabel = new JLabel("Description: ");
//        exerciseNumberOfRepsLabel = new JLabel("Number of reps: ");
//        exerciseNumberOfSetsLabel = new JLabel("Number of sets: ");
//        exerciseRestTimeLabel = new JLabel("Rest time (sec): ");
//        exerciseRatingLabel = new JLabel("Rating: ");
//
//        exerciseNameTextField = new JTextField(10);
//        exerciseDescriptionTextField = new JTextField(40);
//        exerciseNumberOfRepsTextField = new JTextField(2);
//        exerciseNumberOfSetsTextField = new JTextField(2);
//        exerciseRestTimeTextField = new JTextField(3);
//        exerciseRatingTextField = new JTextField(1);
//    }
//
//    private void setUpButton() {
//        addExerciseButton = new JButton("+");
//        deleteExerciseButton = new JButton("-");
//        editExerciseButton = new JButton("Edit");
//        toggleFavoriteExercisesButton = new JButton("View favorite exercises");
//    }
//
//    private void addExercise() {
//        try {
//            Exercise exercise = new Exercise(exerciseNameTextField.getText(), exerciseDescriptionTextField.getText(),
//                    Integer.parseInt(exerciseNumberOfRepsTextField.getText()),
//                    Integer.parseInt(exerciseNumberOfSetsTextField.getText()),
//                    Integer.parseInt(exerciseRestTimeTextField.getText()),
//                    Integer.parseInt(exerciseRatingTextField.getText()));
//            exercises.add(exercise);
//            displayAllExercises();
//            resetTextFields();
//        } catch (Exception error) {
//            JOptionPane.showMessageDialog(null, error.toString());
//        }
//    }
//
//    private void deleteExercise() {
//        if (exercises.size() == 0) {
//            JOptionPane.showMessageDialog(null, "Error: Database is empty.");
//        } else if (workout.findExercise(exerciseNameTextField.getText())) {
//            JOptionPane.showMessageDialog(null, "Error: this exercise is not in the database.");
//        } else {
//            for (int i = 0; i < exercises.size(); i++) {
//                String exerciseName = exercises.get(i).getExerciseName();
//                if (exerciseName.compareToIgnoreCase(exerciseNameTextField.getText()) == 0) {
//                    exercises.remove(i);
//                    break;
//                }
//            }
//            displayAllExercises();
//            resetTextFields();
//        }
//    }
//
//    private void resetTextFields() {
//        exerciseNameTextField.setText("");
//        exerciseDescriptionTextField.setText("");
//        exerciseNumberOfRepsTextField.setText("");
//        exerciseNumberOfSetsTextField.setText("");
//        exerciseRestTimeTextField.setText("");
//        exerciseRatingTextField.setText("");
//    }
//
//    public static void displayAllExercises() {
//        exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
//        exerciseTextArea.setText("");
//        for (Exercise exercise : exercises) {
//            exerciseTextArea.append(exercise.getExerciseName() + " - "
//                    + "Info: " + exercise.getExerciseDescription() + " - "
//                    + "Reps: " + exercise.getExerciseNumOfReps() + " - "
//                    + "Sets: " + exercise.getExerciseNumOfSets() + " - "
//                    + "Rest (s): " + exercise.getExerciseRestTime() + " - "
//                    + "Rating: " + exercise.returnDefinedRating() + "\n\n");
//        }
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}

//package ui;
//
//import model.Exercise;
//import model.Workout;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class ExercisePanel extends WorkoutPanelPrototype implements ActionListener {
//
//    private JScrollPane exerciseScrollPane;
//
//    private JButton toggleFavoriteExercisesButton;
//    private JButton addExerciseButton;
//    private JButton deleteExerciseButton;
//    private JButton editExerciseButton;
//
//    private JLabel exerciseNameLabel;
//    private JLabel exerciseDescriptionLabel;
//    private JLabel exerciseNumberOfRepsLabel;
//    private JLabel exerciseNumberOfSetsLabel;
//    private JLabel exerciseRestTimeLabel;
//    private JLabel exerciseRatingLabel;
//
//    private JTextField exerciseNameTextField;
//    private JTextField exerciseDescriptionTextField;
//    private JTextField exerciseNumberOfRepsTextField;
//    private JTextField exerciseNumberOfSetsTextField;
//    private JTextField exerciseRestTimeTextField;
//    private JTextField exerciseRatingTextField;
//
//    private JPanel topFlowPanel;
//    private JPanel centerFlowPanel;
//    private JPanel bottomFlowPanel;
//    private JPanel gridPanel;
//
//    private Object[][] data = {{"Exercise name", "Description", "# of Reps", "# of Sets", "Rest time (sec)", "Rating"}};
//    private ExerciseTable exerciseTable;
//
//    private static List<Exercise> exercises;
//    private Workout workout;
//
//    private ExercisePanel exercisePanel;
//
//    public ExercisePanel() {
//        super();
//        this.setTitle("Exercise Panel");
//        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
//        this.setBounds(350, 25, 825, 400);
//
//        workout = WorkoutAppUI.getWorkoutApp().getWorkout();
//        exercises = workout.getExercises();
//
//        setUpJLabelsAndTextFields();
//        setUpButton();
//        setUpFlowPanels();
//
//        exerciseTable = new ExerciseTable();
//
//        gridPanel.add(topFlowPanel);
//        gridPanel.add(centerFlowPanel);
//        gridPanel.add(bottomFlowPanel);
//
//        this.add(exerciseTable, BorderLayout.CENTER);
//        this.add(gridPanel, BorderLayout.SOUTH);
//
//        addExerciseButton.addActionListener(event -> addExercise());
////        deleteExerciseButton.addActionListener(event -> deleteExercise());
////        editExerciseButton.addActionListener(event -> exitApplication ());
////        toggleFavoriteExercisesButton.addActionListener(event -> deleteStudent ());
//    }
//
//    public ExercisePanel(Object[][] data) {
//        super();
//        this.setTitle("Exercise Panel");
//        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
//        this.setBounds(350, 25, 825, 400);
//
//        exercises = WorkoutAppUI.getWorkoutApp().getWorkout().getExercises();
//        workout = WorkoutAppUI.getWorkoutApp().getWorkout();
//
//        exerciseTable = new ExerciseTable(data);
//
//        setUpJLabelsAndTextFields();
//        setUpButton();
//        setUpFlowPanels();
//
//        gridPanel.add(topFlowPanel);
//        gridPanel.add(centerFlowPanel);
//        gridPanel.add(bottomFlowPanel);
//
//        this.add(exerciseTable, BorderLayout.CENTER);
//        this.add(gridPanel, BorderLayout.SOUTH);
//
//        addExerciseButton.addActionListener(event -> addExercise());
////        deleteExerciseButton.addActionListener(event -> deleteExercise());
////        editExerciseButton.addActionListener(event -> exitApplication ());
////        toggleFavoriteExercisesButton.addActionListener(event -> deleteStudent ());
//    }
//
//    private void setUpFlowPanels() {
//        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        gridPanel = new JPanel(new GridLayout(3, 1));
//
//        topFlowPanel.add(exerciseNameLabel);
//        topFlowPanel.add(exerciseNameTextField);
//        topFlowPanel.add(exerciseDescriptionLabel);
//        topFlowPanel.add(exerciseDescriptionTextField);
//
//        centerFlowPanel.add(exerciseNumberOfRepsLabel);
//        centerFlowPanel.add(exerciseNumberOfRepsTextField);
//        centerFlowPanel.add(exerciseNumberOfSetsLabel);
//        centerFlowPanel.add(exerciseNumberOfSetsTextField);
//        centerFlowPanel.add(exerciseRestTimeLabel);
//        centerFlowPanel.add(exerciseRestTimeTextField);
//        centerFlowPanel.add(exerciseRatingLabel);
//        centerFlowPanel.add(exerciseRatingTextField);
//
//        bottomFlowPanel.add(addExerciseButton);
//        bottomFlowPanel.add(deleteExerciseButton);
//        bottomFlowPanel.add(editExerciseButton);
//        bottomFlowPanel.add(toggleFavoriteExercisesButton);
//    }
//
//    private void setUpJLabelsAndTextFields() {
//        exerciseNameLabel = new JLabel("Name: ");
//        exerciseDescriptionLabel = new JLabel("Description: ");
//        exerciseNumberOfRepsLabel = new JLabel("Number of reps: ");
//        exerciseNumberOfSetsLabel = new JLabel("Number of sets: ");
//        exerciseRestTimeLabel = new JLabel("Rest time (sec): ");
//        exerciseRatingLabel = new JLabel("Rating: ");
//
//        exerciseNameTextField = new JTextField(10);
//        exerciseDescriptionTextField = new JTextField(40);
//        exerciseNumberOfRepsTextField = new JTextField(2);
//        exerciseNumberOfSetsTextField = new JTextField(2);
//        exerciseRestTimeTextField = new JTextField(3);
//        exerciseRatingTextField = new JTextField(1);
//    }
//
//    private void setUpButton() {
//        addExerciseButton = new JButton("+");
//        deleteExerciseButton = new JButton("-");
//        editExerciseButton = new JButton("Edit");
//        toggleFavoriteExercisesButton = new JButton("View favorite exercises");
//    }
//
//    private void addExercise() {
//        try {
//            Exercise exercise = new Exercise(exerciseNameTextField.getText(), exerciseDescriptionTextField.getText(),
//                    Integer.parseInt(exerciseNumberOfRepsTextField.getText()),
//                    Integer.parseInt(exerciseNumberOfSetsTextField.getText()),
//                    Integer.parseInt(exerciseRestTimeTextField.getText()),
//                    Integer.parseInt(exerciseRatingTextField.getText()));
//            exercises.add(exercise);
//            displayAllExercises();
//            //resetTextFields();
//        } catch (Exception error) {
//            JOptionPane.showMessageDialog(null, error.toString());
//        }
//    }
//
//    private void displayAllExercises() {
//        WorkoutAppUI.updateExercisePanel(ExerciseTable.update(exercises));
//    }
//
//    private void resetTextFields() {
//        exerciseNameTextField.setText("");
//        exerciseDescriptionTextField.setText("");
//        exerciseNumberOfRepsTextField.setText("");
//        exerciseNumberOfSetsTextField.setText("");
//        exerciseRestTimeTextField.setText("");
//        exerciseRatingTextField.setText("");
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}

package ui;

import model.Exercise;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExercisePanel extends WorkoutPanelPrototype implements ActionListener {

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

    ExerciseJTable mainJTable = new ExerciseJTable(defaultTableModel);
    JScrollPane exerciseScrollPane = new JScrollPane(mainJTable);


    private static List<Exercise> exercises;
    private static Workout workout;


    public ExercisePanel() {
        super();
        this.setTitle("Exercise Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 25, 825, 400);

        workout = WorkoutAppUI.getWorkoutApp().getWorkout();
        exercises = workout.getExercises();

        updateExerciseTable(workout);


        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();

        setUpTable();

        this.add(exerciseScrollPane, BorderLayout.CENTER);
        this.add(gridPanel, BorderLayout.SOUTH);

        addExerciseButton.addActionListener(event -> addExercise());
//        deleteExerciseButton.addActionListener(event -> deleteExercise());
//        editExerciseButton.addActionListener(event -> exitApplication ());
//        toggleFavoriteExercisesButton.addActionListener(event -> deleteStudent ());
    }

    private void setUpTable() {
        exerciseScrollPane.setPreferredSize(new Dimension(500,300));
        exerciseScrollPane.setVisible(true);

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
    }

    private void addExercise() {
        try {
            Exercise exercise = new Exercise(exerciseNameTextField.getText(),
                    exerciseDescriptionTextField.getText(),
                    Integer.parseInt(exerciseNumberOfRepsTextField.getText()),
                    Integer.parseInt(exerciseNumberOfSetsTextField.getText()),
                    Integer.parseInt(exerciseRestTimeTextField.getText()),
                    Integer.parseInt(exerciseRatingTextField.getText()));
            exercises.add(exercise);
            //updateExerciseTable(workout);
            defaultTableModel.addRow(exerciseToStringObject(exercise));
            //resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
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

    public static void updateExerciseTable(Workout workout) {
        defaultTableModel.setRowCount(0);

        defaultTableModel.addRow(exerciseEntries);
        for (int i = 0; i < workout.exercisesSize(); i++) {
            defaultTableModel.addRow(exerciseToStringObject(workout.getExercise(i)));
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}