package com.example.fetch_exercise.UtilityFunctions;

import com.example.fetch_exercise.model.DataModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Provides functionality to sort an array of DataModel objects
 */
public class Sort {
    /**
     * Sorts array first by listID, and then by name
     *
     * @param data array containing DataModel objects from JSON file
     * @return void, modifies array in place
     */
    public static void sortData(ArrayList<DataModel> data) {
        //a and b are DataModel objects
        Collections.sort(data, (a,b) -> {
            //first compare by ListID
            int compare = a.listId.compareTo(b.listId);
            //if ListID is the same, then compare by name
            if(compare == 0) {
                //compare by integer comparison not string
                //use ID b/c id is the same one used in the name
                return Integer.compare(Integer.parseInt(a.id),Integer.parseInt(b.id));
            }
            return compare;
        });
    }
}

