package com.gpetuhov.android.sampleandroidarchitecture.models;

public class Quake {
  private String location;
  private String magnitude;

  public Quake(String location, String magnitude) {
    this.location = location;
    this.magnitude = magnitude;
  }

  public String getLocation() {
    return location;
  }

  public String getMagnitude() {
    return magnitude;
  }
}
