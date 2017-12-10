package com.base.baseproject.mvp.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class BaseMvpPresenter<V extends BaseMvpView> {

    private WeakReference<V> mMvpView;
    private final CompositeDisposable mCompositeDisposable;
    public BaseMvpPresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public CompositeDisposable getDisposable(){
        return mCompositeDisposable;
    }

    public void onAttach(V view) {
        mMvpView = new WeakReference<V>(view);
    }

    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView.clear();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null && mMvpView.get() != null;
    }

    public V getMvpView() {
        return mMvpView.get();
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
