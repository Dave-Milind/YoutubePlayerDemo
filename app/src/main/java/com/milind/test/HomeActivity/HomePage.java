package com.milind.test.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.milind.test.Profile;
import com.milind.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage extends YouTubeBaseActivity implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fab_profile)
    public FloatingActionButton fabProfile;
    @BindView(R.id.fab_logout)
    public FloatingActionButton fabCamera;
    @BindView(R.id.menu)
    public FloatingActionMenu menu;

    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
    @BindView(R.id.home_swipeRefreshLayout)
    SwipeRefreshLayout homeSwipeRefreshLayout;

    HomePresenter homePresenter;
    static HomeComponent homeComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        homeComponent = DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build();
        homeSwipeRefreshLayout.setOnRefreshListener(this);
        homePresenter = new HomePresenter();
        homePresenter.attachView(this);
        homePresenter.start();


    }


    @OnClick({R.id.fab_profile, R.id.fab_logout})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab_profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
            case R.id.fab_logout:
                homePresenter.signOut();
                finishAffinity();
                break;
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (adapter != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            homeRecyclerview.setLayoutManager(linearLayoutManager);
            homeRecyclerview.setAdapter(adapter);
        }


    }

    @Override
    public void onRefresh() {

        homePresenter.youtubeApiCall();
        homeSwipeRefreshLayout.setRefreshing(false);


    }
}
