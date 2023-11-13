package com.example.reclamationcrud.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reclamationcrud.R;
import com.example.reclamationcrud.database.AppDatabase;
import com.example.reclamationcrud.entities.Reclamation;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UpdateReclamationActivity extends AppCompatActivity {

    String numeroReclamation;
    String desc ;
    String dateSoumission;
    Boolean etat;
    int id ;
    String currentnumeroReclamation ="";
    String currentdesc ="";
    Date currentdateSoumission =null ;
    Boolean currentetat =false;

    TextInputEditText etDesc;
    Spinner sp_etat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_update_rec);
        if (getIntent() != null) {
             numeroReclamation = getIntent().getStringExtra("nom");
             desc = getIntent().getStringExtra("desc");
             dateSoumission = getIntent().getStringExtra("date");
             etat = getIntent().getBooleanExtra("etat",false);
             id = getIntent().getIntExtra("id",0);
        }



         sp_etat = findViewById(R.id.etat);
         etDesc = findViewById(R.id.et_descrip);


        etDesc.setText(desc);
        setupSpinner();

        Button update = findViewById(R.id.btn_ajouter);
       update.setOnClickListener(v -> updateReclamation(new Reclamation(id,numeroReclamation,desc,etat,null)));

        }



    void updateReclamation(Reclamation reclamation) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            // Parsing the string to a Date object
            currentdateSoumission = sdf.parse(dateSoumission);

            // Display the Date object
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reclamation.setDateSoumission(currentdateSoumission);
        AppDatabase.getDatabase(getApplicationContext()).getRecDao().upsertReclamation(reclamation);
        finish();
    }


    private void setupSpinner() {
        String[] etats = getResources().getStringArray(R.array.etat);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, etats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_etat.setAdapter(adapter);
        if(etat){
            sp_etat.setSelection(1, false);

        }
        else{
            sp_etat.setSelection(0, false);

        }

        // Handle item selection in the spinner
        sp_etat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String etatSelected = parent.getItemAtPosition(position).toString();
             if(etatSelected.toUpperCase().contains("EN ATTENTE")){
                 etat=false;
             }else{
                 etat=true;
             }


            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }


}