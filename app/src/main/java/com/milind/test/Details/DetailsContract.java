package com.milind.test.Details;

public class DetailsContract {

    interface View{

        void setDataToViews(DetailsModel detailsModel);

    }
    interface Presenter{

        void start(String videoId);

        void attachView(View view);

    }
}
