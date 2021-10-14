package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {
    private Exercise testExercise;
    private Exercise testExercise2;
    private Exercise testExercise3;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Name", "Description",
                1, 2, 3, -1);
        testExercise2 = new Exercise("Name", "Description",
                1, 2, 3, 5);
        testExercise3 = new Exercise("Name", "Description",
                1, 2, 3, 6);
    }

    @Test
    void testConstructor() {
        assertEquals("Name", testExercise.getExerciseName());
        assertEquals("Description", testExercise.getExerciseDescription());
        assertEquals(1, testExercise.getExerciseNumOfReps());
        assertEquals(2, testExercise.getExerciseNumOfSets());
        assertEquals(-1, testExercise.getExerciseRating());
    }

    @Test
    void testReturnDefinedRating() {
        assertEquals("Unrated", testExercise.returnDefinedRating());
        assertEquals("A - Amazing", testExercise2.returnDefinedRating());
        assertEquals("Unrated", testExercise3.returnDefinedRating());
    }

}