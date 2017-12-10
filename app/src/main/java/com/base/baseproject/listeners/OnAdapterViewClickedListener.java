package com.base.baseproject.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public interface OnAdapterViewClickedListener {
    void onAdapterViewClicked(View v, RecyclerView.ViewHolder vh, int pos);
}
