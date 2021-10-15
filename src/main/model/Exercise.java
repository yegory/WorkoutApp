package model;

import java.util.Arrays;
import java.util.List;

// Represents an exercise having:
//      - a name;
//      - description;
//      - # of reps;
//      - # of sets;
//      - rest time in seconds (>= 0); and
//      - rating [1-5] (any other number is "Unrated")
public class Exercise {
    private static final int CONSOLE_OUTPUT_LINE_LENGTH = 80;

    private String exerciseName;
    private String exerciseDescription;
    private int exerciseNumOfReps;
    private int exerciseNumOfSets;
    private int exerciseRestTime;
    private int exerciseRating;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Exercise(String exerciseName, String exerciseDescription, int exerciseNumberOfReps, int exerciseNumberOfSets,
                    int exerciseRestTime, int exerciseRating) {
        this.exerciseDescription = exerciseDescription;
        this.exerciseNumOfReps = Math.max(1, exerciseNumberOfReps);
        this.exerciseNumOfSets = Math.max(1, exerciseNumberOfSets);
        this.exerciseRestTime = Math.max(0, exerciseRestTime);
        this.exerciseRating = exerciseRating;
        if (exerciseName.equals("")) {
            this.exerciseName = "temporary name - " + this.exerciseNumOfReps + this.exerciseNumOfSets
                    + this.exerciseRestTime + this.exerciseRating;
        } else {
            this.exerciseName = exerciseName;
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public String printExercise() {
        String returnedString = "[" + exerciseName + "]\n";

        String descriptionContainer = exerciseDescription;
        String[] exerciseDescriptionAsArray = descriptionContainer.split(" ");
        List<String> outputArrayList = Arrays.asList(exerciseDescriptionAsArray);

        String descriptionBuilder = "";
        int charCount = 0;
        for (String word : outputArrayList) {
            descriptionBuilder += word + " ";
            charCount += word.length();

            if (charCount > CONSOLE_OUTPUT_LINE_LENGTH) {
                descriptionBuilder += "\n";
                charCount = 0;
            }
        }

        returnedString += descriptionBuilder.trim()
                + "\nNumber of reps: " + exerciseNumOfReps + "\n"
                + "Number of sets: " + exerciseNumOfSets + "\n"
                + "Rest time     : " + exerciseRestTime + " seconds\n"
                + "Rating        : " + returnDefinedRating();
        return returnedString;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
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

    public void setExerciseName(String name) {
        if (name.equals("")) {
            this.exerciseName = "temporary name - " + exerciseNumOfReps + exerciseNumOfSets
                    + exerciseRestTime + exerciseRating;
        } else {
            this.exerciseName = name;
        }
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseNumOfReps(int exerciseNumOfReps) {
        this.exerciseNumOfReps = Math.max(1, exerciseNumOfReps);
    }

    public void setExerciseNumOfSets(int exerciseNumOfSets) {
        this.exerciseNumOfSets = Math.max(1, exerciseNumOfSets);
    }

    public void setExerciseRestTime(int exerciseRestTime) {
        this.exerciseRestTime = Math.max(0, exerciseRestTime);
    }

    public void setExerciseRating(int exerciseRating) {
        this.exerciseRating = exerciseRating;
    }
}