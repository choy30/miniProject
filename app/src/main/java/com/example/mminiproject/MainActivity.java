package com.example.mminiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback  {
    private DatabaseReference databaseReference;
    private Button btn_load, btn_add;
    private EditText etname, etlat, etlng;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = (EditText) findViewById(R.id.txt_name);
        etlat = (EditText) findViewById(R.id.txt_lat);
        etlng = (EditText) findViewById(R.id.txt_lng);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePin();
            }
        });

        btn_load = findViewById(R.id.btn_load);
        btn_load.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });

    }


    private void savePin() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", etname.getText().toString().trim());
        map.put("lat", Double.parseDouble(etlat.getText().toString().trim()));
        map.put("lng", Double.parseDouble(etlng.getText().toString().trim()));
        FirebaseDatabase.getInstance().getReference().child("locations").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        etname.setText("");
                        etlat.setText("");
                        etlng.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}