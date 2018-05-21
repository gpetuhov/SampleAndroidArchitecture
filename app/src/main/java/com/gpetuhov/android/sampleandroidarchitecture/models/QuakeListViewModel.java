package com.gpetuhov.android.sampleandroidarchitecture.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.gpetuhov.android.sampleandroidarchitecture.QuakeApp;
import com.gpetuhov.android.sampleandroidarchitecture.repo.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

// ViewModel keeps data for our MainActivity.
// While MainActivity can be destroyed (for example, on screen rotation),
// ViewModel is not, and provides the recreated activity with already available data.
public class QuakeListViewModel extends ViewModel {

  private static final String TAG = QuakeListViewModel.class.getSimpleName();

  @Inject Repository repository;

  // Data must be wrapped into LiveData
  private LiveData<List<Quake>> quakeList;

  public QuakeListViewModel() {
    QuakeApp.getAppComponent().inject(this);
  }

  public LiveData<List<Quake>> getQuakeList() {
    // Check if data already exists
    if (quakeList != null) {
      Log.d(TAG, "Using previously loaded data");
      return quakeList;
    }

    Log.d(TAG, "Loading data from network");
    quakeList = repository.getQuakeList();

    // Data does not exist.
    // Create dummy data.
//    Log.d(TAG, "Generating dummy data");
//    List<Quake> list = new ArrayList<>();
//    for (int i = 0; i < 50; i++) {
//      list.add(new Quake("Location number" + i, "5"));
//    }

    // Our data is just the List, so it must be wrapped into MutableLiveData
//    MutableLiveData<List<Quake>> mutableLiveData = new MutableLiveData<>();
//    mutableLiveData.setValue(list);
//    quakeList = mutableLiveData;
    return quakeList;
  }
}
