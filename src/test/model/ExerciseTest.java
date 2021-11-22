package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {
    private Exercise testExercise;
    private Exercise testExercise2;
    private Exercise testExercise3;
    private Exercise illegalExerciseExample;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Crunches", "Trains the abs.", 1, 2, 3, -1);
        testExercise2 = new Exercise("Push up", "Trains the chest.", 1, 2, 3, 5);
        testExercise3 = new Exercise("Triceps extensions", "Trains the triceps", 1, 2, 3, 6);
        illegalExerciseExample = new Exercise("", "", -5, 0, -91, 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Crunches", testExercise.getExerciseName());
        assertEquals("Trains the abs.", testExercise.getExerciseDescription());
        assertEquals(1, testExercise.getExerciseNumOfReps());
        assertEquals(2, testExercise.getExerciseNumOfSets());
        assertEquals(3, testExercise.getExerciseRestTime());
        assertEquals(-1, testExercise.getExerciseRating());

        assertEquals("TempName1105", illegalExerciseExample.getExerciseName());
        assertEquals("", illegalExerciseExample.getExerciseDescription());
        assertEquals(1, illegalExerciseExample.getExerciseNumOfReps());
        assertEquals(1, illegalExerciseExample.getExerciseNumOfSets());
        assertEquals(0, illegalExerciseExample.getExerciseRestTime());
        assertEquals(5, illegalExerciseExample.getExerciseRating());
    }

    @Test
    void testReturnDefinedRating() {
        assertEquals("Unrated", testExercise.returnDefinedRating());
        testExercise.setExerciseRating(0);
        assertEquals("F - Atrocious", testExercise.returnDefinedRating());
        testExercise.setExerciseRating(1);
        assertEquals("E - Terrible", testExercise.returnDefinedRating());
        testExercise.setExerciseRating(2);
        assertEquals("D - Ok-ish", testExercise.returnDefinedRating());
        testExercise.setExerciseRating(3);
        assertEquals("C - Decent", testExercise.returnDefinedRating());
        testExercise.setExerciseRating(4);
        assertEquals("B - Great", testExercise.returnDefinedRating());
        testExercise.setExerciseRating(5);
        assertEquals("A - Amazing", testExercise.returnDefinedRating());
    }

    @Test
    void testSetExerciseName() {
        testExercise.setExerciseName("");
        testExercise2.setExerciseName("NewName123");
        assertEquals("TempName123-1", testExercise.getExerciseName());
        assertEquals("NewName123", testExercise2.getExerciseName());
    }

    @Test
    void testSetExerciseDescription() {
        testExercise.setExerciseDescription("");
        testExercise2.setExerciseDescription("NewDescription123");
        assertEquals("", testExercise.getExerciseDescription());
        assertEquals("NewDescription123", testExercise2.getExerciseDescription());
    }

    @Test
    void testSetExerciseReps() {
        testExercise.setExerciseNumOfReps(0);
        testExercise2.setExerciseNumOfReps(-1);
        testExercise3.setExerciseNumOfReps(5);
        assertEquals(1, testExercise.getExerciseNumOfReps());
        assertEquals(1, testExercise2.getExerciseNumOfReps());
        assertEquals(5, testExercise3.getExerciseNumOfReps());
    }

    @Test
    void testSetExerciseSets() {
        testExercise.setExerciseNumOfSets(0);
        testExercise2.setExerciseNumOfSets(-1);
        testExercise3.setExerciseNumOfSets(5);
        assertEquals(1, testExercise.getExerciseNumOfSets());
        assertEquals(1, testExercise2.getExerciseNumOfSets());
        assertEquals(5, testExercise3.getExerciseNumOfSets());
    }


    @Test
    void testSetExerciseRestTime() {
        testExercise.setExerciseRestTime(3);
        testExercise2.setExerciseRestTime(-1);
        testExercise3.setExerciseRestTime(12);
        assertEquals(3, testExercise.getExerciseRestTime());
        assertEquals(0, testExercise2.getExerciseRestTime());
        assertEquals(12, testExercise3.getExerciseRestTime());
    }

    @Test
    void testSetExerciseRating() {
        testExercise.setExerciseRating(3);
        testExercise2.setExerciseRating(-6);
        testExercise3.setExerciseRating(12);
        assertEquals(3, testExercise.getExerciseRating());
        assertEquals(-6, testExercise2.getExerciseRating());
        assertEquals(12, testExercise3.getExerciseRating());
    }

    @Test
    void testExerciseToStringObject() {
        String[] exerciseStringObject = testExercise.exerciseToStringObject();

        assertEquals(exerciseStringObject[0], testExercise.getExerciseName());
        assertEquals(exerciseStringObject[1], testExercise.getExerciseDescription());
        assertEquals(Integer.parseInt(exerciseStringObject[2]), testExercise.getExerciseNumOfReps());
        assertEquals(Integer.parseInt(exerciseStringObject[3]), testExercise.getExerciseNumOfSets());
        assertEquals(Integer.parseInt(exerciseStringObject[4]), testExercise.getExerciseRestTime());
        assertEquals(exerciseStringObject[5], testExercise.returnDefinedRating());
    }

}