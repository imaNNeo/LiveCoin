package com.base.baseproject.mvp.activity.splash;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.base.baseproject.R;
import com.base.baseproject.mvp.activity.main.MainActivity;
import com.base.baseproject.mvp.base.BaseActivity;
import com.base.baseproject.viewhelper.widget.AppProgressView;

import javax.inject.Inject;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashView{

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    private static final byte REQUEST_CODE_ASK_PERMISSIONS = 1;
    public static final long WAITING_TIME = 2000;

    @BindView(R.id.pv_loading)
    AppProgressView pvLoading;

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        mPresenter.onAttach(this);
        checkPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


    private void checkPermissions() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasExternalPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int hasInternetPermission = checkSelfPermission(Manifest.permission.INTERNET);
            int camera = checkSelfPermission(Manifest.permission.CAMERA);

            if (hasExternalPermission != PackageManager.PERMISSION_GRANTED ||
                    hasInternetPermission != PackageManager.PERMISSION_GRANTED ||
                    camera != PackageManager.PERMISSION_GRANTED
                    ) {
                requestPermissions(new String[] {
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.INTERNET,
                                Manifest.permission.CAMERA
                        },
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            startWorking();
        }else{
            startWorking();
        }
    }

    private void startWorking() {
        mPresenter.allPermissionsGranted();
        new Handler().postDelayed(() -> mPresenter.onWaitingTimeFinished(),WAITING_TIME);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                boolean allGranted = true;
                int pos = 0;
                for(int i=0;i<grantResults.length;i++){
                    int r = grantResults[i];
                    if(r != PackageManager.PERMISSION_GRANTED){
                        allGranted = false;
                        pos = i;
                        break;
                    }
                }
                if (allGranted) {
                    // Permission Granted
                    checkPermissions();
                } else {
                    // Permission Denied
                    Toast.makeText(SplashActivity.this, "permission Denied " + pos, Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void showLoading() {
        pvLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pvLoading.setVisibility(View.GONE);
    }

    @Override
    public void closeYourself() {
        finish();
    }

    @Override
    public void gotoMain() {
        MainActivity.start(this);
    }

}