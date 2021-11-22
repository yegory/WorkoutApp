package ui;

import javafx.beans.InvalidationListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
    Represents the File Panel that is responsible for loading and saving actions. No implementation to change which
    file it will save to.
 */

public class FilePanel extends AbstractInternalFrame implements ActionListener {

    JPanel topPanel;
    JPanel bottomPanel;
    JPanel gridPanel;

    JLabel saveLoadLocationLabel;
    JTextArea saveLoadTextArea;

    JButton loadButton;
    JButton saveButton;

    /*
        Constructs a File panel as a small non-resizable JFrame
     */
    public FilePanel() {
        super("File Panel");
        setBounds(50, 0, 250, 150);
        setResizable(false);

        setUp();
    }

    /*
        MODIFIES: this
        EFFECTS: adds all elements to the main frame of File Panel
     */
    private void setUp() {
        setUpLabel();
        setUpButtons();
        setUpPanel();
        topPanel.add(saveLoadLocationLabel);
        topPanel.add(saveLoadTextArea);
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);

        add(gridPanel);
    }

    /*
        initializes the JLabel and JTextArea
     */
    private void setUpLabel() {
        saveLoadLocationLabel = new JLabel("SAVE/LOAD Location: ");
        saveLoadTextArea = new JTextArea("./data/WorkoutAppData.json");
        saveLoadTextArea.setEditable(false);
        saveLoadTextArea.setFocusable(false);
        saveLoadTextArea.setBackground(new Color(0x118df0));
    }

    /*
        initializes and adds panels to the File panel.
     */
    private void setUpPanel() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(0x118df0));
        bottomPanel.setBackground(new Color(0x0B69AD));

        gridPanel = new JPanel(new GridLayout(2,1));

        gridPanel.add(topPanel);
        gridPanel.add(bottomPanel);
        gridPanel.setVisible(true);
    }

    /*
        Initialize buttons and adds action listeners to each one.
     */
    private void setUpButtons() {
        loadButton = new JButton("Load file");
        loadButton.addActionListener(this);
        saveButton = new JButton("Save file");
        saveButton.addActionListener(this);
    }

    /*
        MODIFIES: WorkoutAppUI, workout
        EFFECTS: Handles the loading functionality of the file. If load is successful, the Exercise Panel
                and Routine Panel are updated and a success dialogue is shown.
                Otherwise, an error dialogue is displayed.
     */
    private void handleLoadWorkout() {
        try {
            WorkoutAppUI.loadWorkout();
            WorkoutAppUI.updateExercisePanel(WorkoutAppUI.getWorkout());
            WorkoutAppUI.updateRoutinePanel();
            JOptionPane.showMessageDialog(loadButton, "Successfully loaded file", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException loadError) {
            JOptionPane.showMessageDialog(loadButton, "Could not load file", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
        MODIFIES: WorkoutAppUI, workout
        EFFECTS: Handles the saving functionality of the file. If save is successful, a success dialogue is shown.
                Otherwise, an error dialogue is displayed.
     */
    private void handleSaveWorkout() {
        try {
            WorkoutAppUI.saveWorkout();
            JOptionPane.showMessageDialog(saveButton, "Successfully saved file", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException saveError) {
            JOptionPane.showMessageDialog(saveButton, "Could not save to file", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            handleLoadWorkout();
        } else if (e.getSource() == saveButton) {
            handleSaveWorkout();
        }
    }
}