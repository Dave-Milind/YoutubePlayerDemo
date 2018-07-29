package com.milind.test.HomeActivity;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HomeModule {

    Context context;
    HomeModule(Context context){
        this.context=context;


    }


    @Provides
    Context getContext(){

        return context;
    }

    @Provides
    Retrofit getRetrofit(){

        return new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    YoutubeService getYoutubeService(Retrofit retrofit){

        return retrofit.create(YoutubeService.class);
    }

}
