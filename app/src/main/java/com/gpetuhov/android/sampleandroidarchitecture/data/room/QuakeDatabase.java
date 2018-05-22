package com.gpetuhov.android.sampleandroidarchitecture.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gpetuhov.android.sampleandroidarchitecture.data.models.Quake;

// This is database class. Implementation is generated by Room library.
// We don't need to export database schema to a JSON file, so set exportSchema = false
@Database(entities = {Quake.class}, exportSchema = false, version = 1)
public abstract class QuakeDatabase extends RoomDatabase {
  public abstract QuakeDao quakeDao();
}
