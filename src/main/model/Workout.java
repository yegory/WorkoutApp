package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 *      Credit to JsonSerializationDemo - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 *      for inspiration for methods relating to JSON
 */

/**
 *      I created this class so that I could save/load to the same file, got the idea from
 *      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo,
 *      where a workroom contained all the information that needed to be saved.
 */

// Represents a workout profile having a name, and list of already existing exercises and workout routines.
public class Workout implements Writable {


    private String name;
    private List<Exercise> exercises;
    private List<Routine> routines;

    /**
     *      EFFECTS: constructs a new workout with name as provided with empty exercises and routines lists.
     * @param name is the name of the workout profile (could add different log in/profile management
     *
     *
     *      Workout represents :
     *      exercises: a list of all stand-alone exercises that a user currently has in their profile
     *      routines: a list of all routines that a user has created based on the list of exercises at some point in
     *                the program's execution
     */
    /*
            NOTE: List<Exercise> inside the included exercises list in a routine could potentially not exist inside the
            exercises list if the user has deleted the exercise from the exercises list after creating the routine
            involving that exercise. This is completely fine as I couldn't figure out what behaviour I want, and this
            'default' behaviour seems best to me. If I made the choice to validate every exercise inside each routine
            after deleting that exercise from the exercises list, then some routines would be different and personally
            I would not want this to happen if i was the user. However, a better alternative I could do (would take some
            time is that when a user deletes an exercise, there could be a tab opens that shows each routine that will
            be affected and force the user to choose to remove it from the routine or keep it.
     */

    public Workout(String name) {
        this.name = name;
        exercises = new ArrayList<>();
        routines = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Workout created"));
    }

    // EFFECTS: returns the name of the workout profile
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds exercise to this workroom
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        EventLog.getInstance().logEvent(new Event(("Added new exercise \"" + exercise.getExerciseName() + "\"")));
    }

    // MODIFIES: this
    // EFFECTS: adds routine to this workout
    public void addRoutine(Routine routine) {
        routines.add(routine);
        EventLog.getInstance().logEvent(new Event(("Added new routine \"" + routine.getRoutineName() + "\"")));
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

    public List<Routine> getFavoriteRoutines() {
        List<Routine> favorites = new ArrayList<>();
        for (Routine routine: routines) {
            if (routine.getRoutineRating() == 5) {
                favorites.add(routine);
            }
        }
        return favorites;
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
        EventLog.getInstance().logEvent(new Event(("Deleted exercise \""
                + exercises.get(index).getExerciseName() + "\"")));
        exercises.remove(index);
    }

    // MODIFIES: this
    // REQUIRES: index is not out of range for routines
    // EFFECTS: removes the Routine at position equal to index
    public void removeRoutine(int index) {
        EventLog.getInstance().logEvent(new Event(("Deleted routine \""
                + routines.get(index).getRoutineName() + "\"")));
        routines.remove(index);
    }

    // EFFECTS: returns true if there is a matching exercise with the same name inside the exercises list.
    public boolean findExercise(String exerciseName) {
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseName().equals(exerciseName)) {
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("listOfExercises", exercisesToJson());
        json.put("listOfRoutines", routinesToJson());
        EventLog.getInstance().logEvent(new Event("Workout saved"));
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise exercise : exercises) {
            jsonArray.put(exercise.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray routinesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Routine routine : routines) {
            jsonArray.put(routine.toJson());
        }

        return jsonArray;
    }
}