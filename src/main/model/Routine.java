package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

/**
 *      Credit to JsonSerializationDemo - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 *      for inspiration for methods relating to JSON
 */

public class Routine implements Writable {
    /**
     *      Represents a routine entry, having:
     *      routineName         - a name
     *      routineDescription  - a routine description (could be considered like "notes")
     *      includedExercises   - the list of exercises which are in the routine
     *      totalTimeToComplete - total time to complete (assuming that each rep takes 4 seconds)
     *      routineRating       - rating [1-5] (any other number is "Unrated")
     */
    public static final int TIME_FOR_1REP = 4;

    private String routineName;
    private String routineDescription;
    private List<Exercise> includedExercises;
    private int totalTimeToComplete;
    private int routineRating;

    /**
     *      EFFECTS: Constructs the exercise
     * @param routineName routineName is initialized to user input (can't be empty)
     * @param routineDescription sets routineDescription to user input (can be empty)
     * @param includedExercises sets includedExercise to user input (can be empty)
     * @param routineRating sets routineRating to user input (any integer)
     */
    public Routine(String routineName, String routineDescription, List<Exercise> includedExercises, int routineRating) {
        int totalExerciseTime = 0;

        this.routineDescription = routineDescription;
        this.includedExercises = includedExercises;
        for (Exercise exercise : includedExercises) {
            totalExerciseTime += exercise.getExerciseRestTime() * exercise.getExerciseNumOfSets()
                    + exercise.getExerciseNumOfSets() * exercise.getExerciseNumOfReps() * TIME_FOR_1REP;
        }
        this.totalTimeToComplete = totalExerciseTime;
        this.routineRating = routineRating;

        if (routineName.equals("")) {
            this.routineName = "Routine" + routineDescription.length() + totalTimeToComplete + routineRating;
        } else {
            this.routineName = routineName;
        }
    }

    // EFFECTS: returns a string equivalent of a numeric rating from 0-5. Any other number is "Unrated"
    public String returnDefinedRating() {
        if (routineRating == 0) {
            return "F - Atrocious";
        } else if (routineRating == 1) {
            return "E - Terrible";
        } else if (routineRating == 2) {
            return "D - Ok-ish";
        } else if (routineRating == 3) {
            return "C - Decent";
        } else if (routineRating == 4) {
            return "B - Great";
        } else if (routineRating == 5) {
            return "A - Amazing";
        } else {
            return "Unrated";
        }
    }

    public String getRoutineName() {
        return routineName;
    }

    public String getRoutineDescription() {
        return routineDescription;
    }

    public List<Exercise> getIncludedExercises() {
        return includedExercises;
    }

    public int getTotalTimeToComplete() {
        return totalTimeToComplete;
    }

    public int getRoutineRating() {
        return routineRating;
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseRating to the input
    public void setRoutineName(String routineName) {
        String oldName = this.routineName;
        if (routineName.equals("")) {
            this.routineName = "Routine" + routineDescription.length() + totalTimeToComplete + routineRating;
            EventLog.getInstance().logEvent(new Event(
                    "Changed routine name from \"" + oldName + "\" to \"" + this.routineName + "\"."));
        } else if (!oldName.equals(routineName)) {
            this.routineName = routineName;
            EventLog.getInstance().logEvent(new Event(
                    "Changed routine name from \"" + oldName + "\" to \"" + this.routineName + "\"."));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseDescription to the input
    public void setRoutineDescription(String routineDescription) {
        String oldDescription = this.routineDescription;
        if (!oldDescription.equals(routineDescription)) {
            this.routineDescription = routineDescription;
            EventLog.getInstance().logEvent(new Event(
                    "Changed description for \"" + routineName + "\" routine to \""
                            + this.routineDescription + "\"."));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseIncludedExercises to the provided listOfExercise
    public void setIncludedExercises(List<Exercise> includedExercises) {
        List<Exercise> previousExercises = this.includedExercises;
        if (determineIfDifferentExerciseLists(previousExercises, includedExercises)) {
            String previousExercisesString = getIncludedExerciseString();
            this.includedExercises = includedExercises;
            EventLog.getInstance().logEvent(new Event(
                    "Changed included exercises for \"" + routineName + "\" from "
                            + previousExercisesString + " to " + getIncludedExerciseString() + "."));
        }
    }

    private boolean determineIfDifferentExerciseLists(List<Exercise> listA, List<Exercise> listB) {
        if (listA.size() == listB.size()) {
            if (listA.size() != 0) {
                for (int i = 0; i < listA.size(); i++) {
                    if (!listA.get(i).equals(listB.get(i))) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: sets exerciseRating to the input
    public void setTotalTimeToComplete(int totalTimeToComplete) {
        int oldTime = this.totalTimeToComplete;
        int expectedNewTime = Math.max(0,totalTimeToComplete);
        if (oldTime != expectedNewTime) {
            this.totalTimeToComplete = expectedNewTime;
            EventLog.getInstance().logEvent(new Event(
                    "Changed total time for \"" + routineName + "\" routine from \""
                            + formatTotalTimeToComplete(oldTime) + "\" to \""
                            + formatTotalTimeToComplete(this.totalTimeToComplete) + "\"."));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets routineRating to the input
    public void setRoutineRating(int routineRating) {
        String oldRating = returnDefinedRating();
        this.routineRating = routineRating;
        if (!oldRating.equals(returnDefinedRating())) {
            EventLog.getInstance().logEvent(new Event(
                    "Changed rating for \"" + routineName + "\" routine from \"" + oldRating
                            + "\" to " + returnDefinedRating() + "\"."));
        }
    }

    public void updateTotalTimeToComplete() {
        int oldTime = totalTimeToComplete;
        totalTimeToComplete = 0;
        for (Exercise exercise : includedExercises) {
            totalTimeToComplete += exercise.getExerciseRestTime() * exercise.getExerciseNumOfSets()
                    + exercise.getExerciseNumOfSets() * exercise.getExerciseNumOfReps() * TIME_FOR_1REP;
        }
        if (oldTime != totalTimeToComplete) {
            EventLog.getInstance().logEvent(new Event(
                    "Changed total time for \"" + routineName + "\" routine from \""
                            + formatTotalTimeToComplete(oldTime) + "\" to \""
                            + formatTotalTimeToComplete(totalTimeToComplete) + "\"."));
        }
    }

    // EFFECTS: helper to format time, e.g.: "# min, # sec"
    public String formatTotalTimeToComplete(int time) {
        int wholeMinutes = Math.floorDiv(time, 60);
        time -= 60 * wholeMinutes;
        return String.format("%d min, %d sec", wholeMinutes, time);
    }

    // MODIFIES: this
    // EFFECTS: displays all the exercise name
    //          example of possible output: "Routine 2: Routine 2 Name"
    private String getIncludedExerciseString() {
        String exerciseString = "[(" + includedExercises.size() + " exercises) ";
        if (includedExercises.size() != 0) {
            for (int i = 1; i <= includedExercises.size(); i++) {
                if (i == includedExercises.size()) {
                    exerciseString += includedExercises.get(i - 1).getExerciseName() + "]";
                } else {
                    exerciseString += includedExercises.get(i - 1).getExerciseName() + ", ";
                }
            }
        }
        return exerciseString;
    }

    // EFFECTS: converts routine to a Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("routineName", routineName);
        json.put("routineDescription", routineDescription);
        json.put("includedExercises", includedExercisesToJson());
        json.put("routineRating", routineRating);

        return json;
    }

    // EFFECTS: converts exercises to an array of individual Json objects
    public JSONArray includedExercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise exercise : includedExercises) {
            jsonArray.put(exercise.toJson());
        }

        return jsonArray;
    }
}