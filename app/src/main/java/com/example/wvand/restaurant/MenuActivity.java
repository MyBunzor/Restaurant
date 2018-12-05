package com.example.wvand.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();

    String url = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        System.out.println("In MenuActivity");

        // Retrieve intent and the category user clicked on
        Intent getCategory = getIntent();
        String category = (String) getCategory.getSerializableExtra("category");

        // Get the data from website, specifically for category
        MenuRequest request = new MenuRequest(this);
        request.getMenuItems(this, category);

        ListView ListView = findViewById(R.id.ListView1);
        ListViewListener ListViewListener = new ListViewListener();
        ListView.setOnItemClickListener(ListViewListener);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> MenuItems) {

        this.MenuItems = MenuItems;

        ListView ListView = findViewById(R.id.ListView1);

        MenuAdapter adapter = new MenuAdapter(this, R.layout.menuitem_rows, MenuItems);
        ListView.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {

    }

    // Listeners so we can track down the food that's being clicked on
    private class ListViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get category that user clicked on
            MenuItem clickedItem = (MenuItem) parent.getItemAtPosition(position);

            // Open MenuActivity
            Intent MenuItem = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem.putExtra("Itemmenu", clickedItem);
            startActivity(MenuItem);
        }

    }
}

