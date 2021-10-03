package model;

import java.util.List;

public class Exercise {
    private String exerciseName;
    private String exerciseDescription;
    private int exerciseNumOfReps;
    private int exerciseNumOfSets;
    private int exerciseRestTime;
    private int exerciseRating;

    private List<Exercise> listOfExercises;

    public Exercise(String exsName, String exsDesc, int exsNoR, int exsNoS, int exsRT, int exsRating) {
        this.exerciseName = exsName;
        this.exerciseDescription = exsDesc;
        this.exerciseNumOfReps = exsNoR;
        this.exerciseNumOfSets = exsNoS;
        this.exerciseRestTime = exsRT;
        this.exerciseRating = exsRating;
    }

    void addExercise(Exercise exercise) {
        listOfExercises.add(exercise);
    }

    public void printExercise() {
        int index = 65;
        System.out.println("Exercise name: " + exerciseName);
        System.out.println("Description:");
        while (exerciseDescription.length() > index) {
            while (' '  != (exerciseDescription.charAt(index))) {
                index += 1;
            }
            String printOut = exerciseDescription.substring(0, index).trim();
            System.out.println(printOut);
            exerciseDescription = exerciseDescription.substring(index);
        }
        System.out.println(exerciseDescription.trim());

        System.out.println("Number of reps: " + exerciseNumOfReps);
        System.out.println("Number of sets: " + exerciseNumOfSets);
        System.out.println("Rest time: " + exerciseRestTime);
        System.out.println("Rating: " + returnDefinedRating());
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
            default: returnedRating = "N/A";
                break;
        }
        return returnedRating;
    }
}
