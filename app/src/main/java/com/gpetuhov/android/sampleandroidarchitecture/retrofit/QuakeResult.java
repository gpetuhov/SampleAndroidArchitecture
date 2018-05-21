package com.gpetuhov.android.sampleandroidarchitecture.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuakeResult implements Serializable {
  @SerializedName("features")
  @Expose
  private List<QuakeModel> quakeList;

  public List<QuakeModel> getQuakeList() {
    return quakeList;
  }
}
