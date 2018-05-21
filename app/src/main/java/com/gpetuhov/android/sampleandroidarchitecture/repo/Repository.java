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

public class Repository {

  public LiveData<List<Quake>> getQuakeList() {
    MutableLiveData<List<Quake>> data = new MutableLiveData<>();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .addNetworkInterceptor(
        new HttpLoggingInterceptor().setLevel(
          HttpLoggingInterceptor.Level.BASIC
        )
      )
      .build();

    Retrofit retrofit = new Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
      .build();

    QuakeService quakeService = retrofit.create(QuakeService.class);
    // Call.enqueue() is executed on a separate thread
    quakeService.getQuakes("geojson", "10").enqueue(new Callback<QuakeResult>() {
      @Override
      public void onResponse(Call<QuakeResult> call, Response<QuakeResult> response) {
        List<Quake> quakeList = new ArrayList<>();

        QuakeResult quakeResult = response.body();
        List<QuakeModel> quakeModelList = quakeResult.getQuakeList();
        if (quakeModelList != null) {
          for (QuakeModel quakeModel: quakeModelList) {
            QuakeProperties quakeProperties = quakeModel.getQuakeProperties();
            if (quakeProperties != null) {
              quakeList.add(new Quake(quakeProperties.getLocation(), quakeProperties.getMagnitudeString()));
            }
          }
        }

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
