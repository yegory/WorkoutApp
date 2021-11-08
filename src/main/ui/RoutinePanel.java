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
        this.setBounds(350, 450, 825, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
