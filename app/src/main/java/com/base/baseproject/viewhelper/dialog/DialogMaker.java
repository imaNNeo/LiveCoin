package com.base.baseproject.viewhelper.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class DialogMaker {
    public static AlertDialog makeOneButtonDialog(final Context ctx, String message,
                                                     String btnText, final Runnable onButtonClicked){

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setPositiveButton(btnText, (dialogInterface, i) -> {
            if (onButtonClicked != null)
                onButtonClicked.run();
        });
        builder.setCancelable(false);
        builder.setMessage(message);
        return builder.create();
    }

    public static AlertDialog makeTwoButtonDialog(final Context ctx,String message,
                                                     String positiveText, final Runnable positiveRunnable,
                                                     String negativeText, final Runnable negativeRunnable){
        AlertDialog ad = new AlertDialog.Builder(ctx)
                .setPositiveButton(positiveText, (dialogInterface, i) -> {
                    if (positiveRunnable != null)
                        positiveRunnable.run();
                })
                .setNegativeButton(negativeText, (dialogInterface, i) -> {
                    if (negativeRunnable != null)
                        negativeRunnable.run();
                })
                .setMessage(message).create();
        return ad;
    }
}