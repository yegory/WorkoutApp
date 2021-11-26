package ui;

import model.Exercise;
import model.Routine;
import model.Workout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/*
    Represents the Routine Panel that is responsible for all actions related to adding,editing, deleting,
    and printing each routine onto a table.
    It also handles the Exercise table when clicking on "view exercises" button in the table.
    It also handles the ChoiceList/Button Renderer and Button Editor when clicking on "select exercises" in the JPanel
 */

public class RoutinePanel extends AbstractInternalFrame {

    public static boolean isFavoriteView() {
        return (toggleFavoriteRoutinesButton.getText().equals("View all routines"));
    }

    static JButton toggleFavoriteRoutinesButton;
    JButton addRoutineButton;
    JButton deleteRoutineButton;
    JButton editRoutineButton;
    JButton routineIncludedExercisesButton;

    CustomButtonLabel routineNameLabel;
    CustomButtonLabel routineDescriptionLabel;
    CustomButtonLabel routineIncludedExercisesLabel;
    CustomButtonLabel routineRatingLabel;
    CustomButtonLabel routineSearchLabel;

    CustomTextField routineNameTextField;
    CustomTextField routineDescriptionTextField;
    CustomTextField routineRatingTextField;
    CustomTextField routineSearchTextField;

    JPanel topFlowPanel;
    JPanel centerFlowPanel;
    JPanel bottomFlowPanel;
    JPanel gridPanel;

    static String[] tableHeader = {"Name", "Description", "Included exercises", "Total time (s)", "Rating"};

    static DefaultTableModel defaultTableModel;

    static NonEditableJTable table;
    JScrollPane scrollPane;

    private Workout workout;

    /*
        Constructs a Routine panel, with initial view set to all exercises (!favorite)
     */
    public RoutinePanel() {
        super("Routine Panel");
        setBounds(350, 425, 825, 425);

        workout = WorkoutAppUI.getWorkout();
        setUp();
    }

    /*
        MODIFIES: this
        Sets up all the components within this JInternalFrame
     */
    private void setUp() {
        workout = WorkoutAppUI.getWorkout();
        setUpJLabelsAndTextFields();
        setUpButton();
        setUpFlowPanels();
        setUpTableAndScrollPane();
        setUpActionListeners();
        updateRoutineTable(isFavoriteView());

        gridPanel = new JPanel(new GridLayout(3, 1));
        gridPanel.add(topFlowPanel);
        gridPanel.add(centerFlowPanel);
        gridPanel.add(bottomFlowPanel);

        add(gridPanel, BorderLayout.SOUTH);
    }

    private void setUpJLabelsAndTextFields() {
        routineNameLabel = new CustomButtonLabel("Name: ");
        routineDescriptionLabel = new CustomButtonLabel("Description: ");
        routineIncludedExercisesLabel = new CustomButtonLabel("Included exercises: ");
        routineRatingLabel = new CustomButtonLabel("Rating: ");
        routineSearchLabel = new CustomButtonLabel("Modify w/ name: ");

        routineNameTextField = new CustomTextField(10);
        routineDescriptionTextField = new CustomTextField(40);
        routineRatingTextField = new CustomTextField(2);
        routineSearchTextField = new CustomTextField(10);

    }

    /*
        EFFECTS: sets up buttons and adds action listeners to each
     */
    private void setUpButton() {
        addRoutineButton = new JButton("+");
        deleteRoutineButton = new JButton("-");
        editRoutineButton = new JButton("Edit");
        toggleFavoriteRoutinesButton = new JButton("View favorite routines");
        toggleFavoriteRoutinesButton.setPreferredSize(new Dimension(200, 25));

        routineIncludedExercisesButton = new JButton("select exercises");
    }

    /*
        EFFECTS: initializes flow panels and adds elements to the flow panels
     */
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

    /*
        MODIFIES: this
        EFFECTS: sets up the table
     */
    private void setUpTableAndScrollPane() {
        defaultTableModel = new DefaultTableModel(tableHeader, 1);

        table = new NonEditableJTable(defaultTableModel, 2);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(380);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);


        //credit: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
        table.getColumn("Included exercises").setCellRenderer(new ButtonRenderer());
        table.getColumn("Included exercises").setCellEditor(new ButtonEditor(new JCheckBox()));
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

    /*
        EFFECTS: adds action listeners to each button
     */
    private void setUpActionListeners() {
        addRoutineButton.addActionListener(event -> addRoutine());
        deleteRoutineButton.addActionListener(event -> deleteRoutine());
        editRoutineButton.addActionListener(event -> editRoutine());
        toggleFavoriteRoutinesButton.addActionListener(event -> repopulateWithFavoriteView());
        routineIncludedExercisesButton.addActionListener(event -> new ChoiceList());
    }

    /*
        MODIFIES: this, workout
        EFFECTS: adds routine to the list of routines in the workout file.
                Adds the routine to the table
     */
    private void addRoutine() {
        Workout workout = WorkoutAppUI.getWorkout();
        try {
            List<Exercise> chosenExercises = ChoiceList.createListExercise();
            Routine routine = new Routine(routineNameTextField.getText(),
                    routineDescriptionTextField.getText(), chosenExercises,
                    Integer.parseInt(routineRatingTextField.getText()));
            workout.addRoutine(routine);
            updateRoutineTable(isFavoriteView());
            resetTextFields();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    /*
        MODIFIES: this, workout
        EFFECTS: removes a routine from the list of routines in the workout file.
                Deletes the exercise from the table
     */
    private void deleteRoutine() {
        try {
            String routineName = routineSearchTextField.getText();
            workout = WorkoutAppUI.getWorkout();
            List<Routine> routines = workout.getRoutines();
            for (Routine routine : routines) {
                if (routine.getRoutineName().equals(routineName)) {
                    routines.remove(routine);
                    updateRoutineTable(isFavoriteView());
                    resetTextFields();
                    break;
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    /*
        MODIFIES: this, routine
        EFFECTS: edits a routine from the list of routines in the workout file.
                reflects the changes made to the routine in the table
     */
    private void editRoutine() {
        try {
            String routineName = routineSearchTextField.getText();
            workout = WorkoutAppUI.getWorkout();
            List<Routine> routines = workout.getRoutines();
            for (Routine routine : routines) {
                if (routine.getRoutineName().equals(routineName)) {
                    List<Exercise> chosenExercises = ChoiceList.createListExercise();
                    routine.setRoutineName(routineNameTextField.getText());
                    handleEditDescription(routine, routineDescriptionTextField.getText());
                    routine.setIncludedExercises(chosenExercises);
                    routine.setRoutineRating(Integer.parseInt(routineRatingTextField.getText()));
                    routine.updateTotalTimeToComplete();
                    updateRoutineTable(isFavoriteView());
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.toString());
        }
    }

    /*
        MODIFIES: routine
        EFFECTS: if the user's description input is "keep", this function does not alter the description
        (stays the same). Otherwise, routine's description is set to the input.
     */
    private void handleEditDescription(Routine routine, String routineDescriptionInput) {
        if (!routineDescriptionInput.equals("keep")) {
            routine.setRoutineDescription(routineDescriptionInput);
        }
    }

    /*
        MODIFIES: this
        EFFECTS: the function will flip the favoriteView boolean value. And call the update Function on the table.
            A new Button Renderer and Button Editor is made to ensure that the buttons are created in the correct cells.

            If the table was in favoriteView before the call to this function,
            the table's rows will get repopulated with all the current routines.
            Otherwise, only routines with rating equal to '5' will be displayed.
     */
    public void repopulateWithFavoriteView() {
        table.getColumn("Included exercises").setCellRenderer(new ButtonRenderer());
        table.getColumn("Included exercises").setCellEditor(new ButtonEditor(new JCheckBox()));
        updateRoutineTable(!isFavoriteView());
    }

    /*
        MODIFIES: this
        EFFECTS: updates the routine table. Updates the favoriteRoutineButton if needed.
     */
    public static void updateRoutineTable(boolean favoriteView) {
        defaultTableModel.setRowCount(0);

        Workout workout = WorkoutAppUI.getWorkout();

        if (favoriteView) {
            toggleFavoriteRoutinesButton.setText("View all routines");
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

    /*
        Creates a string object from a routine object
     */
    public static String[] routineToStringObject(Routine routine) {

        String[] data = new String[5];
        data[0] = routine.getRoutineName();
        data[1] = routine.getRoutineDescription();
        data[2] = "view [" + (defaultTableModel.getRowCount() + 1) + "]";
        data[3] = routine.formatTotalTimeToComplete(routine.getTotalTimeToComplete());
        data[4] = routine.returnDefinedRating();

        return data;
    }

    /*
       MODIFIES: this
       EFFECTS: resets the Text fields to empty (used to clear input boxes after doing some operation)
    */
    private void resetTextFields() {
        routineNameTextField.setText("");
        routineDescriptionTextField.setText("");
        routineRatingTextField.setText("");
        routineSearchTextField.setText("");
    }
}
