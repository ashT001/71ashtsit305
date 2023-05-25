package com.example.a71preall;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = findViewById(R.id.NewItemGotoButton);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,NewAdvertActivity.class);
                        startActivity(i);
                    }
                }
        );

        b2 = findViewById(R.id.ViewItemButton);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, ListItemActivity.class);
                        startActivity(i);
                    }
                }
        );

        b3 = findViewById(R.id.mapTestButton);
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(i);
                    }
                }
        );

    }
}