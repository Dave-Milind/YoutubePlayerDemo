package com.milind.test.Details;

import android.content.Context;
import android.telecom.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailsService {

    @GET("videos")
    retrofit2.Call<DetailsModel> getDetailsInfo(@Query("part") String part,@Query("id")String id,@Query("key")String apiKey);

}
