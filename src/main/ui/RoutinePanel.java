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

    private boolean favoriteView = false;
    JButton toggleFavoriteRoutinesButton;
    JButton addRoutineButton;
    JButton deleteRoutineButton;
    JButton editRoutineButton;
    JButton routineIncludedExercisesButton;

    JLabel routineNameLabel;
    JLabel routineDescriptionLabel;
    JLabel routineIncludedExercisesLabel;
    JLabel routineRatingLabel;

    JTextField routineNameTextField;
    JTextField routineDescriptionTextField;
    JTextField routineRatingTextField;

    JPanel topFlowPanel;
    JPanel centerFlowPanel;
    JPanel bottomFlowPanel;
    JPanel gridPanel;

    static String[] tableHeader = {"Routine name", "Description", "Included exercises", "Total time (sec)"};
    static String[] routineEntries = {};

    static DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 1);
    NonEditableJTableForRoutinePanel table = new NonEditableJTableForRoutinePanel(defaultTableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    private Workout workout = WorkoutAppUI.getWorkout();
    private List<Routine> routines;

    public RoutinePanel() {
        this.setTitle("Routine Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 450, 825, 400);

        updateRoutineTable(workout);

        table.getColumn("Included exercises").setCellRenderer(new ButtonRenderer());
        table.getColumn("Included exercises").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();

        setUpTable();

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(gridPanel, BorderLayout.SOUTH);

        addRoutineButton.addActionListener(event -> addRoutine());
        deleteRoutineButton.addActionListener(event -> deleteRoutine());
        editRoutineButton.addActionListener(event -> editRoutine());
        toggleFavoriteRoutinesButton.addActionListener(event -> repopulateWithFavorites(favoriteView));
        routineIncludedExercisesButton.addActionListener(event -> new ChoiceList());
    }

    private void setUpTable() {
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVisible(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);

        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0xFF9026));
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
        toggleFavoriteRoutinesButton.setPreferredSize(new Dimension(200, 25));

        routineIncludedExercisesButton = new JButton("select exercises");
    }

    private void addRoutine() {
        Workout workout = WorkoutAppUI.getWorkout();
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
        try {
            String routineName = routineNameTextField.getText();
            workout = WorkoutAppUI.getWorkout();
            routines = workout.getRoutines();
            for (Routine routine : routines) {
                if (routine.getRoutineName().equals(routineName)) {
                    routines.remove(routine);
                    updateRoutineTable(workout);
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    private void editRoutine() {
        try {
            String routineName = routineNameTextField.getText();
            workout = WorkoutAppUI.getWorkout();
            routines = workout.getRoutines();
            for (Routine routine : routines) {
                if (routine.getRoutineName().equals(routineName)) {
                    List<Exercise> chosenExercises = ChoiceList.createListExercise();
                    routine.setRoutineDescription(routineDescriptionTextField.getText());
                    routine.setIncludedExercises(chosenExercises);
                    routine.setRoutineRating(Integer.parseInt(routineRatingTextField.getText()));
                    routine.updateTotalTimeToComplete();
                    updateRoutineTable(workout);
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    public void repopulateWithFavorites(boolean isFavoriteView) {
        Workout workout = WorkoutAppUI.getWorkout();
        if (!isFavoriteView) {
            defaultTableModel.setRowCount(0);

            defaultTableModel.addRow(routineEntries);
            for (int i = 0; i < workout.routinesSize(); i++) {
                if (workout.getRoutine(i).getRoutineRating() == 5) {
                    defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
                }
            }
            favoriteView = true;
            toggleFavoriteRoutinesButton.setText("View all routines");
        } else {
            updateRoutineTable(workout);
            favoriteView = false;
            toggleFavoriteRoutinesButton.setText("View favorite routines");
        }
    }

    public static void updateRoutineTable(Workout workout) {
        defaultTableModel.setRowCount(0);

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
