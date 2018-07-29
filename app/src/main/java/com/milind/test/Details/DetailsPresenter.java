package com.milind.test.Details;

import com.milind.test.YoutubeConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter implements DetailsContract.Presenter {


    DetailsContract.View views;
    DetailsComponent detailsComponent;

    @Override
    public void start(String videoId) {

        detailsComponent = DetailsActivity.detailsComponent;

        makeDetailsApiCall(videoId);

    }

    @Override
    public void attachView(DetailsContract.View views) {

        this.views=views;
    }

    void makeDetailsApiCall(String videoId) {

        detailsComponent.getDetailsService().getDetailsInfo("statistics", videoId, YoutubeConfig.API_KEY).enqueue(new Callback<DetailsModel>() {
            @Override
            public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {

                if (response != null) {
                    views.setDataToViews(response.body());
                }


            }

            @Override
            public void onFailure(Call<DetailsModel> call, Throwable t) {

            }
        });

    }
}
