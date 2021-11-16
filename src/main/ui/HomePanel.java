package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends AbstractInternalFrame implements ActionListener {

    JPanel panel;
    JPanel topPanel;
    JPanel bottomPanel;
    JLabel homeLabel;
    JLabel credit;
    JLabel credits;

    public HomePanel() {
        super();
        setTitle("Home Panel");
        setBackground(WorkoutAppUI.WorkoutPanelColor);
        setBounds(25, 25, 300, 250);

        setUpLabel();
        setUpPanel();

        add(homeLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void setUpPanel() {
        panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0x0));

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());
        topPanel.add(credit);
        topPanel.setVisible(true);
        topPanel.setPreferredSize(new Dimension(500, 50));
        topPanel.setBackground(new Color(0x3C4BEF));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout());
        bottomPanel.add(credits);
        bottomPanel.setVisible(true);
        bottomPanel.setPreferredSize(new Dimension(100, 200));
        bottomPanel.setBackground(new Color(0x3C4BEF));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);
    }


    private void setUpLabel() {
        homeLabel = new JLabel("Made by Yegor Yeryomenko");
        homeLabel.setVisible(true);
        homeLabel.setBounds(0, 0, 200, 100);
        homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeLabel.setPreferredSize(new Dimension(100, 40));
        homeLabel.setFont(new Font("Verdana", Font.PLAIN, 18));

        credit = new JLabel("<html><br>CREDITS</html>");
        credit.setVisible(true);
        credit.setFont(new Font("Verdana", Font.PLAIN, 16));
        credit.setBounds(0, 0, 200, 50);
        credit.setHorizontalAlignment(SwingConstants.CENTER);

        credits = new JLabel("<html>Save and load functionality inspired<br>from JsonSerializationDemo -"
                + "<br>github.students.cs.ubc.ca<br>/CPSC210/JsonSerializationDemo</html>");
        credits.setVisible(true);
        credits.setBounds(0, 0, 200, 50);
        credits.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
