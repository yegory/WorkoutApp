package persistence;

import org.json.JSONObject;

/**
 *      Credit to JsonSerializationDemo - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 *      for almost all the code related to reading/writing to json
 *
 *      Represents a reader that reads workout from JSON data stored in file
 *      Defines behaviours that musts be supported for all classes with important data.
 */
public interface Writable {
    /*
        EFFECTS: returns this as JSON object
     */
    JSONObject toJson();
}