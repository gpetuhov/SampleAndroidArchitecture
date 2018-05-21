package com.gpetuhov.android.sampleandroidarchitecture.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuakeModel implements Serializable {
  @SerializedName("properties")
  @Expose
  private QuakeProperties quakeProperties;

  public QuakeProperties getQuakeProperties() {
    return quakeProperties;
  }
}
