package com.gpetuhov.android.sampleandroidarchitecture.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.gpetuhov.android.sampleandroidarchitecture.models.Quake;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeModel;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeProperties;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeResult;
import com.gpetuhov.android.sampleandroidarchitecture.retrofit.QuakeService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Repository implements all data logic
// (loading from the network, persisting in the database).
// Data is provided outside Repository in the form of LiveData,
// so that other application parts could observe changes in the data.
public class Repository {

  public LiveData<List<Quake>> getQuakeList() {
    // This is the result, that will be returned
    MutableLiveData<List<Quake>> data = new MutableLiveData<>();

    // Build OkHttp client
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .addNetworkInterceptor(
        // This interceptor outputs network requests into log
        new HttpLoggingInterceptor().setLevel(
          HttpLoggingInterceptor.Level.BASIC
        )
      )
      .build();

    // Build Retrofit
    Retrofit retrofit = new Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
      .build();

    // Create Retrofit service
    QuakeService quakeService = retrofit.create(QuakeService.class);

    // Load data from the network.
    // (we request data in the form of JSON and limit result quake list size).
    // (Call.enqueue() is executed on a separate thread, so UI thread is not blocked)
    quakeService.getQuakes("geojson", "10").enqueue(new Callback<QuakeResult>() {
      @Override
      public void onResponse(Call<QuakeResult> call, Response<QuakeResult> response) {
        List<Quake> quakeList = new ArrayList<>();

        // Deserialize result JSON into list of Quake models
        QuakeResult quakeResult = response.body();
        List<QuakeModel> quakeModelList = quakeResult != null ? quakeResult.getQuakeList() : new ArrayList<>();
        for (QuakeModel quakeModel: quakeModelList) {
          QuakeProperties quakeProperties = quakeModel.getQuakeProperties();
          if (quakeProperties != null) {
            quakeList.add(new Quake(quakeProperties.getLocation(), quakeProperties.getMagnitudeString()));
          }
        }

        // Wrap result into LiveData
        data.setValue(quakeList);
      }

      @Override
      public void onFailure(Call<QuakeResult> call, Throwable t) {
        // Do nothing on error
      }
    });

    return data;
  }
}
