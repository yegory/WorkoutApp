package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends WorkoutPanelPrototype implements ActionListener {

    private boolean isOpen;

    public HomePanel() {
        super();
        this.setTitle("Home Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(25, 25, 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
