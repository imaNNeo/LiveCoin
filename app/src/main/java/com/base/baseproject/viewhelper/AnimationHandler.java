package com.base.baseproject.viewhelper;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.base.baseproject.R;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class AnimationHandler {
    public static void shake(Context ctx,View v){
        Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.shake);
        v.startAnimation(animation);
    }

    public static void fadeIn(Context ctx,View v){
        v.startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.fade_in));
    }

    public static void fadeOut(Context ctx,View v){
        v.startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.fade_out));
    }


    public static void slideRightAlpha(View v,int delay,int duration){
        v.animate()
                .alpha(1f)
                .translationX(v.getWidth())
                .setStartDelay(delay)
                .setDuration(duration)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
    public static void slide(View v, int delay, int duration){
        v.animate()
                .alpha(1f)
                .translationX(0)
                .setStartDelay(delay)
                .setDuration(duration)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    public static void scaleInAlpha(View v,int delay,int duration){
        v.animate()
                .alpha(1f)
                .scaleX(1)
                .scaleY(1)
                .setDuration(duration)
                .setStartDelay(delay)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
    public static void resetScaleInAlpha(View v){
        ViewCompat.setScaleX(v,0);
        ViewCompat.setScaleY(v,0);
        ViewCompat.setAlpha(v,0);
    }

}