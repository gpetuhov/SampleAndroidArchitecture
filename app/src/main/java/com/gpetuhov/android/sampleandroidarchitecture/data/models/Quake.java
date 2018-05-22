package com.gpetuhov.android.sampleandroidarchitecture.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// This class is used both for storing quakes in the database
// and for displaying quakes in the UI.
// In this example we need only quake location and magnitude.
// Entity class MUST have getters and setters for ALL fields.
@Entity(tableName = "quakes")
public class Quake {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int id;

  @ColumnInfo(name = "location")
  private String location;

  @ColumnInfo(name = "magnitude")
  private String magnitude;

  public Quake(String location, String magnitude) {
    this.location = location;
    this.magnitude = magnitude;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(String magnitude) {
    this.magnitude = magnitude;
  }
}
