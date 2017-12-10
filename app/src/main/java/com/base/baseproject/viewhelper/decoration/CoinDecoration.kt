package com.base.baseproject.viewhelper.decoration

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.base.baseproject.R

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
class CoinDecoration(val context : Context) : RecyclerView.ItemDecoration(){

    private val size = context.resources.getDimensionPixelSize(R.dimen.size1x)

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.left = size
        outRect?.right = size
        outRect?.top = size
        outRect?.bottom = size
    }
}
