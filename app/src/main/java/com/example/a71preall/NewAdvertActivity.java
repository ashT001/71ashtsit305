package com.example.a71preall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class NewAdvertActivity extends AppCompatActivity {

    public void getDeviceLocation(View view){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(ItemLocationEditText.getText().toString(), 1);

            if (addressList != null){
                double doubleLat = addressList.get(0).getLatitude();
                double doubleLong = addressList.get(0).getLongitude();

                ItemLocationEditText.setText(String.valueOf(doubleLat) + "," + String.valueOf(doubleLong));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Task location = fusedLocationClient.getLastLocation();
        location.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    Log.d("TAG", "onComplete: found loc");
                    Location CurrentLocation = (Location) task.getResult();

                    String s = String.valueOf(CurrentLocation.getLatitude() + CurrentLocation.getLongitude());

                    ItemLocationEditText.setText(s);
                }
                else{
                    Log.d("TAG", "onComplete: cannot find loc");
                }
            }
        });*/
    }

    FusedLocationProviderClient fusedLocationClient;
    Location location;
    Button b1;
    Button b2;
    Button b3;
    private EditText ItemNameEditText, ItemDescriptionEditText, ItemDateEditText, ItemLocationEditText;
    Switch IsLostSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        b1 = findViewById(R.id.NewItemAddButton);
        ItemNameEditText = findViewById(R.id.ItemNameEditText);
        ItemDescriptionEditText = findViewById(R.id.ItemDescriptionEditText);
        ItemDateEditText = findViewById(R.id.ItemDateEditText);
        ItemLocationEditText = findViewById(R.id.ItemLocationEditText);
        IsLostSwitch = findViewById(R.id.IsLostSwitch);
        b2 = findViewById(R.id.backbutton);
        b3 = findViewById(R.id.getCurrentLocButton);

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

        b3.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(View v) {
                        Task task = fusedLocationClient.getLastLocation();

                        getDeviceLocation(v);
                        //ItemLocationEditText.setText("-38.155710,144.345960");
                    }
                }
        );



    }
}