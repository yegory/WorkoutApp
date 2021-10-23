package persistence;

import model.Workout;
import org.json.JSONObject;
import java.io.*;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    // borrowed from JsonSerializationDemo (link in README)
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // borrowed from JsonSerializationDemo (link in README)
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    // borrowed from JsonSerializationDemo (link in README)
    public void write(Workout workout) {
        JSONObject json = workout.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // borrowed from JsonSerializationDemo (link in README)
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // borrowed from JsonSerializationDemo (link in README)
    private void saveToFile(String json) {
        writer.print(json);
    }
}
