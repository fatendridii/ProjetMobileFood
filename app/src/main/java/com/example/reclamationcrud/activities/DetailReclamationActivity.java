package com.example.reclamationcrud.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reclamationcrud.R;
import com.example.reclamationcrud.database.AppDatabase;
import com.example.reclamationcrud.entities.Reclamation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailReclamationActivity extends AppCompatActivity {

    TextView tvnumeroReclamation;
    TextView tvdesc;
    TextView tvetat_recl;
    TextView tvdate;
    String numero_recl;
    String desc;
    Boolean etat_recl;
    Date dateRecl;
    int id =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rec);
         id=getIntent().getIntExtra("id",0);

        Reclamation reclamation = AppDatabase.getDatabase(getApplicationContext()).getRecDao().getReclamationById(id);
        tvdate=findViewById(R.id.tl_dateSoumission);
        tvdesc=findViewById(R.id.tl_Descrip);
        tvetat_recl =findViewById(R.id.tl_Etat);
        tvnumeroReclamation =findViewById(R.id.tl_NumeroRec);


        String dateexpString="";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        try {
            // Parse the original date string
            Date datee = inputDateFormat.parse(reclamation.getDateSoumission().toString());

            // Format the date to the desired string format
            dateexpString = outputDateFormat.format(datee);

            // Display the formatted date
        } catch (Exception e) {
            e.printStackTrace();
        }
        numero_recl = reclamation.getNumeroReclamation();
        desc= reclamation.getDescription();
        etat_recl = reclamation.isTraitee();
        dateRecl = reclamation.getDateSoumission();

        tvnumeroReclamation.setText(numero_recl);
        tvdesc.setText(desc);
        tvdate.setText(dateexpString);
        tvetat_recl.setText(etat_recl? "Traitée":"En attente");
        ImageView ivEdit = findViewById(R.id.ivEdit);
        ImageView ivDelete = findViewById(R.id.iv_delete);

        String finalDateexpString = dateexpString;


        ivEdit.setOnClickListener(v -> {

            Intent i =new Intent(this, UpdateReclamationActivity.class);
            i.putExtra("id", reclamation.getId_reclamation());
            i.putExtra("nom", numero_recl);
            i.putExtra("desc", desc);
            i.putExtra("date", finalDateexpString);
            i.putExtra("etat", etat_recl);


            this.startActivity(i);
            finish();

        });
        ivDelete.setOnClickListener(v -> {

            AppDatabase.getDatabase(getApplicationContext()).getRecDao().deleteReclamation(reclamation);
            finish();

        });
    }


}