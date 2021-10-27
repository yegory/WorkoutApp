package persistence;

import model.Exercise;
import model.Routine;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Modelled off of JsonSerializationDemo
    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonTest {
    protected void checkExercise(String exerciseName, String exerciseDescription, int exerciseNumOfReps,
                                 int exerciseNumOfSets, int exerciseRestTime, int exerciseRating, Exercise exercise) {
        assertEquals(exerciseName, exercise.getExerciseName());
        assertEquals(exerciseDescription, exercise.getExerciseDescription());
        assertEquals(exerciseNumOfReps, exercise.getExerciseNumOfReps());
        assertEquals(exerciseNumOfSets, exercise.getExerciseNumOfSets());
        assertEquals(exerciseRestTime, exercise.getExerciseRestTime());
        assertEquals(exerciseRating, exercise.getExerciseRating());
    }

    protected void checkRoutine(String routineName, String routineDescription, List<Exercise> includedExercises,
                                int routineRating, Routine routine) {
        assertEquals(routineName, routine.getRoutineName());
        assertEquals(routineDescription, routine.getRoutineDescription());
        for (int i = 0; i < includedExercises.size(); i++) {
            Exercise ex = includedExercises.get(i);
            checkExercise(ex.getExerciseName(), ex.getExerciseDescription(), ex.getExerciseNumOfReps(),
                    ex.getExerciseNumOfSets(), ex.getExerciseRestTime(), ex.getExerciseRating(), ex);
        }
        assertEquals(routineRating, routine.getRoutineRating());
    }
}