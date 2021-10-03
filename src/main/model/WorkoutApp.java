package model;

import java.util.Scanner;

public class WorkoutApp {
    private Scanner scanner;
    private Exercise kneePushUps;
    private Exercise normalPushUps;
    private Exercise inclinedPushUps;


    public WorkoutApp() {
        runWorkout();
    }

    private void runWorkout() {
        init();
        kneePushUps.printExercise();
    }

    private void init() {
        kneePushUps = new Exercise("Knee push ups",
                "Start with your knees and hands on the floor. Adjust the distance between your knees and "
                        + "hands so that your body becomes straight. Execute the exercise by lowering and pushing up "
                        + "yourself with your hands. Make sure to keep your body straight.",
                10, 3, 60, 2);
        normalPushUps = new Exercise("Normal push ups",
                "A bodyweight exercise where one pushes their body up starting from a prone position using "
                        + "their arms. Only the toes and hands must be in contact with the ground",
                15, 3, 45, 3);
        inclinedPushUps = new Exercise("Inclined push ups",
                "Similar to the normal push up, this is a bodyweight exercise where one pushes their body up."
                        + " However, the difference here is that you want to raise the feet above the body using "
                        + "something solid. This exercise requires more strength as more weight is put on the hands",
                15, 3, 45, 4);
    }
}
