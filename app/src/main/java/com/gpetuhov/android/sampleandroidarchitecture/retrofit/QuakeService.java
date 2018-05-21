package com.gpetuhov.android.sampleandroidarchitecture.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuakeService {

  @GET("query")
  Call<QuakeResult> getQuakes(
    @Query("format") String format,
    @Query("limit") String limit
  );
}
