package com.gpetuhov.android.sampleandroidarchitecture.data.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.gpetuhov.android.sampleandroidarchitecture.data.models.Quake;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

// All queries to quake table are grouped into this class
@Dao
public interface QuakeDao {
  @Insert(onConflict = REPLACE)
  void insertAll(List<Quake> quakeList);

  // This query returns LiveData.
  // Data changes inside this LiveData are triggered every time
  // the corresponding table is updated.
  @Query("SELECT * FROM quakes")
  LiveData<List<Quake>> readAll();

  @Query("DELETE FROM quakes")
  void deleteAll();
}

