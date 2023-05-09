package com.example.a71preall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    ListView listviewitem;
    ArrayAdapter itemArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        listviewitem = findViewById(R.id.listviewitem);
        b1 = findViewById(R.id.backbutton);
        b2 = findViewById(R.id.loaddatabasebutton);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ListItemActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                }
        );

        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(ListItemActivity.this);
                        List<Item> itemList = dataBaseHelper.getAllItems();

                        itemArrayAdapter = new ArrayAdapter<Item>(ListItemActivity.this, android.R.layout.simple_list_item_1, itemList);
                        listviewitem.setAdapter(itemArrayAdapter);

                        //Toast.makeText(ListItemActivity.this, itemList.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        );

        listviewitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item clickedItem = (Item) adapterView.getItemAtPosition(i);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(listviewitem.getContext());
                dataBaseHelper.deleteOne(clickedItem);
                Toast.makeText(ListItemActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}