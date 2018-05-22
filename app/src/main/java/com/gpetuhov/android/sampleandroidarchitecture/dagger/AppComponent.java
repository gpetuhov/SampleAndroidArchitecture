package com.gpetuhov.android.sampleandroidarchitecture.dagger;

import com.gpetuhov.android.sampleandroidarchitecture.data.models.QuakeListViewModel;
import com.gpetuhov.android.sampleandroidarchitecture.data.repo.RepositoryModule;
import com.gpetuhov.android.sampleandroidarchitecture.data.room.QuakeDatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
  RepositoryModule.class,
  QuakeDatabaseModule.class
})
public interface AppComponent {
  void inject(QuakeListViewModel quakeListViewModel);
}
