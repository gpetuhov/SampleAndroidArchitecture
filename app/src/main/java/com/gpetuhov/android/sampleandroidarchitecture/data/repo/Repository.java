package com.gpetuhov.android.sampleandroidarchitecture.data.repo;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.gpetuhov.android.sampleandroidarchitecture.data.models.Quake;
import com.gpetuhov.android.sampleandroidarchitecture.data.room.QuakeDao;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeModel;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeProperties;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeResult;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Repository implements all data logic
// (loading from the network, persisting in the database).
// Data is provided outside Repository in the form of LiveData,
// so that other application parts could observe changes in the data.

// Note that Android Architecture recommends using single source of truth.
// That is, we read data from the database ONLY. It is read in the from of LiveData.
// Data loaded from the network is not sent to UI directly.
// Instead it is written into database, thus triggering LiveData update,
// which in turn triggers UI update in MainActivity.
public class Repository {

  private Executor executor;
  private QuakeDao quakeDao;

  Repository(QuakeDao quakeDao) {
    this.quakeDao = quakeDao;
    this.executor = Executors.newSingleThreadExecutor();
  }

  public LiveData<List<Quake>> getQuakeList() {
    // Immediately read data from the database and start loading data from the network
    refreshQuakeList();
    return quakeDao.readAll();
  }

  private void refreshQuakeList() {
    Retrofit retrofit = getRetrofit();

    // Create Retrofit service
    QuakeService quakeService = retrofit.create(QuakeService.class);

    // This all is done on a separate thread,
    // because network and database operation are not allowed on UI thread.
    executor.execute(() -> {
      try {
        // Load data from the network.
        // (we request data in the form of JSON and limit result quake list size).
        Response<QuakeResult> response = quakeService.getQuakes("geojson", "10").execute();

        // Delete cached data, deserialize response and write new data into database
        // This will trigger update of LiveData, previously read from the database in getQuakeList()
        quakeDao.deleteAll();
        quakeDao.insertAll(getQuakeListFromResponse(response));

      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  // Build Retrofit
  private Retrofit getRetrofit() {
    return new Retrofit.Builder()
      .client(getOkHttpClient())
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
      .build();
  }

  // Build OkHttp client
  private OkHttpClient getOkHttpClient() {
    return new OkHttpClient.Builder()
      .addNetworkInterceptor(
        // This interceptor outputs network requests into log
        new HttpLoggingInterceptor().setLevel(
          HttpLoggingInterceptor.Level.BASIC
        )
      )
      .build();
  }

  // Deserialize result JSON into list of Quake models
  private List<Quake> getQuakeListFromResponse(Response<QuakeResult> response) {
    List<Quake> quakeList = new ArrayList<>();

    QuakeResult quakeResult = response.body();
    List<QuakeModel> quakeModelList = quakeResult != null ? quakeResult.getQuakeList() : new ArrayList<>();
    for (QuakeModel quakeModel: quakeModelList) {
      QuakeProperties quakeProperties = quakeModel.getQuakeProperties();
      if (quakeProperties != null) {
        quakeList.add(new Quake(quakeProperties.getLocation(), quakeProperties.getMagnitudeString()));
      }
    }

    return quakeList;
  }
}
