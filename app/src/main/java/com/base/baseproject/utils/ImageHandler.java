package com.base.baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.base.baseproject.R;
import com.base.baseproject.viewhelper.widget.AppImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Single;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class ImageHandler {
    private static ImageHandler mInstance;
    public static ImageHandler getmInstance(Context ctx){
        if(ctx instanceof Activity) ctx = ctx.getApplicationContext();
        if(mInstance==null)
            mInstance = new ImageHandler(ctx);

        /*if(UserHandler.getInstance(ctx).isLogin()) {
            ServiceObjects.UserLoginAnswer answer = UserHandler.getInstance(ctx).getUserLoginAnswer();
            mInstance.setAuthorization(answer.token_type,answer.access_token);
        }*/

        return mInstance;
    }

    Context mContext;
    private String authorization;
    public ImageHandler(Context ctx){
        mContext = ctx;
    }

    private void setAuthorization(String tokenType, String accessToken) {
        if(TextUtils.isEmpty(tokenType) || TextUtils.isEmpty(accessToken))
            return;

        this.authorization = tokenType + " " + accessToken;
    }


    public Bitmap convertBase64(String base64){
        if(TextUtils.isEmpty(base64))
            return null;

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public void loadImage(String imageUrl, ImageView iv, boolean isCircle,
                          boolean withLoadingPlaceHolder,boolean cache,
                          @DrawableRes int errorRes){
        if(iv==null)return;

        if(imageUrl==null) imageUrl = "";

        DiskCacheStrategy cacheStrategy = cache?DiskCacheStrategy.ALL:DiskCacheStrategy.NONE;
        try{
            if(!isCircle) {
                int placeHolderRes = 0;
                if(withLoadingPlaceHolder){
                    if(errorRes!=0)
                        placeHolderRes = errorRes;
                    else
                        placeHolderRes = R.drawable.loading;
                }

                Glide.with(mContext)
                        .load(imageUrl)
                        .placeholder(placeHolderRes)
                        .diskCacheStrategy(cacheStrategy)
                        .error(placeHolderRes)
                        .into(iv);
            }else{
                int placeHolderRes = 0;
                if(withLoadingPlaceHolder) {
                    if(errorRes!=0)
                        placeHolderRes = errorRes;
                    else
                        placeHolderRes = R.drawable.loading_circle;
                }

                Glide.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy(cacheStrategy)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .placeholder(placeHolderRes)
                        .error(placeHolderRes)
                        .into(iv);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadResImage(@DrawableRes int resId, ImageView iv){
        Glide.with(mContext)
                .load(resId)
                .into(iv);
    }


    public static Bitmap base64ToBmp(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static Single<String> bmpToBase64(Bitmap bitmap)
    {
        return Single.create(e -> {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            String base64 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
            e.onSuccess(base64);
        });
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap getBitmapFromImageView(AppImageView appImageView){
        Bitmap bmp = null;
        TransitionDrawable drawable = (TransitionDrawable) appImageView.getDrawable();
        int i=0;
        while(true){
            try {
                Drawable d = drawable.getDrawable(i);
                if(d instanceof GlideBitmapDrawable){
                    GlideBitmapDrawable gbd = (GlideBitmapDrawable) d;
                    bmp = gbd.getBitmap();
                    break;
                }
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return bmp;
    }

    public static boolean saveBitmapToFile(Bitmap bmp,File f){
        if(bmp==null || f==null)return false;

        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {}
        }




        FileOutputStream out = null;
        boolean success = false;
        try {
            out = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public static Single<Bitmap> saveCameraBitmap(byte[] data){
        return Single.create(e -> {
            Bitmap takedBitmap;

            BitmapFactory.Options op = new BitmapFactory.Options();
            op.outWidth = 1000;
            op.outHeight = 400;
            takedBitmap = BitmapFactory.decodeByteArray(data,0,data.length,op);
            e.onSuccess(takedBitmap);
        });
    }
}
