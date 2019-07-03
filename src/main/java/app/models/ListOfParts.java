package app.models;

import app.entities.Part;

import java.util.*;

/**
 * parts list
 */
public class ListOfParts {
    private static ListOfParts ourInstance = new ListOfParts();
    private static List<Part> parts;
    private static Map<String, Boolean> sorted;
    static {
        sorted.put("partnumber", true);
        sorted.put("partname", true);
        sorted.put("vendor", true);
        sorted.put("qty", true);
        sorted.put("shipped", true);
        sorted.put("received", true);
    }

    public static ListOfParts getInstance() {
        return ourInstance;
    }

    private ListOfParts() {
        parts = new ArrayList<>();
        sorted = new HashMap<>();
    }

    public Map<String, Boolean> getSortedMap() {
        return sorted;
    }

    public List<Part> getParts(Comparator<Part> comparator) {
        parts.sort(comparator);
        return parts;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
