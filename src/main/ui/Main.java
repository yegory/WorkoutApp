package ui;

import java.io.FileNotFoundException;

// exception inspired by JsonSerializationDemo (link in README)
public class Main {
    public static void main(String[] args) {
        try {
            new WorkoutApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}