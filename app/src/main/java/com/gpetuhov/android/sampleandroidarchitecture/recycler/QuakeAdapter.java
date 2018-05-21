package com.gpetuhov.android.sampleandroidarchitecture.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpetuhov.android.sampleandroidarchitecture.R;
import com.gpetuhov.android.sampleandroidarchitecture.models.Quake;

import java.util.ArrayList;
import java.util.List;

public class QuakeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<Quake> quakeList = new ArrayList<>();
  private LayoutInflater layoutInflater;

  public QuakeAdapter(Context context) {
    this.layoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = layoutInflater.inflate(R.layout.quake_list_item, parent, false);
    return new QuakeHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ((QuakeHolder) holder).bind(quakeList.get(position));
  }

  @Override
  public int getItemCount() {
    return quakeList.size();
  }

  public void setQuakeList(List<Quake> quakeList) {
    this.quakeList.clear();
    this.quakeList.addAll(quakeList);
    notifyDataSetChanged();
  }

  // === ViewHolder ===

  private class QuakeHolder extends RecyclerView.ViewHolder {

    private TextView locationTextView;
    private TextView magnitudeTextView;

    QuakeHolder(View itemView) {
      super(itemView);
      locationTextView = itemView.findViewById(R.id.location);
      magnitudeTextView = itemView.findViewById(R.id.magnitude);
    }

    void bind(Quake quake) {
      locationTextView.setText(quake.getLocation());
      magnitudeTextView.setText(quake.getMagnitude());
    }
  }
}
