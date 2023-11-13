package com.example.reclamationcrud.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "Reclamation")

public class Reclamation {
    @PrimaryKey(autoGenerate = true)
    private int id_reclamation;
   private String numeroReclamation;
   private String description;
    private boolean traitee;
   private Date dateSoumission;




    public Reclamation(int id_reclamation, String numeroReclamation, String description, boolean traitee, Date dateSoumission) {
        this.id_reclamation = id_reclamation;
        this.numeroReclamation = numeroReclamation;
        this.description = description;
        this.traitee = traitee;
        this.dateSoumission = dateSoumission;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getNumeroReclamation() {
        return numeroReclamation;
    }

    public void setNumeroReclamation(String numeroReclamation) {
        this.numeroReclamation = numeroReclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTraitee() {
        return traitee;
    }

    public void setTraitee(boolean traitee) {
        this.traitee = traitee;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Reclamation)) {
            return false;
        }
        Reclamation otherReclamation = (Reclamation) other;
        return id_reclamation == otherReclamation.id_reclamation && numeroReclamation.equals(otherReclamation.numeroReclamation);
    }
}
