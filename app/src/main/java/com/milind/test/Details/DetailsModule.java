package com.milind.test.Details;

import android.content.Context;

import com.milind.test.HomeActivity.YoutubeService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DetailsModule {

Context context;

DetailsModule(Context context){

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
    DetailsService getDetailsService(Retrofit retrofit){

        return retrofit.create(DetailsService.class);
    }

}
