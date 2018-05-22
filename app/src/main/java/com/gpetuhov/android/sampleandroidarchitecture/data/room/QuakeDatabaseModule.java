package com.gpetuhov.android.sampleandroidarchitecture.data.room;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gpetuhov.android.sampleandroidarchitecture.QuakeApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

// This Dagger module provides QuakeDatabase and QuakeDao objects
@Module
public class QuakeDatabaseModule {

  @Provides
  @Singleton
  Context provideContext() {
    return QuakeApp.getApplication().getApplicationContext();
  }

  @Provides
  @Singleton
  QuakeDatabase provideQuakeDatabase(Context context) {
    return Room.databaseBuilder(context, QuakeDatabase.class, "quake_database").build();
  }

  @Provides
  @Singleton
  QuakeDao provideQuakeDao(QuakeDatabase quakeDatabase) {
    return quakeDatabase.quakeDao();
  }
}
