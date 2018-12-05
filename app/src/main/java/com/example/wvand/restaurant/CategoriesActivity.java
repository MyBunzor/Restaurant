package com.example.wvand.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ArrayList categories = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        ListView ListView = findViewById(R.id.ListView);
        ListViewListener ListViewListener = new ListViewListener();
        ListView.setOnItemClickListener(new ListViewListener());
        System.out.println("First point");
        }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        this.categories = categories;

        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                categories);

        ListView ListView = findViewById(R.id.ListView);
        ListView.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private class ListViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get category that user clicked on
            String clickedCategory = (String) parent.getItemAtPosition(position);

            // Open MenuActivity
            Intent MenuItem = new Intent(CategoriesActivity.this, MenuActivity.class);
            MenuItem.putExtra("category", clickedCategory);
            startActivity(MenuItem);
        }

    }


}
