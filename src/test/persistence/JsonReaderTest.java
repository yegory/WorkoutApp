package persistence;

import model.Exercise;
import model.Workout;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
    Modelled off of JsonSerializationDemo
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./lib/NoFileInPath.json");
        try {
            Workout workout = new Workout("profile");
            workout = reader.read(workout);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkout.json");
        try {
            Workout workout = new Workout("profile");
            workout = reader.read(workout);
            assertEquals("testReaderEmptyWorkout", workout.getName());
            assertEquals(0, workout.exercisesSize());
            assertEquals(0, workout.routinesSize());
        } catch (IOException e) {
            fail("Failed to read path that has no problems");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkout.json");
        try {
            Workout workout = new Workout("profile");
            workout = reader.read(workout);
            assertEquals("testReaderGeneralWorkout", workout.getName());
            List<Exercise> exercises = workout.getExercises();
            assertEquals(8, exercises.size());
            for (Exercise ex : exercises) {
                checkExercise(ex.getExerciseName(), ex.getExerciseDescription(), ex.getExerciseNumOfReps(),
                        ex.getExerciseNumOfSets(), ex.getExerciseRestTime(), ex.getExerciseRating(), ex);
            }

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}