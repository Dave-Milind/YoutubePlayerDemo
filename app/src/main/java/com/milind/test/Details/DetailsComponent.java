package com.milind.test.Details;

import android.content.Context;

import dagger.Component;

@Component(modules = DetailsModule.class)
public interface DetailsComponent {

    Context getDetailsContext();

    DetailsService getDetailsService();


}
