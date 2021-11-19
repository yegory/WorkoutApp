package model;

import org.json.JSONObject;
import persistence.Writable;

/*
    Json stuff borrowed from  JsonSerializationDemo
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

// Represents an exercise having:
//      - a name;
//      - description;
//      - # of reps;
//      - # of sets;
//      - rest time in seconds (>= 0); and
//      - rating [1-5] (any other number is "Unrated")
public class Exercise implements Writable {

    private String exerciseName;
    private String exerciseDescription;
    private int exerciseNumOfReps;
    private int exerciseNumOfSets;
    private int exerciseRestTime;
    private int exerciseRating;

    // EFFECTS: Constructs the routine
    //          exerciseName is initialized to a temporary name with (hopefully) a unique number combination.
    //          sets exerciseDescription to user input (can be empty)
    //          sets exerciseNumOfReps to whichever integer is greatest ( user input or 1)
    //          sets exerciseNumOfSets to whichever integer is greatest ( user input or 1)
    //          sets exerciseRestTime to whichever integer is greatest ( user input or 0)
    //          sets exerciseRating to any integer
    public Exercise(String exerciseName, String exerciseDescription, int exerciseNumberOfReps, int exerciseNumberOfSets,
                    int exerciseRestTime, int exerciseRating) {
        this.exerciseDescription = exerciseDescription;
        this.exerciseNumOfReps = Math.max(1, exerciseNumberOfReps);
        this.exerciseNumOfSets = Math.max(1, exerciseNumberOfSets);
        this.exerciseRestTime = Math.max(0, exerciseRestTime);
        this.exerciseRating = exerciseRating;
        if (exerciseName.equals("")) {
            this.exerciseName = "TempName" + this.exerciseNumOfReps + this.exerciseNumOfSets
                    + this.exerciseRestTime + this.exerciseRating;
        } else {
            this.exerciseName = exerciseName;
        }
    }

    // EFFECTS: returns a string equivalent of a numeric rating from 0-5. Any other number is "Unrated"
    public String returnDefinedRating() {
        if (exerciseRating == 0) {
            return "F - Atrocious";
        } else if (exerciseRating == 1) {
            return "E - Terrible";
        } else if (exerciseRating == 2) {
            return "D - Ok-ish";
        } else if (exerciseRating == 3) {
            return "C - Decent";
        } else if (exerciseRating == 4) {
            return "B - Great";
        } else if (exerciseRating == 5) {
            return "A - Amazing";
        } else {
            return "Unrated";
        }
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public int getExerciseNumOfReps() {
        return exerciseNumOfReps;
    }

    public int getExerciseNumOfSets() {
        return exerciseNumOfSets;
    }

    public int getExerciseRestTime() {
        return exerciseRestTime;
    }

    public int getExerciseRating() {
        return exerciseRating;
    }

    // EFFECTS: sets exerciseName to passed name parameter if length of the parameter is > 0
    //          otherwise assigns a temporary name
    public void setExerciseName(String name) {
        if (name.equals("")) {
            this.exerciseName = "TempName" + exerciseNumOfReps + exerciseNumOfSets
                    + exerciseRestTime + exerciseRating;
        } else {
            this.exerciseName = name;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseDescription to input
    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    // MODIFIES: this
    // EFFECTS:  sets exerciseNumOfReps to whichever integer is greatest ( user input or 1)
    public void setExerciseNumOfReps(int exerciseNumOfReps) {
        this.exerciseNumOfReps = Math.max(1, exerciseNumOfReps);
    }

    // MODIFIES: this
    // EFFECTS:  sets exerciseNumOfSets to whichever integer is greatest ( user input or 1)
    public void setExerciseNumOfSets(int exerciseNumOfSets) {
        this.exerciseNumOfSets = Math.max(1, exerciseNumOfSets);
    }

    // MODIFIES: this
    // EFFECTS:  sets exerciseRestTime to whichever integer is greatest ( user input or 0)
    public void setExerciseRestTime(int exerciseRestTime) {
        this.exerciseRestTime = Math.max(0, exerciseRestTime);
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseRating to the input
    public void setExerciseRating(int exerciseRating) {
        this.exerciseRating = exerciseRating;
    }

    // EFFECTS: converts exercise to Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("exerciseName", exerciseName);
        json.put("exerciseDescription", exerciseDescription);
        json.put("exerciseNumOfReps", exerciseNumOfReps);
        json.put("exerciseNumOfSets", exerciseNumOfSets);
        json.put("exerciseRestTime", exerciseRestTime);
        json.put("exerciseRating", exerciseRating);
        return json;
    }

    /*
        EFFECTS: constructs a string object from an exercise object.
     */
    public String[] exerciseToStringObject() {

        String[] data = new String[6];
        data[0] = exerciseName;
        data[1] = exerciseDescription;
        data[2] = Integer.toString(exerciseNumOfReps);
        data[3] = Integer.toString(exerciseNumOfSets);
        data[4] = Integer.toString(exerciseRestTime);
        data[5] = returnDefinedRating();

        return data;
    }
}