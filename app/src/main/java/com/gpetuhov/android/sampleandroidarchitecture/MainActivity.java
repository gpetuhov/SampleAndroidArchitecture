package com.gpetuhov.android.sampleandroidarchitecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gpetuhov.android.sampleandroidarchitecture.models.Quake;
import com.gpetuhov.android.sampleandroidarchitecture.recycler.QuakeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(mLayoutManager);

    List<Quake> quakeList = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      quakeList.add(new Quake("Location number" + i, "5"));
    }

    QuakeAdapter adapter = new QuakeAdapter(quakeList, this);
    recyclerView.setAdapter(adapter);
  }
}
