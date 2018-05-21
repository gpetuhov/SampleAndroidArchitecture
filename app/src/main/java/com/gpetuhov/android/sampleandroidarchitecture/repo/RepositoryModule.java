package com.gpetuhov.android.sampleandroidarchitecture.repo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  Repository provideRepository() {
    return new Repository();
  }
}
