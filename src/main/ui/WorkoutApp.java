package ui;

import model.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkoutApp {
    private Scanner input;
    private List<Exercise> listOfExercises;

    // EFFECTS: Runs the Workout application
    public WorkoutApp() {
        runWorkout();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runWorkout() {
        boolean stopApp = false;
        String userInput = null;

        init();

        while (!stopApp) {
            showMainMenu();
            System.out.print("choice: ");
            userInput = input.next();

            if (userInput.equals("q")) {
                stopApp = true;
            } else {
                processMenuChoice(userInput);
            }
        }

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void init() {
        listOfExercises = new ArrayList<>();
        listOfExercises.add(kneePushUps);
        listOfExercises.add(normalPushUps);
        listOfExercises.add(inclinedPushUps);
        listOfExercises.add(benchPressHypertrophy);
        listOfExercises.add(benchPressStrength);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void showMainMenu() {
        System.out.println("\nMain menu, select one of:");
        System.out.println("\t[1] - view all exercises");
        System.out.println("\t[2] - view favorite exercises");
        System.out.println("\t[3] - view routines");
        System.out.println("\t[4] - view favorite routines");
        System.out.println("\t[q] - quit");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void processMenuChoice(String userInput) {
        if (userInput.equals("1")) {
            separatorLine();
            viewExercises();
        } else if (userInput.equals("2")) {
            viewFavoriteExercises();
        } else if (userInput.equals("3")) {
            viewRoutines();
        } else if (userInput.equals("4")) {
            viewFavoriteRoutines();
        } else {
            separatorLine();
            System.out.println("\t\t\tSelection not valid");
            separatorLine();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void viewExercises() {
        boolean goBack = false;
        String userInput = null;

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (!goBack) {
            showExerciseMenu();
            System.out.print("choice: ");
            userInput = input.next();

            if (userInput.equals("b")) {
                goBack = true;
                separatorLine();
            } else {
                processExerciseMenuChoice(userInput);
            }
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void showExerciseMenu() {
        System.out.println("\nExercise menu, select one of:");
        System.out.println("\t[1] - add a new exercise");
        System.out.println("\t[2] - delete an exercise");
        System.out.println("\t[3] - edit an exercise");
        System.out.println("\t[b] - back to main menu");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void viewFavoriteExercises() {
        //
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void viewRoutines() {
        showRoutineMenu();
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void showRoutineMenu() {
        System.out.println("\nRoutine menu, select one of:");
        System.out.println("[1] - create a new routine");
        System.out.println("[2] - delete an existing routine");
        System.out.println("[3] - edit an existing routine");
        System.out.println("[b] - back to main menu");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void viewFavoriteRoutines() {
        //
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void processExerciseMenuChoice(String userInput) {
        if (userInput.equals("1")) {
            addExercise();
        } else if (userInput.equals("2")) {
            deleteExercise();
        } else if (userInput.equals("3")) {
            editExercise();
        } else {
            separatorLine();
            System.out.println("\t\t\tSelection not valid");
            separatorLine();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS: ask user inputs for each field in exercise,
    //          then create new exercise object and add it to the list of exercises
    private void addExercise() {
        //
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void deleteExercise() {
        //
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void editExercise() {
        //
    }

    private void separatorLine() {
        System.out.println("---------------------------------------------");
    }


    // SOME PRE-MADE EXERCISES

    Exercise kneePushUps = new Exercise("Knee push ups",
            "Start with your knees and hands on the floor. Adjust the distance between your knees and "
                    + "hands so that your body becomes straight. Execute the exercise by lowering and pushing up "
                    + "yourself with your hands. Make sure to keep your body straight.",
            10, 3, 60, 2);

    Exercise normalPushUps = new Exercise("Normal push ups",
            "A bodyweight exercise where one pushes their body up starting from a prone position using "
                    + "their arms. Only the toes and hands must be in contact with the ground.",
            15, 3, 45, 3);

    Exercise inclinedPushUps = new Exercise("Inclined push ups",
            "Similar to the normal push up, this is a bodyweight exercise where one pushes their body up."
                    + " However, the difference here is that you want to raise the feet above the body using a "
                    + "block or bench. This exercise requires more strength as more weight is put on the hands.",
            15, 3, 45, 4);

    Exercise benchPressHypertrophy = new Exercise("Bench press hypertrophy",
            "Load the bench press with medium weights to tire out"
                    + " the muscle. Make sure to control the weight on the on the way down, and up.",
            15, 2, 60, 4);

    Exercise benchPressStrength = new Exercise("Bench press for strength",
            "Load the bench press with a heavy load for maximum muscle activation."
                    + " The goal is to move the weight from point A to point B, so you need to coordinate well"
                    + " but don't stretch out the movement longer than needed.",
            5, 5, 300, 4);
}
