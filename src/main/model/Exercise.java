package model;

import java.util.List;

// Represents an exercise having:
//      - a name;
//      - description;
//      - # of reps;
//      - # of sets;
//      - rest time in seconds; and
//      - rating [1-5] (any other number is "Unrated")
public class Exercise {
    private String exerciseName;
    private String exerciseDescription;
    private int exerciseNumOfReps;
    private int exerciseNumOfSets;
    private int exerciseRestTime;
    private int exerciseRating;

    public Exercise(String exsName, String exsDesc, int exsNoR, int exsNoS, int exsRT, int exsRating) {
        this.exerciseName = exsName;
        this.exerciseDescription = exsDesc;
        this.exerciseNumOfReps = exsNoR;
        this.exerciseNumOfSets = exsNoS;
        this.exerciseRestTime = exsRT;
        this.exerciseRating = exsRating;
    }

    public void printExercise() {
        int index = 65;
        exerciseDescription = "[" + exerciseName + "]: " + exerciseDescription;
        while (exerciseDescription.length() > index) {
            while (' '  != (exerciseDescription.charAt(index))) {
                index += 1;
            }
            String printOut = exerciseDescription.substring(0, index).trim();
            System.out.println(printOut);
            exerciseDescription = exerciseDescription.substring(index);
        }
        System.out.println(exerciseDescription.trim() + "\n"
                            + "Number of reps: " + exerciseNumOfReps + "\n"
                            + "Number of sets: " + exerciseNumOfSets + "\n"
                            + "Rest time     : " + exerciseRestTime + " seconds\n"
                            + "Rating        : " + returnDefinedRating());
    }

    private String returnDefinedRating() {
        String returnedRating;
        switch (exerciseRating) {
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
            default: returnedRating = "Unrated";
                break;
        }
        return returnedRating;
    }
}
