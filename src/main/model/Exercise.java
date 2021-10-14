package model;

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

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Exercise(String exsName, String exsDesc, int exsNoR, int exsNoS, int exsRT, int exsRating) {
        this.exerciseName = exsName;
        this.exerciseDescription = exsDesc;
        this.exerciseNumOfReps = exsNoR;
        this.exerciseNumOfSets = exsNoS;
        this.exerciseRestTime = exsRT;
        this.exerciseRating = exsRating;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void printExercise() {
        int maxLineLength = 65;
        String exerciseDescription1 = "[" + exerciseName + "]: " + exerciseDescription;

        while (exerciseDescription1.length() > maxLineLength) {
            while (' ' != (exerciseDescription1.charAt(maxLineLength))) {
                maxLineLength += 1;
            }
            String printOut = exerciseDescription1.substring(0, maxLineLength).trim();
            System.out.println(printOut);
            exerciseDescription1 = exerciseDescription1.substring(maxLineLength);
            maxLineLength = 65;
        }

        System.out.println(exerciseDescription1.trim() + "\n"
                + "Number of reps: " + exerciseNumOfReps + "\n"
                + "Number of sets: " + exerciseNumOfSets + "\n"
                + "Rest time     : " + exerciseRestTime + " seconds\n"
                + "Rating        : " + returnDefinedRating());
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
        exerciseName = name;
    }

    public void setExerciseDescription(String desc) {
        exerciseDescription = desc;
    }

    public void setExerciseNumOfReps(int reps) {
        exerciseNumOfReps = reps;
    }

    public void setExerciseNumOfSets(int sets) {
        exerciseNumOfSets = sets;
    }

    public void setExerciseRestTime(int restTime) {
        exerciseRestTime = restTime;
    }

    public void setExerciseRating(int rating) {
        exerciseRating = rating;
    }
}
