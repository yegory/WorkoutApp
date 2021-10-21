package ui;

import model.Exercise;
import model.Routine;
import model.Routines;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import static model.Routine.TIME_FOR_1REP;

public class WorkoutApp {
    private static final String JSON_STORE = "./data/WorkoutAppData.json";
    private Scanner input;
    private List<Exercise> listOfExercises;
    private Routines listOfRoutines;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: Runs the Workout application
    public WorkoutApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runWorkout();
    }


    // MODIFIES: this
    // EFFECTS: processes user input on the Main menu screen
    private void runWorkout() {
        boolean stopApp = false;

        init();

        while (!stopApp) {
            showMainMenu();
            System.out.print("choice: ");
            String userInput = input.next();

            if (userInput.equals("q")) {
                stopApp = true;
            } else {
                processMenuChoice(userInput);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user input on the Exercise screen
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

    // MODIFIES: this
    // EFFECTS: processes the user input on the Routine menu screen
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

    // MODIFIES: this
    // EFFECTS: processes the user input on the Exercise edit menu screen
    private void runExerciseEditMenu(Exercise exercise) {
        boolean goBack = false;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (!goBack) {
            showExerciseEditMenu();
            System.out.print("choice: ");
            String userInput = input.next();

            if (userInput.equals("b")) {
                goBack = true;
                separatorLine();
            } else {
                processExerciseEditMenuChoice(userInput, exercise);
                goBack = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user input on the Routine edit menu screen
    private void runRoutineEditMenu(Routine routine) {
        boolean goBack = false;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        while (!goBack) {
            showRoutineEditMenu();
            System.out.print("choice: ");
            String userInput = input.next();

            if (userInput.equals("b")) {
                goBack = true;
                separatorLine();
            } else {
                processRoutineEditMenuChoice(userInput, routine);
                goBack = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes the ListOfExercises and ListOfRoutines and adds pre-made
    //          exercises/routines that are found at the bottom of this file.
    private void init() {
        listOfExercises = new ArrayList<>();
        listOfRoutines = new Routines();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Displays the main menu.
    private void showMainMenu() {
        System.out.println("\nMain menu, select one of:");
        System.out.println("\t[1] - view exercise menu");
        System.out.println("\t[2] - view routine menu");
        System.out.println("\t[q] - quit");
    }

    // EFFECTS: displays Exercise menu options
    private void showExerciseMenu() {
        System.out.println("\nExercise menu, select one of:");
        System.out.println("\t[1] - view all exercise");
        System.out.println("\t[2] - view favorite exercises");
        System.out.println("\t[3] - add a new exercise");
        System.out.println("\t[4] - delete an exercise");
        System.out.println("\t[5] - edit an exercise");
        System.out.println("\t[b] - back to main menu");
    }

    // EFFECTS: Displays the Routine menu.
    private void showRoutineMenu() {
        System.out.println("\nRoutine menu, select one of:");
        System.out.println("\t[1] - view routines");
        System.out.println("\t[2] - view favorite routines");
        System.out.println("\t[3] - create a new routine");
        System.out.println("\t[4] - delete an existing routine");
        System.out.println("\t[5] - edit an existing routine");
        System.out.println("\t[b] - back to main menu");
    }

    // EFFECTS: Displays the Exercise edit menu.
    private void showExerciseEditMenu() {
        System.out.println("Select all information that you want to change a single string");
        System.out.println("\t[1] - to change name");
        System.out.println("\t[2] - to change description");
        System.out.println("\t[3] - to change number of reps");
        System.out.println("\t[4] - to change number of sets");
        System.out.println("\t[5] - to change rest time");
        System.out.println("\t[6] - to change rating");
        System.out.println("\t[b] - to go back to Exercise menu");
    }

    // EFFECTS: Displays the Routine edit menu.
    private void showRoutineEditMenu() {
        System.out.println("Select all information that you want to change a single string");
        System.out.println("\t[1] - change name");
        System.out.println("\t[2] - change description");
        System.out.println("\t[3] - change included exercises");
        System.out.println("\t[4] - change rating");
        System.out.println("\t[b] - go back to routine menu");
    }

    // MODIFIES: this
    // EFFECTS: processes the user's choice from main menu screen
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

    // MODIFIES: this
    // EFFECTS: processes the user's choice from exercise menu screen
    private void processExerciseMenuChoice(String userInput) {
        if (userInput.equals("1")) {
            viewExercises();
        } else if (userInput.equals("2")) {
            viewFavoriteExercises();
        } else if (userInput.equals("3")) {
            processAddExercise();
        } else if (userInput.equals("4")) {
            processDeleteExercise();
        } else if (userInput.equals("5")) {
            processEditExercise();
        } else {
            separatorLine();
            System.out.println("\t\t\tSelection not valid");
            separatorLine();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user's choice from routine menu screen
    private void processRoutineMenuChoice(String userInput) {
        if (userInput.equals("1")) {
            viewRoutines();
        } else if (userInput.equals("2")) {
            viewFavoriteRoutines();
        } else if (userInput.equals("3")) {
            processAddRoutine();
        } else if (userInput.equals("4")) {
            deleteRoutine();
        } else if (userInput.equals("5")) {
            processEditRoutine();
        } else {
            separatorLine();
            System.out.println("\t\t\tSelection not valid");
            separatorLine();
        }
    }

    // MODIFIES: this and listOfExercises, creates a new exercise
    // REQUIRES: the input to exercise reps, sets, rest time, and rating are all integers
    // EFFECTS: ask user inputs for each field in exercise,
    //          then create new exercise object and add it to the list of exercises
    private void processAddExercise() {
        input = new Scanner(System.in);

        System.out.print("Please input the exercise name: ");
        String name = input.nextLine();
        System.out.print("Please input the exercise description: ");
        String description = input.nextLine();
        System.out.print("Please input the number of reps: ");
        int reps = input.nextInt();
        System.out.print("Please input the number of sets: ");
        int sets = input.nextInt();
        System.out.print("Please input the rest time in seconds: ");
        int restTime = input.nextInt();
        System.out.print("Please assign a rating [1-5 or -1 for N/A]: ");
        int rating = input.nextInt();
        Exercise exercise = new Exercise(name, description, reps, sets, restTime, rating);
        listOfExercises.add(exercise);

        System.out.println("\nExercise successfully added!");
    }

    // MODIFIES: this and listOfRoutines
    // REQUIRES: the included Exercises prompt needs integers
    // EFFECTS: constructs a new routine
    private void processAddRoutine() {
        input = new Scanner(System.in);

        System.out.print("Please input the routine name: ");
        String name = input.nextLine();

        System.out.print("Please input the routine description: ");
        String description = input.nextLine();

        System.out.println("\nPlease input the numbers of each corresponding exercise you want to add (in order)");
        displayAllExerciseNames();
        System.out.print("choice: ");
        String userInput = input.nextLine();
        List<Exercise> includedExercises = buildUpIncludedExerciseList(userInput);

        System.out.println("Please assign a rating [1-5 or -1 for N/A]");
        int routineRating = input.nextInt();

        Routine routine = new Routine(name, description, includedExercises, routineRating);
        listOfRoutines.addRoutine(routine);
        System.out.println("Exercise successfully added!");
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to decide whether they want to search for the exercise using its name or number
    //          if input is "b" -> the user is returned to Exercise Menu
    private void processDeleteExercise() {
        boolean decisionMade = false;
        input = new Scanner(System.in);
        while (!decisionMade) {
            separatorLine();
            System.out.println("Which exercise would you like to delete?");
            System.out.println("\tType [1] to search using the exercise number");
            System.out.println("\tType [2] to search using the exercise name");
            System.out.println("\tType [b] to go back");
            System.out.print("choice: ");
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                decisionMade = true;
                deleteExerciseUsingNumber();
            } else if (userInput.equals("2")) {
                decisionMade = true;
                deleteExerciseUsingName();
            } else if (userInput.equals("b")) {
                decisionMade = true;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: parses the user input's input. Ignores every character except the ones that are looked for below;
    private void processExerciseEditMenuChoice(String userInput, Exercise exercise) {
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

    // MODIFIES: this
    // EFFECTS: Parses the user's string input one char at a time, and calls the
    //          corresponding methods.
    private void processRoutineEditMenuChoice(String userInput, Routine routine) {
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == '1') {
                editRoutineName(routine);
            } else if (userInput.charAt(i) == '2') {
                editRoutineDescription(routine);
            } else if (userInput.charAt(i) == '3') {
                editRoutineIncludedExercises(routine);
            } else if (userInput.charAt(i) == '4') {
                editRoutineRating(routine);
            }
        }
        System.out.println("\nDone!\n");
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to select either to search using the exercise number or exercise name
    //          if input is "b", the user is brought back to the exerciseMenu
    //          else, an error is displayed and the user is asked for input again.
    private void processEditExercise() {
        boolean decisionMade = false;
        input = new Scanner(System.in);
        while (!decisionMade) {
            separatorLine();
            System.out.println("Which exercise would you like to delete?");
            System.out.println("\tType [1] to search using the exercise number");
            System.out.println("\tType [2] to search using the exercise name");
            System.out.println("\tType [b] to go back");
            System.out.print("choice: ");
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                decisionMade = true;
                editExerciseUsingNumber();
            } else if (userInput.equals("2")) {
                decisionMade = true;
                editExerciseUsingName();
            } else if (userInput.equals("b")) {
                decisionMade = true;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to select either to search using the routine number or routine name
    //          if input is "b", the user is brought back to the routine Menu
    //          else, an error is displayed and the user is asked for input again.
    private void processEditRoutine() {
        boolean decisionMade = false;

        separatorLine();
        System.out.println("Which routine would you like to edit?");
        System.out.println("\tType [1] to search using the routine number");
        System.out.println("\tType [2] to search using the routine name");
        System.out.println("\tType [b] to go back");
        System.out.print("choice: ");
        input = new Scanner(System.in);
        while (!decisionMade) {
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                decisionMade = true;
                editRoutineUsingNumber();
            } else if (userInput.equals("2")) {
                decisionMade = true;
                editRoutineUsingName();
            } else if (userInput.equals("b")) {
                decisionMade = true;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    // EFFECTS: outputs all the exercise information in order as they appear in listOfExercises
    private void viewExercises() {
        System.out.println("\n");
        for (int i = 1; i <= listOfExercises.size(); i++) {
            separatorLine();
            System.out.println("Exercise " + i + ": " + listOfExercises.get(i - 1).printExercise());
        }
    }

    // EFFECTS: outputs all the routine information in order as they appear in listOfRoutines
    private void viewRoutines() {
        System.out.println("\n");
        if (listOfRoutines.size() == 0) {
            System.out.println("There are no routines. You can add some.");
        } else {
            for (int i = 1; i <= listOfRoutines.size(); i++) {
                separatorLine();
                System.out.println("Routine " + i + ":");
                System.out.println(listOfRoutines.getRoutine(i - 1).printRoutine());
            }
        }
    }

    // EFFECTS: for each exercise that has a rating of 5,
    //          outputs all the exercise information in order as they appear in listOfExercises
    private void viewFavoriteExercises() {
        int exerciseCount = 0;
        for (Exercise currentExercise : listOfExercises) {
            if (currentExercise.getExerciseRating() == 5) {
                separatorLine();
                exerciseCount += 1;
                System.out.println("Exercise " + exerciseCount + ":");
                System.out.println(currentExercise.printExercise());
            }
        }
        if (exerciseCount == 0) {
            System.out.println("\n\tSeems that you don't have any favorite exercises!");
            System.out.println("\t(A favorite exercise is either: 'favorite' and/or has a rating of 5)");
            separatorLine();
        }
    }

    // EFFECTS: for each routine that has a rating of 5,
    //          outputs all the routine information in order as they appear in listOfRoutines
    private void viewFavoriteRoutines() {
        int routineCount = 0;
        for (int i = 0; i < listOfRoutines.size(); i++) {
            Routine currentRoutine = listOfRoutines.getRoutine(i);
            if (currentRoutine.getRoutineRating() == 5) {
                separatorLine();
                routineCount += 1;
                System.out.println("Routine " + routineCount + ":");
                System.out.println(currentRoutine.printRoutine());
            }
        }
        if (routineCount == 0) {
            System.out.println("\n\tSeems that you don't have any favorite routine!");
            System.out.println("\t(A favorite routine is either: 'favorite' and/or has a rating of 5)");
            separatorLine();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to decide whether they want to search for the routine using its name or number
    //          if input is "b" -> the user is returned to Routine Menu
    private void deleteRoutine() {
        boolean decisionMade = false;
        input = new Scanner(System.in);

        while (!decisionMade) {
            separatorLine();
            System.out.println("Which exercise would you like to delete?");
            System.out.println("\tType [1] to search using the exercise number");
            System.out.println("\tType [2] to search using the exercise name");
            System.out.println("\tType [b] to go back");
            System.out.print("choice: ");
            String userInput = input.nextLine();
            if (userInput.equals("1")) {
                decisionMade = true;
                deleteRoutineUsingNumber();
            } else if (userInput.equals("2")) {
                decisionMade = true;
                deleteRoutineUsingName();
            } else if (userInput.equals("b")) {
                decisionMade = true;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to select the routine number same as listed under the viewRoutines,
    //          if the user input is out of bounds (not one of the routine numbers, prints an error)
    private void deleteRoutineUsingNumber() {
        input = new Scanner(System.in);
        separatorLine();
        System.out.println("What's the routine number?");
        displayAllRoutineNames();
        int userInput = input.nextInt();
        try {
            listOfRoutines.removeRoutine(userInput - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not find the routine associated with that number!");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user to delete using the routine name
    private void deleteRoutineUsingName() {
        input = new Scanner(System.in);
        boolean foundExercise = false;
        separatorLine();
        System.out.println("What's the routine name?");
        displayAllRoutineNames();
        String userInput = input.nextLine();
        for (int i = 0; i < listOfRoutines.size(); i++) {
            if (listOfRoutines.getRoutine(i).getRoutineName().equals(userInput)) {
                listOfRoutines.removeRoutine(i);
                foundExercise = true;
                break;
            }
        }
        if (foundExercise) {
            System.out.println("Routine removed!");
        } else {
            System.out.println("Routine not found!");
        }
    }

    // MODIFIES: this
    // REQUIRES: the userInput is an integer
    // EFFECTS: prompts the user to select the exercise number as listed in the viewExercises,
    //          if the user input is out of bounds (not one of the exercise numbers, prints an error)
    private void deleteExerciseUsingNumber() {
        input = new Scanner(System.in);
        System.out.println("What's the exercise number?");
        displayAllExerciseNames();
        int userInput = input.nextInt();
        try {
            listOfExercises.remove(userInput - 1);
            System.out.println("Exercise removed");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not find the exercise associated with that number!");
        }
    }

    // MODIFIES: this
    // EFFECTS: if exercise found, removes the selected exercise from listOfExercises
    //          else prints a message to the user
    private void deleteExerciseUsingName() {
        input = new Scanner(System.in);
        boolean foundExercise = false;
        System.out.println("What's the exercise name?");
        displayAllExerciseNames();
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

    // MODIFIES: this and exercise
    // REQUIRES: the input is an integer
    // EFFECTS: if the inputted integer is found in the listOfExercise list, the runExerciseEditMenu is run with the
    //          corresponding exercise that was selected by the user.
    //
    private void editExerciseUsingNumber() {
        input = new Scanner(System.in);
        System.out.println("What's the exercise number?");
        displayAllExerciseNames();
        int userInput = input.nextInt();
        if (userInput >= 1 && userInput <= listOfExercises.size()) {
            System.out.println("Exercise found!");
            runExerciseEditMenu(listOfExercises.get(userInput - 1));
        } else {
            System.out.println("Number is out of range!");
        }
    }

    // MODIFIES: this and exercise
    // EFFECTS: processes user input to select preferable edit method
    private void editExerciseUsingName() {
        input = new Scanner(System.in);
        boolean foundExercise = false;
        separatorLine();
        System.out.println("What's the exercise name?");
        displayAllExerciseNames();
        System.out.print("choice: ");
        String userInput = input.nextLine();
        for (Exercise exercise : listOfExercises) {
            if (exercise.getExerciseName().equals(userInput)) {
                System.out.println("Exercise found!");
                foundExercise = true;
                runExerciseEditMenu(exercise);
            }
        }
        if (!foundExercise) {
            System.out.println("Exercise not found!");
        }
    }

    // MODIFIES: this and routine
    // REQUIRES: user input is an integer
    // EFFECTS: processes user to edit using the routine number
    private void editRoutineUsingNumber() {
        input = new Scanner(System.in);
        separatorLine();
        System.out.println("What's the routine number?");
        displayAllRoutineNames();
        int userInput = input.nextInt();
        if (userInput >= 1 && userInput <= listOfRoutines.size()) {
            System.out.println("Routine found!");
            runRoutineEditMenu(listOfRoutines.getRoutine(userInput - 1));
        } else {
            System.out.println("Number is out of range!");
        }
    }

    // MODIFIES: this and routine
    // EFFECTS: processes user to edit using the routine name
    private void editRoutineUsingName() {
        input = new Scanner(System.in);
        boolean foundRoutine = false;
        separatorLine();
        System.out.println("What's the exercise name?");
        displayAllRoutineNames();
        System.out.print("choice: ");
        String userInput = input.nextLine();
        for (int i = 0; i < listOfRoutines.size(); i++) {
            Routine routine = listOfRoutines.getRoutine(i);
            if (routine.getRoutineName().equals(userInput)) {
                System.out.println("Routine found!");
                foundRoutine = true;
                runRoutineEditMenu(routine);
            }
        }
        if (!foundRoutine) {
            System.out.println("Exercise not found!");
        }
    }

    // MODIFIES: exercise
    // EFFECTS: sets the exercise name to the users input (or assigns a temporary name)
    private void editExerciseName(Exercise exercise) {
        System.out.print("Please input the new name: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        exercise.setExerciseName(userInput);
    }

    // MODIFIES: exercise
    // EFFECTS: sets the exercise description to the users input
    private void editExerciseDescription(Exercise exercise) {
        System.out.print("Please input the new description: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        exercise.setExerciseDescription(userInput);
    }

    // MODIFIES: exercise
    // REQUIRES: user's input is an integer
    // EFFECTS: sets the exercise rest time to the users input (if input <= 0: then it's set to 1)
    private void editExerciseNoR(Exercise exercise) {
        System.out.print("Please input the new number of reps: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseNumOfReps(userInput);
    }

    // MODIFIES: exercise
    // REQUIRES: user's input is an integer
    // EFFECTS: sets the exercise rest time to the users input (if input <= 0: then it's set to 1)
    private void editExerciseNoS(Exercise exercise) {
        System.out.print("Please input the new number of sets: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseNumOfSets(userInput);
    }

    // MODIFIES: exercise
    // REQUIRES: user's input is an integer
    // EFFECTS: sets the exercise rest time to the users input (if input <= 0: then it's set to 0)
    private void editExerciseRestTime(Exercise exercise) {
        System.out.print("Please input the new rest time: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseRestTime(userInput);
    }

    // MODIFIES: exercise
    // REQUIRES: user's input is an integer
    // EFFECTS: sets the exercise rating to the users input
    private void editExerciseRating(Exercise exercise) {
        System.out.print("Please input the new rating: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        exercise.setExerciseRating(userInput);
    }

    // MODIFIES: routine
    // REQUIRES: the input is non-empty (the function handles this though)
    // EFFECTS: prompts the user to input a non-empty new name for the routine
    //          when input is non-empty, sets the Routine name to the user input.
    private void editRoutineName(Routine routine) {
        System.out.print("Please input the new name: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        while (userInput.length() == 0) {
            System.out.print("Please input a non-empty name: ");
            userInput = input.nextLine();
        }
        routine.setRoutineName(userInput);
    }

    // MODIFIES: routine
    // EFFECTS: prompts the user to input a new description for the routine
    //          afterward sets the routine description to user input
    private void editRoutineDescription(Routine routine) {
        System.out.print("Please input the new descriptions: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        routine.setRoutineDescription(userInput);
    }

    // MODIFIES: routine
    // REQUIRES: the user input consists only of integers
    // EFFECTS: modifies the Array of Exercises in the routine and sets new total time to complete the routine
    private void editRoutineIncludedExercises(Routine routine) {
        System.out.println("Please input the numbers of each corresponding exercise you want to add");
        System.out.println("The order of the exercises will be reflected by the order of your selections");
        displayAllRoutineNames();
        System.out.print("choice: ");
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        routine.setIncludedExercises(buildUpIncludedExerciseList(userInput));
        calculateNewTotalTime(routine);
    }

    // MODIFIES: routine
    // EFFECTS: updates the exercise to reflect a new total time to complete the included exercises
    public void calculateNewTotalTime(Routine routine) {
        int totalExerciseTime = 0;
        for (Exercise exercise : routine.getIncludedExercises()) {
            totalExerciseTime += exercise.getExerciseRestTime() * exercise.getExerciseNumOfSets()
                    + exercise.getExerciseNumOfSets() * exercise.getExerciseNumOfReps() * TIME_FOR_1REP;
        }
        routine.setTotalTimeToComplete(totalExerciseTime);
    }

    // MODIFIES: Routine
    // EFFECTS: sets a new rating for the routine
    private void editRoutineRating(Routine routine) {
        System.out.println("Please input the new rating: ");
        input = new Scanner(System.in);
        int userInput = input.nextInt();
        routine.setRoutineRating(userInput);
    }

    // REQUIRES: user input is a String of integers only
    // EFFECTS: returns an Array List of exercises that were chosen by the user. (helper for add routine etc)
    private List<Exercise> buildUpIncludedExerciseList(String userInput) {
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

    // MODIFIES: this
    // EFFECTS: displays all the exercise name
    //          example of possible output: "Routine 2: Routine 2 Name"
    private void displayAllExerciseNames() {
        for (int i = 1; i <= listOfExercises.size(); i++) {
            System.out.println("Exercise " + i + ": " + listOfExercises.get(i - 1).getExerciseName());
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all the routine names in order as they appear in
    //          example of possible output: "Routine 2: Routine 2 Name"
    private void displayAllRoutineNames() {
        for (int i = 1; i <= listOfRoutines.size(); i++) {
            System.out.println("Routine " + i + ": " + listOfRoutines.getRoutine(i - 1).getRoutineName());
        }
    }

    // EFFECTS: prints a nice looking separator line,
    //          created a separate method for this so that length stays consistent
    private void separatorLine() {
        System.out.println("---------------------------------------------------");
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkoutProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfExercises);
            jsonWriter.write(listOfRoutines);
            jsonWriter.close();
            System.out.println("Saved exercises to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}