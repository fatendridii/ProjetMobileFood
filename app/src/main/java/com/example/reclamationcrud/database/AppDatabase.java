package com.example.reclamationcrud.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.reclamationcrud.entities.Reclamation;
import com.example.reclamationcrud.utils.Converters;


@Database(entities = {Reclamation.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ReclamationDao getRecDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = createDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase createDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Reclamation_db.db")
                .allowMainThreadQueries()
                .build();
    }
}
