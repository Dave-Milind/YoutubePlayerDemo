package com.milind.test.HomeActivity;

import com.milind.test.BasePresenter;
import com.milind.test.BaseView;

public interface HomeContract {

interface View extends BaseView{

    public void setAdapter(Adapter adapter);

}
interface Presenter<T extends BaseView> extends BasePresenter{


    void attachView(View view);

}
}
