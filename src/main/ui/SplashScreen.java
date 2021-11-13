package ui;

import javax.swing.*;
import java.awt.*;

/*
learnt this from https://www.youtube.com/watch?v=JEI-fcfnFkc (Bro Code on YouTube)

image taken from: https://www.today.com/series/one-small-thing/5-day-workout-plan-women-strengthen-tone-muscles-t166294
 */

public class SplashScreen extends JFrame {

    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JLabel topLabel = new JLabel("Cleaning up the gym...");
    JProgressBar progressBar = new JProgressBar();

    ImageIcon imageIcon = new ImageIcon("dumbbells.jpg");

    public SplashScreen() {
        setTitle("Workout App");
        setVisible(true);
        setSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topPanel.setBackground(new Color(0x73c4e2));

        topPanel.setPreferredSize(new Dimension(400, 100));
        centerPanel.add(new JLabel(imageIcon));

        topPanel.setLayout(new BorderLayout());
        topLabel.setFont(new Font("Cooper Black", Font.BOLD, 32));
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(topLabel, BorderLayout.CENTER);

        progressBar.setValue(0);
        progressBar.setBounds(0, 0, 400, 100);
        progressBar.setFont(new Font("MV Boli", Font.BOLD, 25));
        progressBar.setStringPainted(true);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        centreOnScreen();
        fill();
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public void fill() {
        int progress = 0;

        while (progress <= 99) {
            if (progress >= 25 && progress < 65) {
                topLabel.setText("Playing Enter Sandman...");
            } else if (progress >= 65) {
                topLabel.setText("Turning off the lunk alarm...");
            }
            progressBar.setValue(progress);
            try {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress += 1;
        }
        topLabel.setText("Almost done :)");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispose();
    }
}
