package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FilePanel extends AbstractInternalFrame implements ActionListener {

    JPanel topPanel;
    JPanel bottomPanel;
    JPanel gridPanel;

    JLabel saveLoadLocationLabel;
    JTextArea saveLoadTextArea;

    JTextArea topTextArea;
    JButton loadButton;
    JButton saveButton;

    public FilePanel() {
        super("File Panel");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(25, 350, 250, 150);
        setResizable(false);

        setUpLabel();
        setUpButtons();
        setUpPanel();
        topPanel.add(saveLoadLocationLabel);
        topPanel.add(saveLoadTextArea);
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);

        add(gridPanel);
    }

    private void setUpLabel() {
        saveLoadLocationLabel = new JLabel("SAVE/LOAD Location: ");
        saveLoadTextArea = new JTextArea("./data/WorkoutAppData.json");
        saveLoadTextArea.setEditable(false);
        saveLoadTextArea.setFocusable(false);
        saveLoadTextArea.setBackground(new Color(0x118df0));
    }


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

    private void setUpButtons() {
        loadButton = new JButton("Load file");
        loadButton.addActionListener(this);
        saveButton = new JButton("Save file");
        saveButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
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
        } else if (e.getSource() == saveButton) {
            try {
                WorkoutAppUI.saveWorkout();
                JOptionPane.showMessageDialog(saveButton, "Successfully saved file", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException saveError) {
                JOptionPane.showMessageDialog(saveButton, "Could not save to file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}