package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Exercise;
import model.Routine;
import model.Workout;
import org.json.*;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // borrowed from JsonSerializationDemo (link in README)
    public Workout read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkout(jsonObject);
    }

    // borrowed from: https://devqa.io/java-read-json-file-as-string/
    public static String readFile(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    // EFFECTS: parses workroom from JSON object and returns it
    // borrowed from JsonSerializationDemo (link in README)
    private Workout parseWorkout(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workout workout = new Workout(name);
        addExercises(workout, jsonObject);
        addRoutines(workout, jsonObject);
        return workout;
    }

    // inspired by JsonSerializationDemo (link in README)
    private void addExercises(Workout workout, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfExercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(workout, nextExercise);
        }
    }

    // inspired by JsonSerializationDemo (link in README)
    private void addExercise(Workout workout, JSONObject jsonObject) {
        String exerciseName = jsonObject.getString("exerciseName");
        String exerciseDescription = jsonObject.getString("exerciseDescription");
        int exerciseNumOfReps = jsonObject.getInt("exerciseNumOfReps");
        int exerciseNumOfSets = jsonObject.getInt("exerciseNumOfSets");
        int exerciseRestTime = jsonObject.getInt("exerciseRestTime");
        int exerciseRating = jsonObject.getInt("exerciseRating");

        Exercise exercise = new Exercise(exerciseName, exerciseDescription, exerciseNumOfReps,
                exerciseNumOfSets, exerciseRestTime, exerciseRating);

        workout.addExercise(exercise);
    }

    // inspired by JsonSerializationDemo (link in README)
    private void addRoutines(Workout workout, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfRoutines");
        for (Object json : jsonArray) {
            JSONObject nextRoutine = (JSONObject) json;
            addRoutine(workout, nextRoutine);
        }
    }

    // inspired by JsonSerializationDemo (link in README)
    private void addRoutine(Workout workout, JSONObject jsonObject) {
        List<Exercise> exerciseList = new ArrayList<>();

        String routineName = jsonObject.getString("routineName");
        String routineDescription = jsonObject.getString("routineDescription");
        JSONArray includedExercises = jsonObject.getJSONArray("includedExercises");
        int routineRating = jsonObject.getInt("routineRating");

        for (int i = 0; i < includedExercises.length(); i++) {
            String exerciseName = includedExercises.getJSONObject(i).getString("exerciseName");
            String exerciseDescription = includedExercises.getJSONObject(i).getString("exerciseDescription");
            int exerciseNumOfReps = includedExercises.getJSONObject(i).getInt("exerciseNumOfReps");
            int exerciseNumOfSets = includedExercises.getJSONObject(i).getInt("exerciseNumOfSets");
            int exerciseRestTime = includedExercises.getJSONObject(i).getInt("exerciseRestTime");
            int exerciseRating = includedExercises.getJSONObject(i).getInt("exerciseRating");

            Exercise exercise = new Exercise(exerciseName, exerciseDescription, exerciseNumOfReps, exerciseNumOfSets,
                    exerciseRestTime, exerciseRating);
            exerciseList.add(exercise);
        }

        Routine routine = new Routine(routineName, routineDescription, exerciseList, routineRating);
        workout.addRoutine(routine);
    }
}
