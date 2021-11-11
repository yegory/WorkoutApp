package ui;

import model.Exercise;
import model.Routine;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RoutinePanel extends WorkoutPanelPrototype implements ActionListener {

    private boolean isfavoriteView = false;
    private JButton toggleFavoriteRoutinesButton;
    private JButton addRoutineButton;
    private JButton deleteRoutineButton;
    private JButton editRoutineButton;
    JButton routineIncludedExercisesButton;

    private JLabel routineNameLabel;
    private JLabel routineDescriptionLabel;
    private JLabel routineIncludedExercisesLabel;
    private JLabel routineRatingLabel;

    private JTextField routineNameTextField;
    private JTextField routineDescriptionTextField;
    private JTextField routineRatingTextField;

    private JPanel topFlowPanel;
    private JPanel centerFlowPanel;
    private JPanel bottomFlowPanel;
    private JPanel gridPanel;

    static String[] tableHeader = {"Routine name", "Description", "Button", "Total time (sec)"};
    static String[] routineEntries = {"Routine name", "Description", "Included exercises", "Total time (sec)"};

    static DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);
    NonEditableJTableForRoutinePanel mainJTable = new NonEditableJTableForRoutinePanel(defaultTableModel);
    JScrollPane scrollPane = new JScrollPane(mainJTable);

    ExerciseTable exerciseTable;

    private ChoiceList choiceList;

    private Workout workout = WorkoutAppUI.getWorkoutApp().getWorkout();
    private List<Exercise> exercises = workout.getExercises();


    public RoutinePanel() {
        this.setTitle("Routine Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 450, 825, 400);

        updateRoutineTable(workout);

        mainJTable.getColumn("Button").setCellRenderer(new ButtonRenderer());
        mainJTable.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
        mainJTable.setPreferredScrollableViewportSize(mainJTable.getPreferredSize());
        mainJTable.getColumnModel().getColumn(0).setPreferredWidth(100);

        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();

        setUpTable();

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(gridPanel, BorderLayout.SOUTH);

        addRoutineButton.addActionListener(event -> addRoutine());
        deleteRoutineButton.addActionListener(event -> deleteRoutine());
        editRoutineButton.addActionListener(event -> editRoutine());
        toggleFavoriteRoutinesButton.addActionListener(event -> repopulateWithFavorites(isfavoriteView));
        routineIncludedExercisesButton.addActionListener(event -> selectExercises());
    }

    private void setUpTable() {
        scrollPane.setPreferredSize(new Dimension(500,300));
        scrollPane.setVisible(true);

        mainJTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        mainJTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        mainJTable.getColumnModel().getColumn(2).setPreferredWidth(15);
        mainJTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    }

    private void setUpFlowPanels() {
        topFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomFlowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridPanel = new JPanel(new GridLayout(3, 1));

        topFlowPanel.add(routineNameLabel);
        topFlowPanel.add(routineNameTextField);
        topFlowPanel.add(routineIncludedExercisesLabel);
        topFlowPanel.add(routineIncludedExercisesButton);

        centerFlowPanel.add(routineDescriptionLabel);
        centerFlowPanel.add(routineDescriptionTextField);
        centerFlowPanel.add(routineRatingLabel);
        centerFlowPanel.add(routineRatingTextField);

        bottomFlowPanel.add(addRoutineButton);
        bottomFlowPanel.add(deleteRoutineButton);
        bottomFlowPanel.add(editRoutineButton);
        bottomFlowPanel.add(toggleFavoriteRoutinesButton);

        gridPanel.add(topFlowPanel);
        gridPanel.add(centerFlowPanel);
        gridPanel.add(bottomFlowPanel);
    }

    private void setUpJLabelsAndTextFields() {
        routineNameLabel = new JLabel("Name: ");
        routineDescriptionLabel = new JLabel("Description: ");
        routineIncludedExercisesLabel = new JLabel("Included exercises: ");

        routineRatingLabel = new JLabel("Rating: ");

        routineNameTextField = new JTextField(10);
        routineDescriptionTextField = new JTextField(40);
        routineRatingTextField = new JTextField(2);

    }

    private void setUpButton() {
        addRoutineButton = new JButton("+");
        deleteRoutineButton = new JButton("-");
        editRoutineButton = new JButton("Edit");
        toggleFavoriteRoutinesButton = new JButton("View favorite routines");
        toggleFavoriteRoutinesButton.setPreferredSize(new Dimension(200,25));

        routineIncludedExercisesButton = new JButton("select exercises");
    }

    private void addRoutine() {

        Workout workout = WorkoutAppUI.getWorkoutApp().getWorkout();

        try {
            List<Exercise> chosenExercises = ChoiceList.createListExercise();
            Routine routine = new Routine(routineNameTextField.getText(),
                    routineDescriptionTextField.getText(), chosenExercises,
                    Integer.parseInt(routineRatingTextField.getText()));
            workout.addRoutine(routine);
            defaultTableModel.addRow(routineToStringObject(routine));
            resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    private void deleteRoutine() {
    }

    private void editRoutine() {
    }


    public void repopulateWithFavorites(boolean isFavoriteView) {

        Workout workout = WorkoutAppUI.getWorkoutApp().getWorkout();

        if (!isFavoriteView) {
            defaultTableModel.setRowCount(0);

            defaultTableModel.addRow(routineEntries);
            for (int i = 0; i < workout.routinesSize(); i++) {
                if (workout.getRoutine(i).getRoutineRating() == 5) {
                    defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
                }
            }
            isfavoriteView = true;
            toggleFavoriteRoutinesButton.setText("View all routines");
        } else {
            updateRoutineTable(workout);
            isfavoriteView = false;
            toggleFavoriteRoutinesButton.setText("View favorite routines");
        }
    }

    private void selectExercises() {
        ChoiceList choiceList = new ChoiceList();
    }


    public static void updateRoutineTable(Workout workout) {
        defaultTableModel.setRowCount(0);

        defaultTableModel.addRow(routineEntries);
        for (int i = 0; i < workout.routinesSize(); i++) {
            defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
        }
    }

    public static String[] routineToStringObject(Routine routine) {

        String[] data = new String[4];

        data[0] = routine.getRoutineName();
        data[1] = routine.getRoutineDescription();

        data[2] = "view [" + defaultTableModel.getRowCount() + "]";
        data[3] = routine.formatTotalTimeToComplete(routine.getTotalTimeToComplete());

        return data;
    }

    private void resetTextFields() {
        routineNameTextField.setText("");
        routineDescriptionTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
