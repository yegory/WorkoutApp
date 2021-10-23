package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Workout implements Writable {

    private String name;
    private List<Exercise> exercises;
    private List<Routine> routines;

    public Workout(String name) {
        this.name = name;
        exercises = new ArrayList<>();
        routines = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void addRoutine(Routine routine) {
        routines.add(routine);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public Exercise getExercise(int index) {
        return exercises.get(index);
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public Routine getRoutine(int index) {
        return routines.get(index);
    }

    // EFFECTS: returns number of thingies in this workroom
    public int exercisesSize() {
        return exercises.size();
    }

    public int routinesSize() {
        return routines.size();
    }

    public void removeExercise(int index) {
        exercises.remove(index);
    }

    // MODIFIES: this
    // REQUIRES: index is not out of range for myRoutines
    // EFFECTS: removes the Routine at position equal to index.
    public void removeRoutine(int index) {
        routines.remove(index);
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