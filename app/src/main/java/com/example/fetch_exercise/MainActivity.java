package com.example.fetch_exercise;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fetch_exercise.UtilityFunctions.Sort;
import com.example.fetch_exercise.model.DataModel;
import com.example.fetch_exercise.request.FetchData;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //convert String URL to type URL
        URL url = FetchData.getURL("https://fetch-hiring.s3.amazonaws.com/hiring.json");
        //call async function to read JSON file in background
        CompletableFuture.runAsync(() -> {
            //store DataModel objects in data containing JSON data
            ArrayList<DataModel> data = FetchData.parseJson(url);
            //sorts data according to instructions
            Sort.sortData(data);
            //print each item to Log with filter Message
            for(DataModel model: data) {
                Log.i("Message", model.listId + " " + model.id + " " + model.name);
            }
            //now go back to mainThread b/c this is a UI operation
            runOnUiThread(() -> {
                setContentView(R.layout.activity_main);
                //find ListView by id
                ListView view = findViewById(R.id.list);
                //create an Adapter to fill ListView with each item in data
                ArrayAdapter<DataModel> adapter = new ArrayAdapter<DataModel>(
                        this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        data);
                //sets the adapter for Listview View
                view.setAdapter(adapter);
            });

        });
    }
}

