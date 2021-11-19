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

    private static boolean favoriteView;
    static JButton toggleFavoriteRoutinesButton;
    JButton addRoutineButton;
    JButton deleteRoutineButton;
    JButton editRoutineButton;
    JButton routineIncludedExercisesButton;

    CustonButtonLabel routineNameLabel;
    CustonButtonLabel routineDescriptionLabel;
    CustonButtonLabel routineIncludedExercisesLabel;
    CustonButtonLabel routineRatingLabel;
    CustonButtonLabel routineSearchLabel;

    CustomTextField routineNameTextField;
    CustomTextField routineDescriptionTextField;
    CustomTextField routineRatingTextField;
    CustomTextField routineSearchTextField;

    JPanel topFlowPanel;
    JPanel centerFlowPanel;
    JPanel bottomFlowPanel;
    JPanel gridPanel;

    static String[] tableHeader = {"Name", "Description", "Included exercises", "Total time (s)", "Rating"};
    static String[] routineEntries = {};

    static DefaultTableModel defaultTableModel;

    static NonEditableJTable table;
    JScrollPane scrollPane;

    private Workout workout;
    private List<Routine> routines;

    public RoutinePanel() {
        super("Routine Panel");
        setBounds(350, 425, 825, 425);

        workout = WorkoutAppUI.getWorkout();
        favoriteView = false;
        setUp();
    }

    private void setUp() {
        workout = WorkoutAppUI.getWorkout();
        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();
        setUpTableAndScrollPane();
        setUpActionListeners();
        updateRoutineTable();

        gridPanel = new JPanel(new GridLayout(3, 1));
        gridPanel.add(topFlowPanel);
        gridPanel.add(centerFlowPanel);
        gridPanel.add(bottomFlowPanel);

        add(gridPanel, BorderLayout.SOUTH);
    }

    private void setUpJLabelsAndTextFields() {
        routineNameLabel = new CustonButtonLabel("Name: ");
        routineDescriptionLabel = new CustonButtonLabel("Description: ");
        routineIncludedExercisesLabel = new CustonButtonLabel("Included exercises: ");
        routineRatingLabel = new CustonButtonLabel("Rating: ");
        routineSearchLabel = new CustonButtonLabel("Modify w/ name: ");

        routineNameTextField = new CustomTextField(10);
        routineDescriptionTextField = new CustomTextField(40);
        routineRatingTextField = new CustomTextField(2);
        routineSearchTextField = new CustomTextField(10);

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

        topFlowPanel.setBackground(new Color(0x696969));
        centerFlowPanel.setBackground(new Color(0x868686));
        bottomFlowPanel.setBackground(new Color(0x696969));

        topFlowPanel.add(routineNameLabel);
        topFlowPanel.add(routineNameTextField);
        topFlowPanel.add(routineIncludedExercisesLabel);
        topFlowPanel.add(routineIncludedExercisesButton);
        centerFlowPanel.add(routineDescriptionLabel);
        centerFlowPanel.add(routineDescriptionTextField);
        centerFlowPanel.add(routineRatingLabel);
        centerFlowPanel.add(routineRatingTextField);
        bottomFlowPanel.add(routineSearchLabel);
        bottomFlowPanel.add(routineSearchTextField);
        bottomFlowPanel.add(addRoutineButton);
        bottomFlowPanel.add(deleteRoutineButton);
        bottomFlowPanel.add(editRoutineButton);
        bottomFlowPanel.add(toggleFavoriteRoutinesButton);
    }

    private void setUpTableAndScrollPane() {
        defaultTableModel = new DefaultTableModel(tableHeader, 1);

        table = new NonEditableJTable(defaultTableModel, 2);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(380);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);


        //credit: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
        table.getColumn("Included exercises").setCellRenderer(new ButtonRenderer());
        table.getColumn("Included exercises").setCellEditor(new ButtonEditor(new JCheckBox(), favoriteView));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0xfca311));
        table.setBackground(new Color(0xe5e5e5));
        table.setRowHeight(25);
        table.setRowMargin(5);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setVisible(true);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setUpActionListeners() {
        addRoutineButton.addActionListener(event -> addRoutine());
        deleteRoutineButton.addActionListener(event -> deleteRoutine());
        editRoutineButton.addActionListener(event -> editRoutine());
        toggleFavoriteRoutinesButton.addActionListener(event -> repopulateWithFavoriteView(favoriteView));
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
                    updateRoutineTable();
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
                    updateRoutineTable();
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

    public static void repopulateWithFavoriteView(boolean favoriteView) {
        table.getColumn("Included exercises").setCellRenderer(new ButtonRenderer());
        table.getColumn("Included exercises").setCellEditor(new ButtonEditor(new JCheckBox(), !favoriteView));
        setFavoriteView(!favoriteView);
        updateRoutineTable();
    }

    // MODIFIES: this
    // REQUIRES:
    // EFFECTS: updates the routine table depending on
    public static void updateRoutineTable() {
        defaultTableModel.setRowCount(0);

        Workout workout = WorkoutAppUI.getWorkout();
        if (favoriteView) {
            for (int i = 0; i < workout.routinesSize(); i++) {
                if (workout.getRoutine(i).getRoutineRating() == 5) {
                    defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
                }
            }
        } else {
            toggleFavoriteRoutinesButton.setText("View favorite routines");
            for (int i = 0; i < workout.routinesSize(); i++) {
                defaultTableModel.addRow(routineToStringObject(workout.getRoutine(i)));
            }
        }
    }


    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public static String[] routineToStringObject(Routine routine) {

        String[] data = new String[5];

        data[0] = routine.getRoutineName();
        data[1] = routine.getRoutineDescription();

        data[2] = "view [" + (defaultTableModel.getRowCount() + 1) + "]";
        data[3] = routine.formatTotalTimeToComplete(routine.getTotalTimeToComplete());
        data[4] = routine.returnDefinedRating();

        return data;
    }

    public static boolean getFavoriteView() {
        return favoriteView;
    }

    public static void setFavoriteView(boolean view) {
        favoriteView = view;
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
