package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FilePanel extends AbstractInternalFrame implements ActionListener {

    JPanel leftLoadSavePanel;
    JPanel rightLoadSavePanel;
    JLabel fileLocationLabelTop;
    JLabel fileLocationLabelBottom;
    JButton loadButton;
    JButton saveButton;

    public FilePanel() {
        super();
        setTitle("File Panel");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(25, 350, 300, 300);

        setUpLabel();
        setUpButtons();
        setUpPanel();
        add(leftLoadSavePanel, BorderLayout.WEST);
        add(rightLoadSavePanel, BorderLayout.CENTER);
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