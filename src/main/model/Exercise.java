package model;

public class Exercise {
    private String exerciseName;
    private String exerciseDescription;
    private int exerciseNumOfReps;
    private int exerciseNumOfSets;
    private int exerciseRestTime;
    private int exerciseRating;

    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = "";
        this.exerciseNumOfReps = 0;
        this.exerciseNumOfSets = 0;
        this.exerciseRestTime = 0;
        this.exerciseRating = -1;
    }
}
