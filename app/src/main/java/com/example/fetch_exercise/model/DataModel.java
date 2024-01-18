package com.example.fetch_exercise.model;

/**
 * Represents a container for each JSON object inside the array
 */
public class DataModel {
    public String id;
    public String listId;
    public String name;
    /**
     * Constructor for DataModel class
     *
     * @param id     id from JSON file
     * @param listId listId from JSON file
     * @param name   name from JSON file
     */
    public DataModel(String id, String listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    /**
     * String representation of class
     *
     *
     * @return String version of class
     */
    public String toString() {
        return "listId: " + listId + ", Id: " + id + ", Name: " + name;
    }
}
