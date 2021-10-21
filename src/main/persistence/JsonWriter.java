package persistence;

import model.Exercise;
import model.Routine;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(List<Exercise> listOfExercise) {
        for (Exercise exercise: listOfExercise) {
            JSONObject json = exercise.toJson();
            saveToFile(json.toString(TAB));
        }
    }

    public void write(List<Routine> listOfRoutines) {
        for (Routine routine: listOfRoutines) {
            JSONObject json = routine.toJson();
            saveToFile(json.toString(TAB));
        }
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
