package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

/*
    !!! I created this class so that I could save/load to the same file, got the idea from
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo,
    where a workroom contained all the information that needed to be saved.
 */

// Represents a workout profile having a name, and list of already existing exercises and workout routines.
public class Workout implements Writable {

    private String name;
    private List<Exercise> exercises;
    private List<Routine> routines;

    // EFFECTS: constructs a new workout with name as provided with empty exercises and routines lists.
    public Workout(String name) {
        this.name = name;
        exercises = new ArrayList<>();
        routines = new ArrayList<>();
    }

    // EFFECTS: returns the name of the workout profile
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to this workroom
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    // MODIFIES: this
    // EFFECTS: adds routine to this workout
    public void addRoutine(Routine routine) {
        routines.add(routine);
    }

    // EFFECT: returns a list of the all the exercises
    public List<Exercise> getExercises() {
        return exercises;
    }

    // REQUIRES: index is not out of range for exercises
    // EFFECT: returns the exercise at position equal to index
    public Exercise getExercise(int index) {
        return exercises.get(index);
    }

    // EFFECT: returns a list of the all the routines
    public List<Routine> getRoutines() {
        return routines;
    }

    // REQUIRES: index is not out of range for routines
    // EFFECT: returns the routine at position equal to index
    public Routine getRoutine(int index) {
        return routines.get(index);
    }

    // EFFECTS: returns number of exercises in this workout
    public int exercisesSize() {
        return exercises.size();
    }

    // EFFECTS: returns number of routines in this workout
    public int routinesSize() {
        return routines.size();
    }

    // MODIFIES: this
    // REQUIRES: index is not out of range for exercises
    // EFFECTS: removes the Routine at position equal to index
    public void removeExercise(int index) {
        exercises.remove(index);
    }

    // MODIFIES: this
    // REQUIRES: index is not out of range for routines
    // EFFECTS: removes the Routine at position equal to index
    public void removeRoutine(int index) {
        routines.remove(index);
    }

    public boolean findExercise(String exerciseName) {
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseName().equals(exerciseName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("listOfExercises", exercisesToJson());
        json.put("listOfRoutines", routinesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise exercise: exercises) {
            jsonArray.put(exercise.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray routinesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Routine routine: routines) {
            jsonArray.put(routine.toJson());
        }

        return jsonArray;
    }
}