package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

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

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray routinesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Routine routine : myRoutines) {
            jsonArray.put(routine.toJson());
        }

        return jsonArray;
    }
}