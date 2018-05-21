package com.gpetuhov.android.sampleandroidarchitecture.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuakeProperties implements Serializable {
  @SerializedName("place")
  @Expose
  private String location;

  @SerializedName("mag")
  @Expose
  private Double magnitude;

  public String getLocation() {
    return location;
  }

  public Double getMagnitude() {
    return magnitude;
  }

  public String getMagnitudeString() {
    return String.valueOf(magnitude);
  }
}
