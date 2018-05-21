package com.gpetuhov.android.sampleandroidarchitecture;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gpetuhov.android.sampleandroidarchitecture.models.QuakeListViewModel;
import com.gpetuhov.android.sampleandroidarchitecture.recycler.QuakeAdapter;


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

    // Get ViewModel and start observing data in it
    QuakeListViewModel viewModel = ViewModelProviders.of(this).get(QuakeListViewModel.class);
    viewModel.getQuakeList().observe(this, adapter::setQuakeList);
  }
}
