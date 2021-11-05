package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.FileNotFoundException;

// exception inspired by JsonSerializationDemo (link in README)

public class WorkoutAppUI extends JFrame {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    private static final Color MAIN_BACKGROUND_COLOR = new Color(0x7289DA);

    private WorkoutApp workoutApp;
    JDesktopPane mainWindow;
    JInternalFrame homePanel;
    JInternalFrame filePanel;
    JInternalFrame exercisePanel;
    JInternalFrame routinePanel;

    public WorkoutAppUI() {
        //try {
        //    workoutApp = new WorkoutApp();
        //} catch (FileNotFoundException e) {
        //    System.out.println("File not found");
        //}
        mainWindow = new JDesktopPane();
        mainWindow.addMouseListener(new DesktopFocusAction());
        setContentPane(mainWindow);
        setSize(WIDTH, HEIGHT);
        setTitle("Workout App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(MAIN_BACKGROUND_COLOR);

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

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu homeMenu = new JMenu("Home");
        JMenu fileMenu = new JMenu("File");
        JMenu exerciseMenu = new JMenu("Exercise Menu");
        JMenu routineMenu = new JMenu("Routine Menu");

        menuBar.add(homeMenu);
        menuBar.add(fileMenu);
        menuBar.add(exerciseMenu);
        menuBar.add(routineMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     * @param accelerator    keystroke accelerator for this menu item
     *                       copied from alarm project :(
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
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
        new ExerciseMenu();
        new WorkoutAppUI();
    }
}