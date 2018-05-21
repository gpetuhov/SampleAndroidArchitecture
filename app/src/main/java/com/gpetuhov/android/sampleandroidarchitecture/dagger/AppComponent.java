package com.gpetuhov.android.sampleandroidarchitecture.dagger;

import com.gpetuhov.android.sampleandroidarchitecture.models.QuakeListViewModel;
import com.gpetuhov.android.sampleandroidarchitecture.repo.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
  RepositoryModule.class
})
public interface AppComponent {
  void inject(QuakeListViewModel quakeListViewModel);
}
