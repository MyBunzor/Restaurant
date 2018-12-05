package com.example.wvand.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;
    String error;

    @Override
    public void onErrorResponse(VolleyError error) {

        // Get error message and put it in string initialised above
        this.error = error.getMessage();

        // Passing description of error back to the activity
        activity.gotMenuItemsError(this.error);
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Getting here");

        // List of menuItems that we'll fill here
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

        // Getting JSONdata
        JSONArray array = new JSONArray();
        try {
            array = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Loop through items
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject menuitem = array.getJSONObject(i);
                String name = menuitem.getString("name");
                String description = menuitem.getString("description");
                String imageUrl = menuitem.getString("image_url");
                long price = menuitem.getLong("price");
                String category = menuitem.getString("category");

                MenuItem menuItem = new MenuItem(name, description, imageUrl, price, category);
                menuItems.add(menuItem);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        activity.gotMenuItems(menuItems);
        }

    }
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> MenuItems);
        void gotMenuItemsError(String message);
    }

    public MenuRequest(Context context){
        this.context = context;
    }

    public void getMenuItems(Callback activity, String retrievedItem){
        System.out.println("Bamma");

        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);
        switch (retrievedItem) {
            case "appetizers":
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=appetizers",
                        null, this, this);
                queue.add(jsonObjectRequest);
                System.out.println("Got here");
                break;

            case "entrees":
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest("https://resto.mprog.nl/menu?category=entrees",
                        null, this, this);
                queue.add(jsonObjectRequest1);
                System.out.println("Entrays");
                break;
        }
    }
}
