package com.gpetuhov.android.sampleandroidarchitecture.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// ViewModel keeps data for our MainActivity.
// While MainActivity can be destroyed (for example, on screen rotation),
// ViewModel is not, and provides the recreated activity with already available data.
public class QuakeListViewModel extends ViewModel {
  private static final String TAG = QuakeListViewModel.class.getSimpleName();

  // Data must be wrapped into LiveData
  private LiveData<List<Quake>> quakeList;

  public LiveData<List<Quake>> getQuakeList() {
    // Check if data already exists
    if (quakeList != null) {
      Log.d(TAG, "Using previously generated dummy data");
      return quakeList;
    }

    // Data does not exist.
    // Create dummy data.
    Log.d(TAG, "Generating dummy data");
    List<Quake> list = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      list.add(new Quake("Location number" + i, "5"));
    }

    // Our data is just the List, so it must be wrapped into MutableLiveData
    MutableLiveData<List<Quake>> mutableLiveData = new MutableLiveData<>();
    mutableLiveData.setValue(list);
    quakeList = mutableLiveData;
    return quakeList;
  }
}
