package com.milind.test.HomeActivity;

import android.telecom.Call;

import retrofit2.http.GET;

public interface YoutubeService {

    @GET("search?part=snippet&maxResults=20&q=comedy%20videos&type=video&order=date&maxResults=10&key=AIzaSyAdTtdFqjXNPm3Hw9OeDq_H3guq5YPU8tg")
    retrofit2.Call<YoutubeModel> getAllFunnyVideos();

//    @GET("")
//    Call<>getVideoLikes();
}
