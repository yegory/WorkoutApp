package ui;

import model.Routine;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/*
    !!! save and load functionality inspired/borrowed from JsonSerializationDemo
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class WorkoutAppUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/WorkoutAppData.json";
    private static Workout workout;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    public static final Color MAIN_BACKGROUND_COLOR = new Color(0xB07000);
    public static final Color WorkoutPanelColor = new Color(0x6D7EB0);

    static JDesktopPane mainWindow;
    static ExerciseTable exerciseTable;
    JInternalFrame homePanel;
    JInternalFrame filePanel;
    static JInternalFrame exercisePanel;
    JInternalFrame routinePanel;

    JMenuItem homeMenuItem;
    JMenuItem fileMenuItem;
    JMenuItem exerciseMenuItem;
    JMenuItem routineMenuItem;


    public WorkoutAppUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        workout = new Workout("Your workout profile");

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
        setVisible(false);
    }

    public static ExerciseTable getExerciseTable() {
        return exerciseTable;
    }

    public static void setExerciseTable(ExerciseTable exerciseTable) {
        WorkoutAppUI.exerciseTable = exerciseTable;
    }

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
            if (routinePanel.isClosed()) {
                routinePanel = new RoutinePanel();
                mainWindow.add(routinePanel);
            }
        }
    }

    public static void updateExercisePanel(Workout workout) {
        ExercisePanel.updateExerciseTable(workout);
    }

    public static void updateRoutinePanel(Workout workout) {
        RoutinePanel.updateRoutineTable(workout);
    }

    public static void displayIncludedExercises(int routinePos) {
        Routine routine = workout.getRoutine(routinePos);
        new ExerciseTable(routine);
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


    public static Workout getWorkout() {
        return workout;
    }

    // !!! inspired by JsonSerializationDemo (link in README)
    // EFFECTS: saves the workout to file
    protected static void saveWorkout() throws IOException {
        jsonWriter.open();
        jsonWriter.write(workout);
        jsonWriter.close();
    }

    // !!! inspired by JsonSerializationDemo (link in README)
    // MODIFIES: this
    // EFFECTS: loads workout from file
    protected static void loadWorkout() throws IOException {
        workout = jsonReader.read();
    }

    public static void main(String[] args) {
        WorkoutAppUI workoutAppUI = new WorkoutAppUI();
        new SplashScreen();
        workoutAppUI.setVisible(true);
    }
}