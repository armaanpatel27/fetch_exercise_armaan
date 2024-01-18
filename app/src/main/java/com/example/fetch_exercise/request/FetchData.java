package com.example.fetch_exercise.request;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.example.fetch_exercise.model.DataModel;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

;

/**
 * Provides functionality to parse JSON file
 */
public class FetchData {

    /**
     * Converts a string representation of a URL to a URL object.
     *
     * @param url The string representation of the URL.
     * @return A URL object representing the specified URL, or null if the input is not a valid URL.
     **/
    public static URL getURL(String url) {
        try {
            //creates URL object and returns it
            URL fetchURL = new URL(url);
            return fetchURL;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parses JSON file while ignoring objects with null or empty names
     *
     * @param url URL to JSON file
     * @return  An array of DataModel objects, each representing a JSON object
     */
    public static ArrayList<DataModel> parseJson(URL url) {
        //data object to be returned
        ArrayList<DataModel> data = new ArrayList<DataModel>();
        try {
            //opens URL stream and JsonReader
            InputStreamReader stream = new InputStreamReader(url.openStream());
            JsonReader reader = new JsonReader(stream);

            //indicates start of parsing array
            reader.beginArray();
            //iterate until no more objects to read
            while(reader.hasNext()) {
                //indicate start of a single object
                reader.beginObject();
                //containers for object fields
                String listId = "";
                String id = "";
                String name = "";
                //iterate through key,value pairs of object
                while(reader.hasNext()) {
                    //captures name of the current key
                    String key = reader.nextName();
                    //if value is Null --> endObject and don't add it to array
                    if(reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        name = null;
                        continue;
                    }
                    //captures value of current key-value pair
                    String value = reader.nextString();

                    //updates value of corresponding fields
                    if(key.equals("listId")) {
                        listId = value;
                    } else if(key.equals("id") ) {
                        id = value;
                    } else if (key.equals("name")) {
                        name = value;
                    }

                }
                //indicates end of this current object
                reader.endObject();
                //if the name is not blank or not null --> add it to output array
                if(name != null && !name.isEmpty()) {
                    data.add(new DataModel(id, listId, name));
                }
            }
            //indicates end of JSON array --> end of JSON parsing
            reader.endArray();
            reader.close();
            }
        catch (Exception e) {
            Log.i("Error", e.toString());
        }
        //return value
        return data;
    }

}
