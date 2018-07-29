package com.milind.test.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.milind.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View{

    @BindView(R.id.details_likes)
    TextView detailsLikes;
    @BindView(R.id.details_dislikes)
    TextView detailsDislikes;

    static DetailsComponent detailsComponent;
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        detailsComponent = DaggerDetailsComponent.builder().detailsModule(new DetailsModule(this)).build();

        String videoId=getIntent().getStringExtra("videoId");
        detailsPresenter = new DetailsPresenter();
        detailsPresenter.attachView(this);
        detailsPresenter.start(videoId);


    }

    @Override
    public void setDataToViews(DetailsModel detailsModel) {
        DetailsModel.Statistics statistics=detailsModel.getItems().get(0).getStatistics();
      detailsLikes.setText("This video is liked by "+statistics.getLikeCount());
        detailsDislikes.setText("This video is disliked by "+statistics.getDislikeCount());


    }
}
