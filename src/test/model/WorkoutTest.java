package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout workout;

    private Exercise exercise1;
    private Exercise exercise2;
    private Routine routine1;
    private Routine routine2;

    @BeforeEach
    void setUp() {
        exercise1 = new Exercise("ExerciseName1", "Description", 1, 2, 3, 4);
        exercise2 = new Exercise("ExerciseName2", "Description", 11, 22, 33, 44);
        routine1 = new Routine("RoutineName1", "Description", Arrays.asList(exercise1, exercise2), 5);
        routine2 = new Routine("RoutineName2", "Description", Arrays.asList(exercise2, exercise1, exercise2), 4);

        workout = new Workout("name");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        workout.addRoutine(routine1);
        workout.addRoutine(routine2);
    }

    @Test
    void testRemoveExercise() {
        assertEquals(2, workout.exercisesSize());
        assertEquals(exercise1, workout.getExercise(0));
        workout.removeExercise(0);
        assertEquals(1, workout.exercisesSize());
        assertEquals(exercise2, workout.getExercise(0));
    }

    @Test
    void testRemoveRoutine() {
        assertEquals(2, workout.routinesSize());
        assertEquals(routine1, workout.getRoutine(0));
        workout.removeRoutine(0);
        assertEquals(1, workout.routinesSize());
        assertEquals(routine2, workout.getRoutine(0));
    }

    @Test
    void testFindExercise() {
        assertTrue(workout.findExercise("ExerciseName1"));
        assertTrue(workout.findExercise("ExerciseName2"));
        assertFalse(workout.findExercise("ExerciseName3"));
    }
}