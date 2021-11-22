package ui;

import model.Event;
import model.EventLog;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * Represents a screen printer for printing event log to screen.
 */
public class ScreenPrinter extends JInternalFrame implements LogPrinter, WindowListener {
    private static final int WIDTH = 360;
    private static final int HEIGHT = 600;
    private JTextArea logArea;

    /**
     * Constructor sets up window in which log will be printed on screen
     * @param parent  the parent component
     */
    public ScreenPrinter() {
        super("Event log", false, true, false, false);
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(new Color(0x62ADFF));
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        setBounds(0,250, WIDTH, HEIGHT);
        setVisible(true);
        addWindowListener(this);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
    }

    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            logArea.setText(logArea.getText() + next.toString() + "\n\n");
        }

        repaint();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        WorkoutAppUI.setScreenPrinter(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
