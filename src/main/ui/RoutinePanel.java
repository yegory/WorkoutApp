package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutinePanel extends WorkoutPanelPrototype implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton();

    public RoutinePanel() {
        this.setTitle("Routine Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(775, 25, 400, 750);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
