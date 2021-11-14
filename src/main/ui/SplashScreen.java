package ui;

import javax.swing.*;
import java.awt.*;

/*
learnt this from https://www.youtube.com/watch?v=JEI-fcfnFkc (Bro Code on YouTube)

dumbbells image taken from: https://www.today.com/series/one-small-thing/5-day-workout-plan-women-strengthen-tone-muscles-t166294

metallica logo found here: https://wallpaperaccess.com/metallica-logo

No hands push up image taken from: http://www.hoaxorfact.com/pranks/russian-soldier-no-hands-push-ups.html
 */

public class SplashScreen extends JFrame {

    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JLabel topLabel = new JLabel("Cleaning up the gym...");
    JProgressBar progressBar = new JProgressBar();

    ImageIcon imageIconDumbbells = new ImageIcon("dumbbells.jpg");
    ImageIcon imageIconMetallica = new ImageIcon("metallica.jpg");
    ImageIcon imageIconNoHands = new ImageIcon("noHands.jpg");
    JLabel imageLabel = new JLabel();

    public SplashScreen() {
        setTitle("Workout App");
        setVisible(true);
        setSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        changeSplashScreen(0);
        topPanel.setPreferredSize(new Dimension(400, 100));
        centerPanel.add(imageLabel);

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
            if (progress >= 30 && progress < 70) {
                changeSplashScreen(1);
            } else if (progress >= 70) {
                changeSplashScreen(2);
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

    private void changeSplashScreen(int state) {
        if (state == 0) {
            topPanel.setBackground(new Color(0x73c4e2));
            imageLabel.setIcon(imageIconDumbbells);
        } else if (state == 1) {
            topLabel.setText("Playing Enter Sandman...");
            imageLabel.setIcon(imageIconMetallica);
            topPanel.setBackground(new Color(0x0));
            topLabel.setForeground(new Color(0x069EDE));
        } else if (state == 2) {
            topLabel.setText("Removing impossible exercises...");
            imageLabel.setIcon(imageIconNoHands);
            topPanel.setBackground(new Color(0xA9A8A8));
            topLabel.setForeground(new Color(0x2D2A2D));
        }
    }
}
