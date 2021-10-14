package model;

import java.util.ArrayList;
import java.util.List;

public class Routines {
    private List<Routine> myRoutines;

    public Routines() {
        myRoutines = new ArrayList<>();
    }

    public void addRoutine(Routine routine) {
        myRoutines.add(routine);
    }

    public int size() {
        return myRoutines.size();
    }

    public Routine getRoutine(int index) {
        return myRoutines.get(index);
    }

    public void removeRoutine(int index) {
        myRoutines.remove(index);
    }
}