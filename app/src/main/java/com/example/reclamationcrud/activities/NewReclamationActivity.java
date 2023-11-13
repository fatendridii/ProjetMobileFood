package com.example.reclamationcrud.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reclamationcrud.R;
import com.example.reclamationcrud.database.AppDatabase;
import com.example.reclamationcrud.entities.Reclamation;
import com.google.android.material.textfield.TextInputEditText;

import java.security.SecureRandom;
import java.util.Date;

public class NewReclamationActivity extends AppCompatActivity {
    Button addBnt;
    TextInputEditText etDesc;
    String desc ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rec);

         addBnt =findViewById(R.id.btn_ajouter);
         etDesc =findViewById(R.id.et_descrip);


        addBnt.setOnClickListener(v -> {
            validateInput();
        });

    }

    void validateInput(){
        desc=etDesc.getText().toString();


        if(!desc.isEmpty()){
            Reclamation reclamation =new Reclamation(0,generateRecName(),desc,false,new Date());
            addReclamation(reclamation);
            Toast.makeText(getApplicationContext(), "Reclamation ajoutée", Toast.LENGTH_SHORT).show();
            finish();

        }
        else{
            Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show();

        }


    }

    void addReclamation(Reclamation reclamation) {
        AppDatabase.getDatabase(getApplicationContext()).getRecDao().upsertReclamation(reclamation);
    }



    public static String generateRecName() {
        final String PREFIX = "#Rec";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PREFIX);

        while (sb.length() < 8) {
            int randomChar = random.nextInt(26); // Generates a random number between 0 and 25

            sb.append(randomChar);
        }

        return sb.toString();
    }
}