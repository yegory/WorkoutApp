package ui;

import model.EventLog;
import model.exception.LogException;

/**
 *      Credit to https://github.students.cs.ubc.ca/CPSC210/AlarmSystem for allowing me to use their implementation of
 *      Event and EventLog classes and the design.
 *
 *      Defines behaviours that event log printers must support.
 */
public interface LogPrinter {
    /*
        Prints the log
        throws LogException if file path name is incorrect
     */
    void printLog(EventLog eventLog) throws LogException;
}
