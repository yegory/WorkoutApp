package persistence;

import model.Exercise;
import model.Routine;
import model.Workout;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
    Modelled off of JsonSerializationDemo
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Workout workout = new Workout("profile");
            JsonWriter writer = new JsonWriter("./data/myError\0:StackTrace.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Workout workout = new Workout("Workout Writer Test");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkout.json");
            writer.open();
            writer.write(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkout.json");
            workout = reader.read(workout);
            assertEquals("Workout Writer Test", workout.getName());
            assertEquals(0, workout.exercisesSize());
            assertEquals(0, workout.routinesSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        Exercise exercise1 = new Exercise("testExercise1", "Desc1", 1, 2, 3, 4);
        Exercise exercise2 = new Exercise("testExercise2", "Desc2", 11, 22, 33, 44);

        try {
            Workout workout = new Workout("Workout Writer Test 2");
            workout.addExercise(exercise1);
            workout.addExercise(exercise2);
            workout.addRoutine(new Routine("testRoutine1", "Desc1", Arrays.asList(exercise1, exercise2), 4));
            workout.addRoutine(new Routine("testRoutine2", "Desc2", Arrays.asList(exercise2, exercise1, exercise2), 5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkout.json");
            writer.open();
            writer.write(workout);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkout.json");
            workout = reader.read(new Workout("profile"));
            assertEquals("Workout Writer Test 2", workout.getName());
            List<Exercise> exercises = workout.getExercises();
            List<Routine> routines = workout.getRoutines();

            assertEquals(2, exercises.size());
            assertEquals(2, routines.size());
            checkExercise("testExercise1", "Desc1", 1, 2, 3, 4, exercises.get(0));
            checkExercise("testExercise2", "Desc2", 11, 22, 33, 44, exercises.get(1));
            checkRoutine("testRoutine1", "Desc1", Arrays.asList(exercise1, exercise2), 4, routines.get(0));
            checkRoutine("testRoutine2", "Desc2", Arrays.asList(exercise2, exercise1, exercise2), 5, routines.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}