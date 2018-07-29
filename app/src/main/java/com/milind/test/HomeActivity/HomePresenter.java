package com.milind.test.HomeActivity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.milind.test.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    GoogleSignInClient mGoogleSignInClient;
    HomeContract.View view;
    Context homeContext;
    String TAG = "PresenterTag";

    @Override
    public void start() {

        init();
    }


    void init() {

        homeContext = HomePage.homeComponent.getHomeContext();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(homeContext, gso);
        youtubeApiCall();

    }


    void youtubeApiCall() {
        YoutubeService youtubeService = HomePage.homeComponent.getYoutubeService();
        Call<YoutubeModel> call = youtubeService.getAllFunnyVideos();
        call.enqueue(new Callback<YoutubeModel>() {
            @Override
            public void onResponse(Call<YoutubeModel> call, Response<YoutubeModel> response) {
                if (response != null) {
                    Log.i(TAG, "onResponse called ");
                    Adapter adapter = new Adapter(homeContext, response.body().getItems());

                    Log.i(TAG, "Items list: " + response.body().getItems().toString());
                    view.setAdapter(adapter);
                } else {

                    Toast.makeText(homeContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<YoutubeModel> call, Throwable t) {
                Toast.makeText(homeContext, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void attachView(HomeContract.View view) {

        this.view = view;
    }

    void signOut() {

        mGoogleSignInClient.signOut().addOnCompleteListener((Activity) homeContext, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


            }
        });

    }


}
