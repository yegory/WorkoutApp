package model;

import java.util.List;

public class Routine {
    // Represents a list of exercise having:
    //      - a name;
    //      - a description (could be considered like "notes")
    //      - the array of names of exercises which are in the routine
    //      - total time to complete (assuming that each rep takes 4 seconds)
    //      - rating [1-5] (any other number is "Unrated")
    public static final int TIME_FOR_1REP = 4;

    private String routineName;
    private String routineDescription;
    private List<Exercise> includedExercises;
    private int totalTimeToComplete;
    private int routineRating;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Routine(String routineName, String routineDescription, List<Exercise> includedExercises, int routineRating) {
        int totalExerciseTime = 0;

        this.routineName = routineName;
        this.routineDescription = routineDescription;
        this.includedExercises = includedExercises;
        for (Exercise exercise : includedExercises) {
            totalExerciseTime += exercise.getExerciseRestTime() * exercise.getExerciseNumOfSets()
                    + exercise.getExerciseNumOfSets() * exercise.getExerciseNumOfReps() * TIME_FOR_1REP;
        }
        this.totalTimeToComplete = totalExerciseTime;
        this.routineRating = routineRating;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public String printRoutine() {
        String outputString = "[" + routineName + "]: " + routineDescription
                + "\nConsists of: ";
        String includedExerciseFormatting = "[";
        if (includedExercises.size() == 1) {
            includedExerciseFormatting += includedExercises.get(0).getExerciseName() + "], ";
        } else {
            for (int i = 0; i < includedExercises.size(); i++) {
                if ((i + 1) != includedExercises.size()) {
                    includedExerciseFormatting += includedExercises.get(i).getExerciseName() + ", ";
                } else {
                    includedExerciseFormatting += "followed by " + includedExercises.get(i).getExerciseName() + "]";
                }
            }
        }
        outputString += includedExerciseFormatting;
        outputString += "\nTakes " + formatTotalTimeToComplete(this.totalTimeToComplete) + " to complete,"
                + " and the rating of this routine is: " + returnDefinedRating() + ".";
        return outputString;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
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

    // GETTERS

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

    // SETTERS

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public void setRoutineDescription(String routineDescription) {
        this.routineDescription = routineDescription;
    }

    public void setIncludedExercises(List<Exercise> includedExercises) {
        this.includedExercises = includedExercises;
    }

    public void setTotalTimeToComplete(int totalTimeToComplete) {
        this.totalTimeToComplete = totalTimeToComplete;
    }

    public void setRoutineRating(int routineRating) {
        this.routineRating = routineRating;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public String formatTotalTimeToComplete(int time) {
        int wholeMinutes = Math.floorDiv(time, 60);
        time -= 60 * wholeMinutes;
        return String.format("%d min, %d sec", wholeMinutes, time);
    }
}