package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FilePanel extends WorkoutPanelPrototype implements ActionListener {

    JPanel leftLoadSavePanel;
    JPanel rightLoadSavePanel;
    JLabel fileLocationLabelTop;
    JLabel fileLocationLabelBottom;
    JButton loadButton;
    JButton saveButton;
    WorkoutApp wa = WorkoutAppUI.getWorkoutApp();

    public FilePanel() {
        super();
        this.setTitle("File Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(25, 350, 300, 300);

        setUpLabel();
        setUpButtons();
        setUpPanel();
        this.add(leftLoadSavePanel, BorderLayout.WEST);
        this.add(rightLoadSavePanel, BorderLayout.CENTER);
    }

    private void setUpLabel() {
        fileLocationLabelTop = new JLabel();
        fileLocationLabelTop.setText("SAVE/LOAD Location");
        fileLocationLabelTop.setVisible(true);
        fileLocationLabelTop.setBounds(0, 0, 200, 100);


        fileLocationLabelBottom = new JLabel();
        fileLocationLabelBottom.setText("./data/WorkoutAppData.json");
        fileLocationLabelBottom.setVisible(true);

        fileLocationLabelTop.setBounds(0, 50, 200, 100);
    }


    private void setUpPanel() {
        leftLoadSavePanel = new JPanel();
        leftLoadSavePanel.setPreferredSize(new Dimension(75, 100));
        leftLoadSavePanel.add(loadButton);
        leftLoadSavePanel.add(saveButton);
        leftLoadSavePanel.setVisible(true);
        leftLoadSavePanel.setBackground(new Color(0x000372));
        leftLoadSavePanel.add(loadButton);
        leftLoadSavePanel.add(saveButton);

        rightLoadSavePanel = new JPanel();
        rightLoadSavePanel.setPreferredSize(new Dimension(75, 100));
        rightLoadSavePanel.add(fileLocationLabelTop);
        rightLoadSavePanel.add(fileLocationLabelBottom);
        rightLoadSavePanel.setVisible(true);
        rightLoadSavePanel.setBackground(new Color(0xAEB286));
    }

    private void setUpButtons() {
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            try {
                wa.loadWorkout();
                System.out.println("load worked");
                WorkoutAppUI.updateExercisePanel(wa.getWorkout());
                JOptionPane.showMessageDialog(loadButton, "Successfully loaded file", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException loadError) {
                JOptionPane.showMessageDialog(loadButton, "Could not load file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == saveButton) {
            try {
                wa.saveWorkout();
                System.out.println("save worked");
                JOptionPane.showMessageDialog(saveButton, "Successfully saved file", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException saveError) {
                JOptionPane.showMessageDialog(saveButton, "Could not save to file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}