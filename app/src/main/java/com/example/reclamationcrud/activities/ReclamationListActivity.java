package com.example.reclamationcrud.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.reclamationcrud.R;
import com.example.reclamationcrud.adapters.ReclamationAdapter;
import com.example.reclamationcrud.database.AppDatabase;
import com.example.reclamationcrud.entities.Reclamation;

import java.util.List;

public class ReclamationListActivity extends AppCompatActivity   {
    List<Reclamation> reclamations;
    ReclamationAdapter reclamationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvReclamations = findViewById(R.id.rv_rec);


        reclamations = AppDatabase.getDatabase(getApplicationContext()).getRecDao().getAllReclamations();

        reclamationAdapter = new ReclamationAdapter(this, reclamations);
        rvReclamations.setAdapter(reclamationAdapter);

        rvReclamations.setLayoutManager(new LinearLayoutManager(this));





    }


    @Override
    protected void onResume() {
        super.onResume();
        reclamationAdapter.setItem(AppDatabase.getDatabase(getApplicationContext()).getRecDao().getAllReclamations());
    }
}
