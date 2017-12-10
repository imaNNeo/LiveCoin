package com.base.baseproject.mvp.fragment.main;

import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.api.retrofit.models.RequestObjects;
import com.base.baseproject.data.api.retrofit.models.ResponseObjects;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.mvp.base.loading.LoadingMvpPresenter;
import com.base.baseproject.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class MainPresenter extends LoadingMvpPresenter<MainView> {
    private static final byte RQ_GET_COIN_ITEMS = 1;


    ApiHelper mApiHelper;
    UserHelper mUserHelper;
    SchedulerProvider mSchedulerProvider;
    public MainPresenter(ApiHelper apiHelper,
                          UserHelper userHelper,
                          SchedulerProvider schedulerProvider) {
        super();
        mApiHelper = apiHelper;
        mUserHelper = userHelper;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void onAttach(MainView view) {
        super.onAttach(view);

        start();
    }

    private void start() {
        getCoinItemsFields = new RequestObjects.GetCoinItemsFields(20);
        callGetCoins();
    }

    RequestObjects.GetCoinItemsFields getCoinItemsFields;
    private void callGetCoins(){
        if(getCoinItemsFields==null)return;
        getMvpView().showLoading();
        mApiHelper.getCoinItems(getCoinItemsFields)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<List<ResponseObjects.CoinItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getDisposable().add(d);
                    }

                    @Override
                    public void onSuccess(List<ResponseObjects.CoinItem> coinItems) {
                        getMvpView().hideLoading();
                        getMvpView().refreshCoinItems(coinItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        handleError(e,RQ_GET_COIN_ITEMS);
                    }
                });
    }

    @Override
    protected void onRetryClicked(int rqCode) {

    }
}
