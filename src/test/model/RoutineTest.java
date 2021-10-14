package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RoutineTest {
    private Routine testRoutine;
    private Exercise exercise1;
    private Exercise exercise2;

    @BeforeEach
    void runBefore() {
        exercise1 = new Exercise("exercise1", "Description1",
                1, 2, 3, -1);
        exercise2 = new Exercise("exercise2", "Description2",
                4, 5, 6, 5);
        testRoutine = new Routine("Routine name", "Routine description",
                Arrays.asList(exercise1, exercise2), 1);
    }

    @Test
    void testConstructor() {
        assertEquals("Routine name", testRoutine.getRoutineName());
        assertEquals("Routine description", testRoutine.getRoutineDescription());
        assertEquals(Arrays.asList(exercise1, exercise2), testRoutine.getIncludedExercises());
        assertEquals(1, testRoutine.getRoutineRating());
    }

    @Test
    void testFormatTotalTimeToComplete() {
        assertEquals("0 min, 0 sec", testRoutine.formatTotalTimeToComplete(0));
        assertEquals("0 min, 1 sec", testRoutine.formatTotalTimeToComplete(1));
        assertEquals("1 min, 0 sec", testRoutine.formatTotalTimeToComplete(60));
        assertEquals("1 min, 1 sec", testRoutine.formatTotalTimeToComplete(61));
        assertEquals("2 min, 5 sec", testRoutine.formatTotalTimeToComplete(125));
    }
}
