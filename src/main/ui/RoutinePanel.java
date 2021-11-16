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

public class RoutinePanel extends AbstractInternalFrame implements ActionListener {

    private boolean favoriteView;
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

    static String[] tableHeader = {"Name", "Description", "Included exercises", "Total time (s)", "Rating"};
    static String[] routineEntries = {};

    static DefaultTableModel defaultTableModel;

    NonEditableJTable table;
    JScrollPane scrollPane;

    private Workout workout;
    private List<Routine> routines;

    public RoutinePanel() {
        setTitle("Routine Panel");
        setBounds(350, 450, 825, 400);

        workout = WorkoutAppUI.getWorkout();
        favoriteView = false;
        setUp();
    }

    private void setUp() {
        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();
        setUpTableAndScrollPane();
        setUpActionListeners();
        workout = WorkoutAppUI.getWorkout();
        updateRoutineTable(workout);
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

        add(gridPanel, BorderLayout.SOUTH);
    }

    private void setUpTableAndScrollPane() {
        defaultTableModel = new DefaultTableModel(tableHeader, 1);

        table = new NonEditableJTable(defaultTableModel, 2);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(380);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0xFF9026));

        //credit: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
        table.getColumn("Included exercises").setCellRenderer(new ButtonRenderer());
        table.getColumn("Included exercises").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVisible(true);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setUpActionListeners() {
        addRoutineButton.addActionListener(event -> addRoutine());
        deleteRoutineButton.addActionListener(event -> deleteRoutine());
        editRoutineButton.addActionListener(event -> editRoutine());
        toggleFavoriteRoutinesButton.addActionListener(event -> repopulateWithFavorites());
        routineIncludedExercisesButton.addActionListener(event -> new ChoiceList());
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

    public void repopulateWithFavorites() {

        Workout workout = WorkoutAppUI.getWorkout();
        if (!favoriteView) {
            this.favoriteView = true;
            toggleFavoriteRoutinesButton.setText("View all routines");

            defaultTableModel.setRowCount(0);
            for (int i = 0; i < workout.routinesSize(); i++) {
                if (workout.getRoutine(i).getRoutineRating() == 5) {
                    defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
                }
            }
        } else {
            this.favoriteView = false;
            toggleFavoriteRoutinesButton.setText("View favorite routines");
            updateRoutineTable(workout);
        }
    }

    public static void updateRoutineTable(Workout workout) {
        defaultTableModel.setRowCount(0);

        for (int i = 0; i < workout.routinesSize(); i++) {
            defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
        }

    }

    public static String[] routineToStringObject(Routine routine) {

        String[] data = new String[5];

        data[0] = routine.getRoutineName();
        data[1] = routine.getRoutineDescription();

        data[2] = "view [" + (defaultTableModel.getRowCount() + 1) + "]";
        data[3] = routine.formatTotalTimeToComplete(routine.getTotalTimeToComplete());
        data[4] = routine.returnDefinedRating();

        return data;
    }

    private void resetTextFields() {
        routineNameTextField.setText("");
        routineDescriptionTextField.setText("");
        routineRatingTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
