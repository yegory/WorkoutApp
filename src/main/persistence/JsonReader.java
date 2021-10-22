package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Exercise;
import model.Workout;
import org.json.*;
import ui.WorkoutApp;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public Workout read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workout workout = new Workout(name);
        addThingies(workout, jsonObject);
        return workout;
    }

    private void addExercises(Workout workout, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addExercise(workout, nextThingy);
        }
    }

    private void addExercise(Workout workout, JSONObject jsonObject) {
        String exerciseName = jsonObject.getString("exerciseName");
        String exerciseDescription = jsonObject.getString("exerciseDescription");
        int exerciseNumOfReps = jsonObject.getInt("exerciseNumOfReps");
        int exerciseNumOfSets = jsonObject.getInt("exerciseNumOfSets");
        int exerciseRestTime = jsonObject.getInt("exerciseRestTime");
        int exerciseRating = jsonObject.getInt("exerciseRating");

        Exercise exercise = Exercise.valueOf(jsonObject.getString("exercise"));
        Exercise exercise = new Exercise(exerciseName, category);
        workout.addThingy(thingy);
    }
}
