package model;

import java.util.ArrayList;
import java.util.List;

public class Routines {
    private List<Routine> myRoutines;

    // MODIFIES: this
    // EFFECTS: Constructs an empty array list called myRoutines
    public Routines() {
        myRoutines = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the routine at the end of the myRoutines List
    public void addRoutine(Routine routine) {
        myRoutines.add(routine);
    }

    public int size() {
        return myRoutines.size();
    }

    public Routine getRoutine(int index) {
        return myRoutines.get(index);
    }

    // MODIFIES: this
    // REQUIRES: index is not out of range for myRoutines
    // EFFECTS: removes the Routine at position equal to index.
    public void removeRoutine(int index) {
        myRoutines.remove(index);
    }
}