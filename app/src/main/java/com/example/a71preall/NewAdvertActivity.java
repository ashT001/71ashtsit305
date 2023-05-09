package com.example.a71preall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class NewAdvertActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    private EditText ItemNameEditText, ItemDescriptionEditText, ItemDateEditText, ItemLocationEditText;
    Switch IsLostSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);


        b1 = findViewById(R.id.NewItemAddButton);
        ItemNameEditText = findViewById(R.id.ItemNameEditText);
        ItemDescriptionEditText = findViewById(R.id.ItemDescriptionEditText);
        ItemDateEditText = findViewById(R.id.ItemDateEditText);
        ItemLocationEditText = findViewById(R.id.ItemLocationEditText);
        IsLostSwitch = findViewById(R.id.IsLostSwitch);
        b2 = findViewById(R.id.backbutton);

        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Item newitem;
                        try{
                            newitem = new Item(-1, ItemNameEditText.getText().toString(), ItemDescriptionEditText.getText().toString(), ItemDateEditText.getText().toString(), ItemLocationEditText.getText().toString(), IsLostSwitch.isChecked());
/*                            Toast.makeText(NewAdvertActivity.this, newitem.toString(), Toast.LENGTH_SHORT).show();*/
                        }


                        catch (Exception e) {
                            Toast.makeText(NewAdvertActivity.this, "Error!", Toast.LENGTH_SHORT);
                            newitem = new Item(-1, "ERROR", "ERROR", "ERROR", "ERROR", false);
                        }


                        DataBaseHelper dataBaseHelper = new DataBaseHelper(NewAdvertActivity.this);

                        boolean success = dataBaseHelper.addOne(newitem);

                        Toast.makeText(NewAdvertActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(NewAdvertActivity.this ,MainActivity.class);
                        startActivity(i);
                    }
                }
        );



    }
}