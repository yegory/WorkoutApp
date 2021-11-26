package persistence;

import model.Workout;
import org.json.JSONObject;
import java.io.*;

/**
 *      Credit to JsonSerializationDemo - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 *      for almost all the code related to reading/writing to json
 *
 *      Represents a writer that writes JSON representation of workroom to file
 */
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    /**
     *      borrowed from JsonSerializationDemo, link to repo above
     *      EFFECTS: constructs writer to write to destination file
     */

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /**
     *      borrowed from JsonSerializationDemo, link to repo above
     *      MODIFIES: this
     *      EFFECTS: opens writer
     *      @throws FileNotFoundException if destination file cannot be opened for writing
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    /**
     *      borrowed from JsonSerializationDemo, link to repo above
     *      MODIFIES: this
     *      EFFECTS: writes JSON representation of workout to file
     */
    public void write(Workout workout) {
        JSONObject json = workout.toJson();
        saveToFile(json.toString(TAB));
    }

    /**
     *      borrowed from JsonSerializationDemo, link to repo above
     *      MODIFIES: this
     *      EFFECTS: closes writer
     */
    public void close() {
        writer.close();
    }

    /**
     *      borrowed from JsonSerializationDemo (link in above)
     *
     *      MODIFIES: this
     *      EFFECTS: writes string to file
     */
    private void saveToFile(String json) {
        writer.print(json);
    }
}