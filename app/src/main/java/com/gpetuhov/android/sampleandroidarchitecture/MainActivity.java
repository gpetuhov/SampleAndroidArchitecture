package com.gpetuhov.android.sampleandroidarchitecture;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gpetuhov.android.sampleandroidarchitecture.data.models.QuakeListViewModel;
import com.gpetuhov.android.sampleandroidarchitecture.recycler.QuakeAdapter;

// In this example we load list of recent quakes from USGS server
// on the first time MainActivity is created.
// If MainActivity is recreated (for example, on screen rotation),
// quake list is not loaded again.

// Data for the MainActivity is provided by QuakeListViewModel.
// It is not destroyed when activity is recreated, and so can
// provide new activity with actual data.
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Init RecyclerView
    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(mLayoutManager);
    QuakeAdapter adapter = new QuakeAdapter(this);
    recyclerView.setAdapter(adapter);

    // Get ViewModel for MainActivity and start observing data it provides.
    // If data changes, update UI by passing new quake list into the adapter.
    QuakeListViewModel viewModel = ViewModelProviders.of(this).get(QuakeListViewModel.class);
    viewModel.getQuakeList().observe(this, adapter::setQuakeList);
  }
}
