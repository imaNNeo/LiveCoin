package com.base.baseproject.mvp.base;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.base.baseproject.di.component.ActivityComponent;
import com.base.baseproject.di.component.DaggerActivityComponent;
import com.base.baseproject.di.module.ActivityModule;
import com.base.baseproject.R;
import com.base.baseproject.data.api.ApiHelper;
import com.base.baseproject.data.db.DbHelper;
import com.base.baseproject.data.preferences.PreferencesHelper;
import com.base.baseproject.data.user.UserHelper;
import com.base.baseproject.listeners.OnActionClickedListener;
import com.base.baseproject.listeners.OnImageCropListener;
import com.base.baseproject.listeners.OnImageLoadListener;
import com.base.baseproject.mvp.MyApplication;
import com.base.baseproject.rx.SchedulerProvider;
import com.base.baseproject.utils.ActivityHolder;
import com.base.baseproject.viewhelper.SnackBarMaker;
import com.base.baseproject.viewhelper.ToolbarHolder;
import com.base.baseproject.viewhelper.dialog.DialogMaker;
import com.base.baseproject.viewhelper.dialog.DialogWaiting;
import com.base.baseproject.viewhelper.widget.AppCoordinatorLayout;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Amir Abdi on 01/05/2017.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public abstract int getLayoutId();


    public boolean isAlive = true;
    public ToolbarHolder mToolbarHolder;
    Snackbar snackbarInternetRetry;
    Dialog mWaitDialog;

    Unbinder mUnbinder;


    protected ActivityComponent mActivityComponent;

    @Inject
    public UserHelper mUserHelper;

    @Inject
    public PreferencesHelper mPreferencesHelper;

    @Inject
    public ApiHelper mApiHelper;

    @Inject
    public DbHelper mDbHelper;

    @Inject
    public SchedulerProvider mSchedulerProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mUnbinder = ButterKnife.bind(this);
        mToolbarHolder = new ToolbarHolder();
        ActivityHolder.getInstance().addActivity(this);
        mToolbarHolder = new ToolbarHolder();
        disableOtherLanguages();
        mWaitDialog = new DialogWaiting(this);
    }

    public ActivityComponent getComponent(){
        if(mActivityComponent==null)
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(((MyApplication)getApplication()).getComponent())
                    .build();
        return mActivityComponent;
    }



    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.activity_enter_effect_fade,R.anim.activity_close_effect_fade);
        isAlive = true;
    }

    @Override
    protected void onDestroy(){
        mUnbinder.unbind();
        mToolbarHolder.unbind();
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.activity_enter_effect_fade,R.anim.activity_close_effect_fade);
        isAlive = false;
    }


    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableOtherLanguages(){
        Resources res = getApplicationContext().getResources();

        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public View getRootView(){
        if(getWindow().getDecorView() instanceof ViewGroup){
            View v = findAppCoordinatorLayout((ViewGroup) getWindow().getDecorView());
            if(v!=null)
                return v;
        }
        return findViewById(R.id.root_view);
    }
    private View findAppCoordinatorLayout(ViewGroup vg){
        for(int i=0;i<vg.getChildCount();i++){
            View v = vg.getChildAt(i);
            if(v instanceof AppCoordinatorLayout){
                return v;
            }

            if(v instanceof ViewGroup){
                return findAppCoordinatorLayout((ViewGroup) v);
            }
        }

        return null;
    }

    public void showWaitDialog(){
        if (!mWaitDialog.isShowing())
            mWaitDialog.show();
    }
    public void hideWaitDialog(){
        if(mWaitDialog.isShowing())
            mWaitDialog.dismiss();
    }

    public void showMessageSnackShort(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }


        Snackbar snackbar;
        if(getRootView()==null)
            snackbar = Snackbar.make(((ViewGroup)getWindow().getDecorView()).getChildAt(0),message,Snackbar.LENGTH_SHORT);
        else
            snackbar = Snackbar.make(getRootView(),message,Snackbar.LENGTH_SHORT);

        ((TextView)snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }
    public void showMessageSnackLong(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Snackbar snackbar;

        int duration = 5000;
        if(getRootView()==null)
            snackbar = Snackbar.make(((ViewGroup)getWindow().getDecorView()).getChildAt(0),message,duration);
        else
            snackbar = Snackbar.make(getRootView(),message,duration);

        ((TextView)snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void showMessageToastShort(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showMessageToastLong(String message){
        if(message == null){
            showMessageSnackLong("null");
            return;
        }
        if(message.length()==0) {
            showMessageSnackLong("");
            return;
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showSystemError(){
        String message = getString(R.string.system_error);
        showMessageSnackLong(message);
    }

    public void showInternetRetry(View container, OnActionClickedListener onActionClickedListener){
        if(container == null)
            container = getRootView();

        String networkProblemMessage = getResources().getString(R.string.error_network_connection_error);
        String retryStr = getResources().getString(R.string.retry);

        snackbarInternetRetry = SnackBarMaker.makeActionTextSnackbar(this,
                container, networkProblemMessage, retryStr, () -> {
                    if(onActionClickedListener!=null)
                        onActionClickedListener.onActionClicked();
                });
        snackbarInternetRetry.show();
    }
    public void hideInternetRetrySnackBar(){
        if(snackbarInternetRetry !=null && snackbarInternetRetry.isShown())
            snackbarInternetRetry.dismiss();
    }

    /*Loading Image*/
    private OnImageLoadListener mOnImageLoadListener;
    public void loadImageFromGalleryOrCamera(OnImageLoadListener listener){
        mOnImageLoadListener = listener;
        EasyImage.openChooserWithGallery(this,"Load Image",0);
    }

    Uri croppedUri;
    private OnImageCropListener mOnImageCropListener;
    public void cropImage(File imageFile, int aspectX, int aspectY, OnImageCropListener listener){
        mOnImageCropListener = listener;
        File croppingFile = new File(imageFile.getAbsolutePath() + "-" + aspectX + "-" + aspectY);
        try {
            croppingFile.createNewFile();
        } catch (IOException e) {}
        if(croppingFile==null || !croppingFile.exists()){
            showMessageSnackLong("An error when creating temp file to crop");
            return;
        }
        croppedUri = Uri.fromFile(croppingFile);
        Crop.of(Uri.fromFile(imageFile), croppedUri).withAspect(aspectX,aspectY).start(this);
    }
    /*Loading Image*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                showMessageSnackLong("Something went wrong\n" + e.getMessage());
            }

            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                if(mOnImageLoadListener !=null) {
                    if (imageFiles!=null && imageFiles.size()>0)
                        mOnImageLoadListener.onImageLoad(imageFiles.get(0));
                }
            }


            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(BaseActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
        if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            if(mOnImageCropListener!=null) {
                mOnImageCropListener.onImageLoad(new File(croppedUri.getPath()));
            }
        }
    }


    public void showOneButtonDialog(String message, String btnText, Runnable onButtonClicked){
        DialogMaker.makeOneButtonDialog(this,message,btnText,onButtonClicked).show();
    }
    public void showAcceptLogoutDialog(Runnable onButtonClicked){
        DialogMaker.makeTwoButtonDialog(this,"Are you logout??","Yes",onButtonClicked,"No",null).show();
    }

    public void showSelectDateDialog(int year, int month, int day, DatePickerDialog.OnDateSetListener listener){
        if(year==-1) year = Calendar.getInstance().get(Calendar.YEAR);
        if(month==-1) month = Calendar.getInstance().get(Calendar.MONTH);
        if(day==-1) day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,listener,year,month,day);
        datePickerDialog.show();
    }

    public void showSelectTimeDialog(int h,int m,TimePickerDialog.OnTimeSetListener listener){
        if(h==-1) h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(m==-1) m = Calendar.getInstance().get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,listener,h,m,true);
        timePickerDialog.show();
    }
}
