package com.example.wvand.restaurant;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Queue;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    // Constructor that accepts a Context and stores it in context instance
    public CategoriesRequest(Context context) {

        this.context = context;
    }

    Context context;
    ArrayList categories = new ArrayList();
    Callback activity;
    String error;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // Get error message and put it in string initialised above
        this.error = error.getMessage();

        // Passing description of error back to the activity
        activity.gotCategoriesError(this.error);
    }

    @Override
    public void onResponse(JSONObject response) {

        System.out.println("Third point");


        // Get categories data from server and put them in JSONArray
        JSONArray array = new JSONArray();
        try {
            array = response.getJSONArray("categories");
        }

        catch (JSONException e) {
            e.printStackTrace();
        }

        // Loop through data, getting categories
        for(int i = 0; i < array.length(); i++) {
            try {


                categories.add(array.getString(i));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
         // Pass datas to CategoriesActivity
         activity.gotCategories(categories);

        }
    }

    // Retrieve categories from the API
    public void getCategories(Callback activity){
        System.out.println("Second point");

        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories",
                null, this, this);
        queue.add(jsonObjectRequest);
    }
}
