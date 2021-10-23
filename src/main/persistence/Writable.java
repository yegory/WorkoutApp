package persistence;

import org.json.JSONObject;

// borrowed from JsonSerializationDemo (link in README)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}