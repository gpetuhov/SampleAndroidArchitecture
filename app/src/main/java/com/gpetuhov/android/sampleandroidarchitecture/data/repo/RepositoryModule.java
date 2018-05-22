package com.gpetuhov.android.sampleandroidarchitecture.data.repo;

import com.gpetuhov.android.sampleandroidarchitecture.data.room.QuakeDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  Repository provideRepository(QuakeDao quakeDao) {
    return new Repository(quakeDao);
  }
}
