package com.gpetuhov.android.sampleandroidarchitecture.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.gpetuhov.android.sampleandroidarchitecture.QuakeApp;
import com.gpetuhov.android.sampleandroidarchitecture.data.repo.Repository;

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

    // Load data from the Repository.
    // ViewModel knows nothing about how the data is provided by the Repository.
    // Repository just provides LiveData which is in turn provided
    // to MainActivity by ViewModel, and MainActivity updates the UI
    // every time LiveData changes.
    Log.d(TAG, "Loading data from the repo");
    quakeList = repository.getQuakeList();

    return quakeList;
  }
}
