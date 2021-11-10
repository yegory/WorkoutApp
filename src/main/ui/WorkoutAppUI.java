package ui;


import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.java.swing.action.OpenAction;
import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;

// exception inspired by JsonSerializationDemo (link in README)

public class WorkoutAppUI extends JFrame implements ActionListener {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    public static final Color MAIN_BACKGROUND_COLOR = new Color(0xB07000);
    public static final Color WorkoutPanelColor = new Color(0x6D7EB0);


    private static JDesktopPane mainWindow;
    private JInternalFrame homePanel;
    private JInternalFrame filePanel;
    private static JInternalFrame exercisePanel;
    private JInternalFrame routinePanel;

    private JMenuItem homeMenuItem;
    private JMenuItem fileMenuItem;
    private JMenuItem exerciseMenuItem;
    private JMenuItem routineMenuItem;

    private static WorkoutApp workoutApp;

    public static WorkoutApp getWorkoutApp() {
        return workoutApp;
    }


    public WorkoutAppUI() {
        try {
            workoutApp = new WorkoutApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        mainWindow = new JDesktopPane();
        mainWindow.addMouseListener(new DesktopFocusAction());
        setContentPane(mainWindow);
        setSize(WIDTH, HEIGHT);
        setTitle("Workout App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setBackground(MAIN_BACKGROUND_COLOR);

        homePanel = new HomePanel();
        filePanel = new FilePanel();
        exercisePanel = new ExercisePanel();
        routinePanel = new RoutinePanel();

        mainWindow.add(homePanel);
        mainWindow.add(filePanel);
        mainWindow.add(exercisePanel);
        mainWindow.add(routinePanel);

        addMenu();

        centreOnScreen();
        setVisible(true);
    }

//    public static void updateExercisePanel() {
//        ExercisePanel.displayAllExercises();
//    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mainMenu = new JMenu("Menu");
        menuBar.add(mainMenu);

        homeMenuItem = new JMenuItem("Open Home panel");
        fileMenuItem = new JMenuItem("Open File panel");
        exerciseMenuItem = new JMenuItem("Open Exercise panel");
        routineMenuItem = new JMenuItem("Open Routine panel");
        mainMenu.add(homeMenuItem);
        mainMenu.add(fileMenuItem);
        mainMenu.add(exerciseMenuItem);
        mainMenu.add(routineMenuItem);

        homeMenuItem.addActionListener(this);
        fileMenuItem.addActionListener(this);
        exerciseMenuItem.addActionListener(this);
        routineMenuItem.addActionListener(this);
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeMenuItem) {
            if (homePanel.isClosed()) {
                homePanel = new HomePanel();
                mainWindow.add(homePanel);
            }
        } else if (e.getSource() == fileMenuItem) {
            if (filePanel.isClosed()) {
                filePanel = new FilePanel();
                mainWindow.add(filePanel);
            }
        } else if (e.getSource() == exerciseMenuItem) {
            if (exercisePanel.isClosed()) {
                exercisePanel = new ExercisePanel();
                mainWindow.add(exercisePanel);
            }
        } else if (e.getSource() == routineMenuItem) {
            if (filePanel.isClosed()) {
                mainWindow.add(new RoutinePanel());
            }
        }
    }

    public static void updateExercisePanel(Workout workout) {
        ExercisePanel.updateExerciseTable(workout);
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            WorkoutAppUI.this.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        new WorkoutAppUI();
    }
}