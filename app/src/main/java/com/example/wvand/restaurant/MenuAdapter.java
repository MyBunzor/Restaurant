package com.example.wvand.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    Context context;

        // Declaring friends as an arraylist, so we can fill it with objects in constructor below
        ArrayList<MenuItem> menuItems;
        public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
            super(context, resource, objects);

            this.context = context;

            this.menuItems = objects;
        }

        // Fills griditem for each friend
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.menuitem_rows, parent, false);
            }

            // Get access to layout's views
            final ImageView menuImage = convertView.findViewById(R.id.imageView);
            TextView title = convertView.findViewById(R.id.itemName);
            TextView price = convertView.findViewById(R.id.itemPrice);

            // Getting data from friends list
            String name = menuItems.get(position).getName();
            long pricy = menuItems.get(position).getPrice();
            String url = menuItems.get(position).getImageUrl();

            RequestQueue queue = Volley.newRequestQueue(context);

            // ImageRequest with url and listeners, added to queue after
            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    menuImage.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Couldn't load image!", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });
            queue.add(imageRequest);



            // Use data to fill the layout's views
            //
            title.setText(name);
            price.setText(String.valueOf(pricy));

            return convertView;
        }
}

