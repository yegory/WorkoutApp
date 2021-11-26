package ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Event;
import model.EventLog;
import model.exception.LogException;

/*
    Represents a file printer for printing the log to file.
 */
public class FilePrinter implements LogPrinter {
    private static final String SEP = System.getProperty("file.separator");

    private FileWriter fileWriter;

    /*
         Constructor creates file.  Each log file has a sequential log number
         starting at LOG_INIT for each run of the application.
         @throws LogException when any kind of input/output error occurs
     */
    public FilePrinter(String date) throws LogException {
        try {
            File logFile = new File("./data/Logs/" + date);
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            throw new LogException("Cannot open file");
        }
    }

    @Override
    public void printLog(EventLog eventLog) throws LogException {
        try {
            for (Event next : eventLog) {
                fileWriter.write(next.toString());
                fileWriter.write("\n\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new LogException("Cannot write to file");
        }
    }
}