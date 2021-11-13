package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static model.Routine.TIME_FOR_1REP;

public class RoutineTest {
    private Routine testRoutine;
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;

    @BeforeEach
    void runBefore() {
        exercise1 = new Exercise("exercise1", "Description1", 1, 2, 3, -1);
        exercise2 = new Exercise("exercise2", "Description2", 4, 5, 6, 5);

        List<Exercise> includedExercise = new LinkedList<Exercise>(Arrays.asList(exercise1,exercise2));

        exercise3 = new Exercise("exercise2", "Description3", 5, 5, 5, 1);
        testRoutine = new Routine("Routine name", "Routine description", includedExercise, 1);
    }

    @Test
    void testConstructor() {
        int totalExerciseTime = 0;
        for (Exercise exercise : testRoutine.getIncludedExercises()) {
            totalExerciseTime += exercise.getExerciseRestTime() * exercise.getExerciseNumOfSets()
                    + exercise.getExerciseNumOfSets() * exercise.getExerciseNumOfReps() * TIME_FOR_1REP;
        }
        assertEquals("Routine name", testRoutine.getRoutineName());
        assertEquals("Routine description", testRoutine.getRoutineDescription());
        assertEquals(Arrays.asList(exercise1, exercise2), testRoutine.getIncludedExercises());
        assertEquals(1, testRoutine.getRoutineRating());
        assertEquals(totalExerciseTime, testRoutine.getTotalTimeToComplete());
    }

    @Test
    void testSetRoutineName() {
        testRoutine.setRoutineName("Modified routine name");
        assertEquals("Modified routine name", testRoutine.getRoutineName());
    }

    @Test
    void testSetRoutineDescription() {
        testRoutine.setRoutineDescription("Modified routine description");
        assertEquals("Modified routine description", testRoutine.getRoutineDescription());
    }

    @Test
    void testSetIncludedExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise1);
        exerciseList.add(exercise2);
        exerciseList.add(exercise3);

        assertEquals(2, testRoutine.getIncludedExercises().size());
        testRoutine.setIncludedExercises(exerciseList);
        assertEquals(3, testRoutine.getIncludedExercises().size());
    }

    @Test
    void testSetTotalTimeToComplete() {
        testRoutine.setTotalTimeToComplete(5);
        assertEquals(5, testRoutine.getTotalTimeToComplete());
    }


    @Test
    void testReturnDefinedRating() {
        testRoutine.setRoutineRating(-1);
        assertEquals("Unrated", testRoutine.returnDefinedRating());
        testRoutine.setRoutineRating(0);
        assertEquals("F - Atrocious", testRoutine.returnDefinedRating());
        testRoutine.setRoutineRating(1);
        assertEquals("E - Terrible", testRoutine.returnDefinedRating());
        testRoutine.setRoutineRating(2);
        assertEquals("D - Ok-ish", testRoutine.returnDefinedRating());
        testRoutine.setRoutineRating(3);
        assertEquals("C - Decent", testRoutine.returnDefinedRating());
        testRoutine.setRoutineRating(4);
        assertEquals("B - Great", testRoutine.returnDefinedRating());
        testRoutine.setRoutineRating(5);
        assertEquals("A - Amazing", testRoutine.returnDefinedRating());
    }

    @Test
    void testFormatTotalTimeToComplete() {
        assertEquals("0 min, 0 sec", testRoutine.formatTotalTimeToComplete(0));
        assertEquals("0 min, 1 sec", testRoutine.formatTotalTimeToComplete(1));
        assertEquals("1 min, 0 sec", testRoutine.formatTotalTimeToComplete(60));
        assertEquals("1 min, 1 sec", testRoutine.formatTotalTimeToComplete(61));
        assertEquals("2 min, 5 sec", testRoutine.formatTotalTimeToComplete(125));
    }

    @Test
    void testUpdateTotalTimeToComplete() {
        testRoutine.setTotalTimeToComplete(999999);
        testRoutine.updateTotalTimeToComplete();
        int timeBeforeRemoving = testRoutine.getTotalTimeToComplete();
        assertNotEquals(999999, timeBeforeRemoving);

        testRoutine.getIncludedExercises().remove(0);

        // since we have calculated the time again for the included exercises, time will be same as before
        int timeAfterRemoving = testRoutine.getTotalTimeToComplete();
        assertEquals(timeBeforeRemoving, timeAfterRemoving);

        testRoutine.setTotalTimeToComplete(5000);

        // now time will be updated
        testRoutine.updateTotalTimeToComplete();
        assertNotEquals(5000, testRoutine.getTotalTimeToComplete());
        assertNotEquals(timeAfterRemoving, testRoutine.getTotalTimeToComplete());
    }
}