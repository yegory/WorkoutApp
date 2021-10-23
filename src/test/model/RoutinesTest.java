//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RoutinesTest {
//    private Routine testRoutine1;
//    private Routine testRoutine2;
//    private Routines testRoutines;
//
//    @BeforeEach
//    void runBefore() {
//
//        Exercise exercise1 = new Exercise("exercise1", "Description1",
//                1, 2, 3, -1);
//        Exercise exercise2 = new Exercise("exercise2", "Description2",
//                4, 5, 6, 5);
//        Exercise exercise3 = new Exercise("exercise3", "Description3",
//                9, 10, 11, 4);
//
//        testRoutine1 = new Routine("Routine1", "Routine description 1",
//                Arrays.asList(exercise1, exercise2), 1);
//        testRoutine2 = new Routine("Routine2", "Routine description 2",
//                Arrays.asList(exercise2, exercise3, exercise1), 5);
//
//        testRoutines = new Routines();
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals(0, testRoutines.size());
//    }
//
//    @Test
//    void testAddRoutine() {
//        assertEquals(0, testRoutines.size());
//        testRoutines.addRoutine(testRoutine1);
//        assertEquals(1, testRoutines.size());
//        testRoutines.addRoutine(testRoutine2);
//        assertEquals(2, testRoutines.size());
//    }
//
//    @Test
//    void testGetRoutine() {
//        assertEquals(0, testRoutines.size());
//        testRoutines.addRoutine(testRoutine1);
//        assertEquals(testRoutine1, testRoutines.getRoutine(0));
//
//        assertEquals(1, testRoutines.size());
//        testRoutines.addRoutine(testRoutine2);
//        assertEquals(testRoutine2, testRoutines.getRoutine(1));
//    }
//
//    @Test
//    void testRemoveRoutine() {
//        assertEquals(0, testRoutines.size());
//        testRoutines.addRoutine(testRoutine1);
//        assertEquals(1, testRoutines.size());
//        testRoutines.addRoutine(testRoutine2);
//        assertEquals(2, testRoutines.size());
//
//        testRoutines.removeRoutine(0);
//        assertEquals(1, testRoutines.size());
//        assertEquals(testRoutine2, testRoutines.getRoutine(0));
//    }
//}
