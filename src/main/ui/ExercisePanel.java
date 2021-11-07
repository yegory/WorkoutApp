package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExercisePanel extends WorkoutPanelPrototype implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton("hello");

    public ExercisePanel() {
        super();
        this.setTitle("Exercise Panel");
        this.setBackground(WorkoutAppUI.WorkoutPanelColor);
        this.setBounds(350, 25, 400, 750);

        myButton.setBounds(0,0,100,40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        this.add(myButton);
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
