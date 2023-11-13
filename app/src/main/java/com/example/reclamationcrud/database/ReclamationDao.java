package com.example.reclamationcrud.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.reclamationcrud.entities.Reclamation;

import java.util.List;

@Dao
public interface ReclamationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsertReclamation(Reclamation reclamation);

    @Delete
    void deleteReclamation(Reclamation reclamation);

    @Query("SELECT * FROM Reclamation")
    List<Reclamation> getAllReclamations();
    @Query("SELECT * FROM Reclamation WHERE id_reclamation = :id")
    Reclamation getReclamationById(int id);
}
