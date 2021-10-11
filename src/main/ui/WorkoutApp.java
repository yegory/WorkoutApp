package ui;

import model.Exercise;
import model.Routine;
import model.Routines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WorkoutApp {
    private Scanner input;
    private List<Exercise> listOfExercises;
    private Routine routine;
    private Routines listOfRoutines;

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

        listOfRoutines = new Routines();
        listOfRoutines.addRoutine(chestWorkout);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void showMainMenu() {
        System.out.println("\nMain menu, select one of:");
        System.out.println("\t[1] - view exercise menu");
        System.out.println("\t[2] - view routine menu");
        System.out.println("\t[q] - quit");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void processMenuChoice(String userInput) {
        if (userInput.equals("1")) {
            separatorLine();
            runExerciseMenu();
        } else if (userInput.equals("2")) {
            runRoutinesMenu();
        } else {
            separatorLine();
            System.out.println("\t\t\tSelection not valid");
            separatorLine();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void runExerciseMenu() {
        boolean goBack = false;

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (!goBack) {
            showExerciseMenu();
            System.out.print("choice: ");
            String userInput = input.next();

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
        System.out.println("\t[1] - view all exercise");
        System.out.println("\t[2] - view favorite exercises");
        System.out.println("\t[3] - add a new exercise");
        System.out.println("\t[4] - delete an exercise");
        System.out.println("\t[5] - edit an exercise");
        System.out.println("\t[b] - back to main menu");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void runRoutinesMenu() {
        boolean goBack = false;

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (!goBack) {
            showRoutineMenu();
            System.out.print("choice: ");
            String userInput = input.next();

            if (userInput.equals("b")) {
                goBack = true;
                separatorLine();
            } else {
                processRoutineMenuChoice(userInput);
            }
        }
        showRoutineMenu();
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void showRoutineMenu() {
        System.out.println("\nRoutine menu, select one of:");
        System.out.println("[1] - view routines");
        System.out.println("[2] - view favorite routines");
        System.out.println("[3] - create a new routine");
        System.out.println("[4] - delete an existing routine");
        System.out.println("[5] - edit an existing routine");
        System.out.println("[b] - back to main menu");
    }


    private void processRoutineMenuChoice(String userInput) {
        switch (userInput) {
            case "1":
                viewRoutines();
                break;
            case "2":
                viewFavoriteRoutines();
                break;
            case "3":
                addRoutine();
                break;
            case "4":
                deleteRoutine();
                break;
            case "5":
                editRoutine();
                break;
            default:
                separatorLine();
                System.out.println("\t\t\tSelection not valid");
                separatorLine();
                break;
        }

    }


    private void viewRoutines() {
        System.out.println("\n");
        for (int i = 1; i <= listOfRoutines.size(); i++) {
            separatorLine();
            System.out.println("Routine " + i + ":");
            listOfRoutines.getRoutine(i - 1).printRoutine();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void viewFavoriteRoutines() {
        int routineCount = 0;
        for (int i = 0; i < listOfRoutines.size(); i++) {
            Routine currentRoutine = listOfRoutines.getRoutine(i);
            if (currentRoutine.getRoutineRating() == 5) {
                separatorLine();
                routineCount += 1;
                System.out.println("Exercise " + routineCount + ":");
                currentRoutine.printRoutine();
            }
        }
        if (routineCount == 0) {
            System.out.println("\n\tSeems that you don't have any favorite routine!");
            System.out.println("\t(A favorite routine is either: 'favorite' and/or has a rating of 5)");
            separatorLine();
        }

    }


    private void addRoutine() {
        input = new Scanner(System.in);

        System.out.println("Please input the routine name");
        String name = input.nextLine();

        System.out.println("Please input the routine description");
        String description = input.nextLine();

        System.out.println("Please input the numbers of each corresponding exercise you want to add (in order)");
        displayAllExerciseNames();
        String userInput = input.nextLine();
        List<Exercise> includedExercises = buildUpIncludedExerciseList(userInput);

        System.out.println("Please assign a rating [1-5 or -1 for N/A]");
        int routineRating = input.nextInt();

        Routine routine = new Routine(name, description, includedExercises, routineRating);
        listOfRoutines.addRoutine(routine);
        System.out.println("Exercise successfully added!");
    }

    private List<Exercise> buildUpIncludedExerciseList(String userInput) {
        int userInputInt = Integer.parseInt(userInput);
        List<Exercise> includedExercises = new ArrayList<>();
        while (userInput.length() > 0) {
            if (Integer.parseInt(userInput.substring(0, 1)) <= listOfExercises.size()) {
                includedExercises.add(listOfExercises.get(Integer.parseInt(userInput.substring(0, 1)) - 1));
                userInput = userInput.substring(1);
            } else {
                System.out.println("Error, exercise number specified outside the range!");
            }
        }
        return includedExercises;
    }

    private void displayAllExerciseNames() {
        for (int i = 1; i <= listOfExercises.size(); i++) {
            System.out.println("Exercise " + i + ": " + listOfExercises.get(i - 1).getExerciseName());
        }
    }


    private void deleteRoutine() {
    }

    private void editRoutine() {
    }


    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void processExerciseMenuChoice(String userInput) {
        switch (userInput) {
            case "1":
                viewExercises();
                break;
            case "2":
                viewFavoriteExercises();
                break;
            case "3":
                addExercise();
                break;
            case "4":
                deleteExercise();
                break;
            case "5":
                editExercise();
                break;
            default:
                separatorLine();
                System.out.println("\t\t\tSelection not valid");
                separatorLine();
                break;
        }
    }

    private void viewExercises() {
        System.out.println("\n");
        for (int i = 1; i <= listOfExercises.size(); i++) {
            separatorLine();
            System.out.println("Exercise " + i + ":");
            listOfExercises.get(i - 1).printExercise();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void viewFavoriteExercises() {
        int exerciseCount = 0;
        for (int i = 0; i < listOfExercises.size(); i++) {
            Exercise currentExercise = listOfExercises.get(i);
            if (currentExercise.getExerciseRating() == 5) {
                separatorLine();
                exerciseCount += 1;
                System.out.println("Exercise " + exerciseCount + ":");
                currentExercise.printExercise();
            }
        }
        if (exerciseCount == 0) {
            System.out.println("\n\tSeems that you don't have any favorite exercises!");
            System.out.println("\t(A favorite exercise is either: 'favorite' and/or has a rating of 5)");
            separatorLine();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS: ask user inputs for each field in exercise,
    //          then create new exercise object and add it to the list of exercises
    private void addExercise() {
        input = new Scanner(System.in);

        System.out.println("Please input the exercise name");
        String name = input.nextLine();
        System.out.println("Please input the exercise description");
        String description = input.nextLine();

        System.out.println("Please input the number of reps");
        int reps = input.nextInt();
        System.out.println("Please input the number of sets");
        int sets = input.nextInt();
        System.out.println("Please input the rest time in seconds");
        int restTime = input.nextInt();
        System.out.println("Please assign a rating [1-5 or -1 for N/A]");
        int rating = input.nextInt();
        Exercise exercise = new Exercise(name, description, reps, sets, restTime, rating);
        listOfExercises.add(exercise);

        System.out.println("Exercise successfully added!");
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void deleteExercise() {
        String userInput = null;
        boolean decisionMade = false;
        input = new Scanner(System.in);
        System.out.println("Which exercise would you like to delete?");
        System.out.println("Type [1] to search using the exercise number or [2] to search with its name?");
        while (!decisionMade) {
            userInput = input.nextLine();
            if (userInput.equals("1")) {
                decisionMade = true;
                deleteExerciseUsingNumber();
            } else if (userInput.equals("2")) {
                decisionMade = true;
                deleteExerciseUsingName();
            }
        }
    }

    private void deleteExerciseUsingNumber() {
        input = new Scanner(System.in);
        System.out.println("What's the exercise number?");
        int userInput = input.nextInt();
        listOfExercises.remove(userInput - 1);
    }

    private void deleteExerciseUsingName() {
        input = new Scanner(System.in);
        boolean foundExercise = false;
        System.out.println("What's the exercise name?");
        String userInput = input.nextLine();
        for (int i = 0; i < listOfExercises.size(); i++) {
            if (listOfExercises.get(i).getExerciseName().equals(userInput)) {
                listOfExercises.remove(i);
                foundExercise = true;
                break;
            }
        }
        if (foundExercise) {
            System.out.println("Exercise removed!");
        } else {
            System.out.println("Exercise not found!");
        }
    }


    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void editExercise() {
        String userInput = null;
        boolean decisionMade = false;
        input = new Scanner(System.in);
        System.out.println("Which exercise would you like to edit?");
        System.out.println("Type [1] to search using the exercise number or [2] to search with its name?");
        while (!decisionMade) {
            userInput = input.nextLine();
            if (userInput.equals("1")) {
                decisionMade = true;
                editUsingNumber();
            } else if (userInput.equals("2")) {
                decisionMade = true;
                editUsingName();
            }
        }
    }

    private void editUsingNumber() {
        input = new Scanner(System.in);
        boolean foundExercise = false;
        System.out.println("What's the exercise number?");
        int userInput = input.nextInt();
        if (userInput >= 1 && userInput <= listOfExercises.size()) {
            System.out.println("Exercise found!");
            runEditMenu(listOfExercises.get(userInput - 1));
        } else {
            System.out.println("Number is out of range!");
        }
    }

    private void editUsingName() {
        input = new Scanner(System.in);
        boolean foundExercise = false;
        System.out.println("What's the exercise name?");
        String userInput = input.nextLine();
        for (int i = 0; i < listOfExercises.size(); i++) {
            Exercise exercise = listOfExercises.get(i);
            if (exercise.getExerciseName().equals(userInput)) {
                System.out.println("Exercise found!");
                foundExercise = true;
                runEditMenu(exercise);
            }
        }
        if (foundExercise) {
            System.out.println("Exercise not found!");
        }
    }

    private void runEditMenu(Exercise exercise) {
        boolean goBack = false;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (!goBack) {
            showEditMenu();
            System.out.print("choice: ");
            String userInput = input.next();

            if (userInput.equals("b")) {
                goBack = true;
                separatorLine();
            } else {
                processEditMenuChoice(userInput, exercise);
            }
        }
    }

    private void showEditMenu() {
        System.out.println("Select all information which you would like to change");
        System.out.println("\t[1] - to change name");
        System.out.println("\t[2] - to change description");
        System.out.println("\t[3] - to change number of reps");
        System.out.println("\t[4] - to change number of sets");
        System.out.println("\t[5] - to change rest time");
        System.out.println("\t[6] - to change rating");
        System.out.println("\t[b] - to go back to Exercise menu");
        System.out.println("e.g. if you want to change the \"Name\" and \"Rest time\" -> type 16");
    }

    private void processEditMenuChoice(String userInput, Exercise exercise) {
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == '1') {
                editExerciseName(exercise);
            } else if (userInput.charAt(i) == '2') {
                editExerciseDescription(exercise);
            } else if (userInput.charAt(i) == '3') {
                editExerciseNoR(exercise);
            } else if (userInput.charAt(i) == '4') {
                editExerciseNoS(exercise);
            } else if (userInput.charAt(i) == '5') {
                editExerciseRestTime(exercise);
            } else if (userInput.charAt(i) == '6') {
                editExerciseRating(exercise);
            }
        }
    }

    private void editExerciseName(Exercise exercise) {
        System.out.println("Please input the new name: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        exercise.setExerciseName(userInput);
    }

    private void editExerciseDescription(Exercise exercise) {
        System.out.println("Please input the new descriptions: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        exercise.setExerciseDescription(userInput);
    }

    private void editExerciseNoR(Exercise exercise) {
        System.out.println("Please input the new number of reps: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseNumOfReps(userInput);
    }

    private void editExerciseNoS(Exercise exercise) {
        System.out.println("Please input the new number of sets: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseNumOfSets(userInput);
    }

    private void editExerciseRestTime(Exercise exercise) {
        System.out.println("Please input the new rest time: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseRestTime(userInput);
    }

    private void editExerciseRating(Exercise exercise) {
        System.out.println("Please input the new rating: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseRating(userInput);
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

    Routine chestWorkout = new Routine("Chest Workout Hypertrophy", "Starts from easy exersises"
            + " and gets progressively more difficult",
            Arrays.asList(kneePushUps, normalPushUps, inclinedPushUps, benchPressHypertrophy), 5);
}
