package com.gpetuhov.android.sampleandroidarchitecture;

import android.app.Application;

import com.gpetuhov.android.sampleandroidarchitecture.dagger.AppComponent;
import com.gpetuhov.android.sampleandroidarchitecture.dagger.DaggerAppComponent;

// Application class is needed to keep and return instance of Dagger component
public class QuakeApp extends Application {

  private static QuakeApp application;
  private static AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    appComponent = DaggerAppComponent.builder().build();
  }

  public static AppComponent getAppComponent() {
    return appComponent;
  }

  public static QuakeApp getApplication() {
    return application;
  }
}
