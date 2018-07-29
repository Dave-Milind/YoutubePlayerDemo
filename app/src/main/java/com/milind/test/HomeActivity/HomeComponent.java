package com.milind.test.HomeActivity;

import android.content.Context;

import dagger.Component;

@Component(modules = HomeModule.class)
public interface HomeComponent {

    Context getHomeContext();

    YoutubeService getYoutubeService();

}
