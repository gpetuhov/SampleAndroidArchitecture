package com.gpetuhov.android.sampleandroidarchitecture;

import android.app.Application;

import com.gpetuhov.android.sampleandroidarchitecture.dagger.AppComponent;
import com.gpetuhov.android.sampleandroidarchitecture.dagger.DaggerAppComponent;

public class QuakeApp extends Application {

  private static AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder().build();
  }

  public static AppComponent getAppComponent() {
    return appComponent;
  }
}
