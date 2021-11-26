package ui;

import model.EventLog;
import model.Routine;
import model.Workout;
import model.exception.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static ui.RoutinePanel.updateRoutineTable;

/**
    !!! save and load functionality inspired/borrowed from JsonSerializationDemo
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    Credit to https://github.students.cs.ubc.ca/CPSC210/AlarmSystem for allowing me to use their implementation of
    Event and EventLog classes and the design, as well as
    centreOnScreen() and DesktopFocusAction methods

    WorkoutAppUI is contains the main method. It is responsible for the Main window that contains 4 Panels, A Menu bar,
    and also initializes a splash screen before running the program.
 */

public class WorkoutAppUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/WorkoutAppData.json";
    private static Workout workout;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private static final String FILE_DESCRIPTOR = "...file";
    private static final String SCREEN_DESCRIPTOR = "...screen";
    private JComboBox<String> printCombo;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    public static final Color MAIN_BACKGROUND_COLOR = new Color(0x253C6B);
    public static final Color WorkoutPanelColor = new Color(0x6D7EB0);

    static JDesktopPane mainWindow;
    JInternalFrame homePanel;
    JInternalFrame filePanel;
    static JInternalFrame exercisePanel;
    JInternalFrame routinePanel;
    private JInternalFrame controlPanel;
    private static ExerciseTable exerciseTable;

    private static ScreenPrinter screenPrinter;

    JMenuItem fileMenuItem;
    JMenuItem exerciseMenuItem;
    JMenuItem routineMenuItem;
    JMenuItem aboutMenuItem;

    /*
        MODIFIES: this
        EFFECTS: Constructor to add JInternalFrames and add a Menu.
     */
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

        addPanels();
        addMenu();
        addControlPanelButtons();

        centreOnScreen();
        setVisible(false);
    }

    private void addPanels() {
        filePanel = new FilePanel();
        exercisePanel = new ExercisePanel();
        routinePanel = new RoutinePanel();
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.pack();
        controlPanel.setBounds(25, 150, 300, 80);
        controlPanel.setVisible(true);

        mainWindow.add(filePanel);
        mainWindow.add(exercisePanel);
        mainWindow.add(routinePanel);
        mainWindow.add(controlPanel);
    }

    public static void setScreenPrinter(ScreenPrinter screenPrinter) {
        WorkoutAppUI.screenPrinter = screenPrinter;
    }

    public static void setExerciseTable(ExerciseTable exerciseTable) {
        WorkoutAppUI.exerciseTable = exerciseTable;
    }

    /*
        MODIFIES: this
        EFFECTS: initializes the JMenu, JMenuBar, and JMenuItems, and adds action listeners for each.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu aboutMenu = new JMenu("About");
        JMenu mainMenu = new JMenu("Menu");
        menuBar.add(aboutMenu);
        menuBar.add(mainMenu);

        aboutMenuItem = new JMenuItem("Open About panel");
        aboutMenu.add(aboutMenuItem);
        fileMenuItem = new JMenuItem("Open File panel");
        exerciseMenuItem = new JMenuItem("Open Exercise panel");
        routineMenuItem = new JMenuItem("Open Routine panel");
        mainMenu.add(fileMenuItem);
        mainMenu.add(exerciseMenuItem);
        mainMenu.add(routineMenuItem);

        aboutMenuItem.addActionListener(this);
        fileMenuItem.addActionListener(this);
        exerciseMenuItem.addActionListener(this);
        routineMenuItem.addActionListener(this);
    }

    /*
         MODIFIES: this
         EFFECTS: centres main application window on desktop
         Taken from AlarmSystem demo project
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /*
        Got from Evren PALA:
        https://stackoverflow.com/questions/15499211/calling-function-on-windows-close
        Added sys.exit(0) to close app (not given by Evren)
     */
    public void processWindowEvent(WindowEvent we) {
        if (we.getID() == WindowEvent.WINDOW_CLOSING) {

            for (Iterator<model.Event> it = EventLog.getInstance().iterator(); it.hasNext(); ) {
                model.Event s = it.next();
                if (!it.hasNext()) {
                    System.out.println(s);
                } else {
                    System.out.println(s + "\n");
                }
            }
            System.exit(0);
        }
    }

    /*
        MODIFIES: this, ExercisePanel
        EFFECTS: updates the ExercisePanel table with the new workout profile. Called from FilePanel after loading.
     */
    public static void updateExercisePanel(Workout workout) {
        ExercisePanel.updateExerciseTable(workout);
    }

    /*
        Updates the routine Panel after the user loaded a save.
     */
    public static void updateRoutinePanel() {
        updateRoutineTable(RoutinePanel.isFavoriteView());
    }

    /*
        The responsibility of this function is to open a new ExerciseTable JPanel with a table of exercises contained
        in the routine with which the user interacted with. Since the position of the routine chosen depends on whether
        the RoutinePanel was in favoriteView or not (if any routine does not have a rating of 5, then indexes won't
        match when searching)
     */
    public static void displayIncludedExercises(int routinePos, boolean favoriteView) {
        List<Routine> routines = workout.getRoutines();
        if (exerciseTable != null) {
            exerciseTable.dispose();
        }
        if (!favoriteView) {
            exerciseTable = new ExerciseTable(routines.get(routinePos));
        } else if (favoriteView) {
            List<Routine> favoriteRoutines = workout.getFavoriteRoutines();
            exerciseTable = new ExerciseTable(favoriteRoutines.get(routinePos));
        }
    }

    /**
     *      Taken from AlarmSystem demo project, however I don't think this serves any purpose in my project,
     *      kept just in case
     *
     *      Represents action to be taken when user clicks desktop to switch focus.
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            WorkoutAppUI.this.requestFocusInWindow();
        }
    }

    // EFFECTS: returns the workout object
    public static Workout getWorkout() {
        return workout;
    }

    /**
     *      inspired by JsonSerializationDemo, link to repo at the top of this class.
     *
     *      MODIFIES: workout
     *      EFFECTS: saves the workout to file
     */
    protected static void saveWorkout() throws IOException {
        jsonWriter.open();
        jsonWriter.write(workout);
        jsonWriter.close();
    }

    /**
     *      inspired by JsonSerializationDemo, link to repo at the top of this class.
     *
     *      MODIFIES: this, workout
     *      EFFECTS: loads workout from file
     */
    protected static void loadWorkout() throws IOException {
        workout = jsonReader.read(workout);
    }

    /**
     *      Taken from AlarmSystem, link to repo at the top of this class.
     *
     *      MODIFIES: this
     *      EFFECTS: Helper to add control buttons.
     */
    private void addControlPanelButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(new JButton(new PrintLogAction()));
        buttonPanel.add(createPrintCombo());

        controlPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     *      Taken from AlarmSystem, link to repo at the top of this class
     *
     *      MODIFIES: this
     *      EFFECTS: Helper to create print options combo box
     *
     *      Removed the ability to clear the log (AlarmSystem allowed it), so that ALL the events are printed out
     *      after the app is closed.
     */
    private JComboBox<String> createPrintCombo() {
        printCombo = new JComboBox<String>();
        printCombo.setBackground(new Color(0xABABFF));
        printCombo.addItem(FILE_DESCRIPTOR);
        printCombo.addItem(SCREEN_DESCRIPTOR);
        return printCombo;
    }

    /**
     *      Taken from AlarmSystem, link to repo at the top of this class.
     *
     *      MODIFIES: this
     *      EFFECTS:  Represents the action to be taken when the user wants to print the event log.
     */
    private class PrintLogAction extends AbstractAction {
        PrintLogAction() {
            super("Print log to...");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String selected = (String) printCombo.getSelectedItem();
            LogPrinter logPrinter;
            try {
                if (selected.equals(FILE_DESCRIPTOR)) {
                    Date date = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String strDate = dateFormat.format(date);
                    logPrinter = new FilePrinter(strDate);
                } else {
                    logPrinter = handleScreenPrinter();
                }
                logPrinter.printLog(EventLog.getInstance());
            } catch (LogException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     *      Taken from AlarmSystem, link to repo at the top of this class.
     *
     *      MODIFIES: this
     *      EFFECTS: Helper to ensure that only one ScreenPrinter is created.
     */
    private LogPrinter handleScreenPrinter() {
        LogPrinter logPrinter;
        if (screenPrinter != null) {
            screenPrinter.dispose();
        }
        logPrinter = new ScreenPrinter();
        screenPrinter = (ScreenPrinter) logPrinter;
        mainWindow.add(screenPrinter);
        return logPrinter;
    }


    /**
     *      MODIFIES: this
     *      EFFECTS: adds panels to mainWindow when user clicks on a menu item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aboutMenuItem) {
            if (homePanel != null) {
                homePanel.dispose();
            }
            homePanel = new HomePanel();
            mainWindow.add(homePanel);
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

    /**
     *      Main method
     */
    public static void main(String[] args) {
        WorkoutAppUI workoutAppUI = new WorkoutAppUI();
        new SplashScreen();
        workoutAppUI.setVisible(true);
    }
}