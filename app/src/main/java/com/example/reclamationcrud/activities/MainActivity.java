package com.example.reclamationcrud.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reclamationcrud.R;
import com.example.reclamationcrud.adapters.ReclamationAdapter;
import com.example.reclamationcrud.entities.Reclamation;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btnNewREc).setOnClickListener(v -> {
            Intent i =new Intent(this, NewReclamationActivity.class);
            startActivity(i);
        });

        findViewById(R.id.btnDisplay).setOnClickListener(v -> {
            Intent i =new Intent(this, ReclamationListActivity.class);
            startActivity(i);
        });
    }
}