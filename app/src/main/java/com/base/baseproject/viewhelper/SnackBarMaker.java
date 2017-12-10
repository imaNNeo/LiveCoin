package com.base.baseproject.viewhelper;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.base.baseproject.R;
import com.base.baseproject.listeners.OnRetryClickedListener;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class SnackBarMaker {

        /*
        Programmer iman khoshabi
        iman.neofight@gmail.com
    */
    public static Snackbar makeActionTextSnackbar(Context ctx, View container,
                                                  String contentText, String actionText,
                                                  final OnRetryClickedListener onRetryClickedListener){
        Snackbar snackbar;

        // Create the Snackbar
        snackbar = Snackbar.make(container, "", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(ctx.getResources().getColor(R.color.snack_action_color));
        snackbar.setAction(actionText, view -> {
            if(onRetryClickedListener!=null)
                onRetryClickedListener.onRetryClicked();
        });
        snackbar.setText(contentText);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ctx.getResources().getColor(R.color.white));
        snackbar.show();

        return snackbar;
    }

}
