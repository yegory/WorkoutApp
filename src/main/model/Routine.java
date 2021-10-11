package model;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    // Represents a list of exercise having:
    //      - a name;
    //      - a description (could be considered like "notes")
    //      - the array of names of exercises which are in the routine
    //      - total time to complete (assuming that each rep takes 4 seconds)
    //      - rating [1-5] (any other number is "Unrated")
    private final int TIME_TO_DO_ONE_REP = 4;

    private String routineName;
    private String routineDescription;
    private List<Exercise> includedExercises;
    private int totalTimeToComplete;
    private int routineRating;


    public Routine(String routineName, String routineDescription, List<Exercise> includedExercises, int routineRating) {
        int totalExerciseTime = 0;

        this.routineName = routineName;
        this.routineDescription = routineDescription;
        this.includedExercises = includedExercises;
        for (Exercise exercise: includedExercises) {
            totalExerciseTime += exercise.getExerciseRestTime() * exercise.getExerciseNumOfSets()
                                + exercise.getExerciseNumOfSets() * exercise.getExerciseNumOfReps() * TIME_TO_DO_ONE_REP;
        }
        this.totalTimeToComplete = totalExerciseTime;
        this.routineRating = routineRating;
    }

    public void printRoutine() {
        String outputString = "[" + routineName + "]: " + routineDescription + ".\nConsists of: ";
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
        outputString += "\nTakes " + (totalTimeToComplete / 60) + " minutes to complete,"
                        + " and the rating of this routine is: " + returnDefinedRating() + ".";
        System.out.println(outputString);
    }

    private String returnDefinedRating() {
        String returnedRating;
        switch (routineRating) {
            case 0: returnedRating = "F - Atrocious";
                break;
            case 1: returnedRating = "E - Terrible";
                break;
            case 2: returnedRating = "D - Ok-ish";
                break;
            case 3: returnedRating = "C - Decent";
                break;
            case 4: returnedRating = "B - Great";
                break;
            case 5: returnedRating = "A - Amazing";
                break;
            default:
                returnedRating = "Unrated";
                break;
        }
        return returnedRating;
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

}
