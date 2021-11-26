package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

/**
 *      Credit to JsonSerializationDemo - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 *      for inspiration for methods relating to JSON
 */

/**
 *      Represents an exercise having:
 *      exerciseName        - a name;
 *      exerciseDescription - a description
 *      exerciseNumOfReps   - number of reps (greater than 0)
 *      exerciseNumOfSets   - number of sets (greater than 0)
 *      exerciseRestTime    - rest time between sets in seconds (>= 0)
 *      exerciseRating      - rating between 1 and 5 inclusive (any other number is "Unrated")
 */
public class Exercise implements Writable {

    private String exerciseName;
    private String exerciseDescription;
    private int exerciseNumOfReps;
    private int exerciseNumOfSets;
    private int exerciseRestTime;
    private int exerciseRating;

    /**
     *      EFFECTS: Constructs the routine
     * @param exerciseName exerciseName is initialized to a temporary name with (hopefully) a unique number combination.
     * @param exerciseDescription sets exerciseDescription to user input (can be empty)
     * @param exerciseNumberOfReps sets exerciseNumOfReps to whichever integer is greatest ( user input or 1)
     * @param exerciseNumberOfSets sets exerciseNumOfSets to whichever integer is greatest ( user input or 1)
     * @param exerciseRestTime sets exerciseRestTime to whichever integer is greatest ( user input or 0)
     * @param exerciseRating sets exerciseRating to any integer
     */
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
    public void setExerciseName(String exerciseName) {
        String nameBefore = this.exerciseName;
        if (exerciseName.equals("")) {
            this.exerciseName = "TempName" + exerciseNumOfReps + exerciseNumOfSets
                    + exerciseRestTime + exerciseRating;
            EventLog.getInstance().logEvent(new Event(
                    "Changed exercise name from \"" + nameBefore + " to \"" + this.exerciseName + "\""));
        } else if (!this.exerciseName.equals(exerciseName)) {
            this.exerciseName = exerciseName;
            EventLog.getInstance().logEvent(new Event(
                    "Changed exercise name from \"" + nameBefore + " to \"" + this.exerciseName + "\""));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseDescription to input
    public void setExerciseDescription(String exerciseDescription) {
        String oldDescription = this.exerciseDescription;
        if (!oldDescription.equals(exerciseDescription)) {
            this.exerciseDescription = exerciseDescription;
            EventLog.getInstance().logEvent(new Event(
                    "Changed description for \"" + exerciseName + "\" to \"" + this.exerciseDescription + "\""));
        }
    }

    // MODIFIES: this
    // EFFECTS:  sets exerciseNumOfReps to whichever integer is greatest ( user input or 1)
    public void setExerciseNumOfReps(int exerciseNumOfReps) {
        int oldReps = this.exerciseNumOfReps;
        this.exerciseNumOfReps = Math.max(1, exerciseNumOfReps);
        if (oldReps != this.exerciseNumOfReps) {
            EventLog.getInstance().logEvent(new Event(
                    "Changed reps for \"" + exerciseName + "\" from "
                            + oldReps + " to " + this.exerciseNumOfReps + "."));
        }
    }

    // MODIFIES: this
    // EFFECTS:  sets exerciseNumOfSets to whichever integer is greatest ( user input or 1)
    public void setExerciseNumOfSets(int exerciseNumOfSets) {
        int oldSets = this.exerciseNumOfSets;
        this.exerciseNumOfSets = Math.max(1, exerciseNumOfSets);
        if (oldSets != this.exerciseNumOfSets) {
            EventLog.getInstance().logEvent(new Event(
                    "Changed sets for \"" + exerciseName + "\" from "
                            + oldSets + " to " + this.exerciseNumOfSets + "."));
        }
    }

    // MODIFIES: this
    // EFFECTS:  sets exerciseRestTime to whichever integer is greatest ( user input or 0)
    public void setExerciseRestTime(int exerciseRestTime) {
        int oldRestTime = this.exerciseRestTime;
        this.exerciseRestTime = Math.max(0, exerciseRestTime);
        if (oldRestTime != this.exerciseRestTime) {
            EventLog.getInstance().logEvent(new Event(
                    "Changed rest time for \"" + exerciseName + "\" from " + oldRestTime + "s to "
                            + this.exerciseRestTime + "s."));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseRating to the input
    public void setExerciseRating(int exerciseRating) {
        String oldRating = returnDefinedRating();
        this.exerciseRating = exerciseRating;
        if (oldRating != returnDefinedRating()) {
            EventLog.getInstance().logEvent(new Event(
                    "Changed rating for \"" + exerciseName + "\" from \"" + oldRating
                            + "\" to \"" + returnDefinedRating() + "\"."));
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Exercise exercise = (Exercise) o;

        if (exerciseRating != exercise.exerciseRating) {
            return false;
        }
        if (!exerciseName.equals(exercise.exerciseName)) {
            return false;
        }
        return Objects.equals(exerciseDescription, exercise.exerciseDescription);
    }

    @Override
    public int hashCode() {
        int result = exerciseName.hashCode();
        result = 31 * result + (exerciseDescription != null ? exerciseDescription.hashCode() : 0);
        result = 31 * result + exerciseRating;
        return result;
    }
}