package com.example.wvand.restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MenuItemActivity extends AppCompatActivity {

    ImageView food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Get intent so we have data to set views with
        Intent MenuItem = getIntent();
        MenuItem menuItem = (MenuItem) MenuItem.getSerializableExtra("Itemmenu");

        // Getting access to views to set them
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);

        // Retrieving data out of menu item
        String title = menuItem.getName();
        String described = menuItem.getDescription();
        long money = menuItem.getPrice();
        String url = menuItem.getImageUrl();

        // Setting texts
        name.setText(title);
        description.setText(described);
        price.setText("€" + String.valueOf(money));

        RequestQueue queue = Volley.newRequestQueue(MenuItemActivity.this);

        // Finding correct Image View
        food = findViewById(R.id.food);

        // Requests for each image in menulist
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                food.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MenuItemActivity.this, "Couldn't load image!", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        queue.add(imageRequest);

    }
}
