package com.base.baseproject.viewhelper.dialog;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.base.baseproject.R;
import com.base.baseproject.listeners.OnItemSelectedListener;
import com.base.baseproject.viewhelper.adapter.AdapterStringList;
import com.base.baseproject.viewhelper.widget.AppTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class DialogSelectItem extends AppDialog {

    @BindView(R.id.tv_title)
    AppTextView tvTitle;

    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    Unbinder mUnbinder;

    @Inject
    AdapterStringList mAdapter;

    public DialogSelectItem(Activity ctx, String title, List<String> items, OnItemSelectedListener listener) {
        super(ctx);
        getComponent().inject(this);
        setContentView(R.layout.dialog_select_item);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mUnbinder = ButterKnife.bind(this);

        tvTitle.setText(title);

        mAdapter.setListener((v, vh, pos) -> {
            AdapterStringList.ViewHolderString vhs = (AdapterStringList.ViewHolderString) vh;
            if(v==vhs.rlContainer){
                dismiss();
                if(listener!=null)
                    listener.onItemSelected(pos);
            }
        });

        mAdapter.resetItems(items);

        rvContent.setLayoutManager(new LinearLayoutManager(ctx));
        rvContent.setAdapter(mAdapter);
    }

    @Override
    public View getViewContainer() {
        return findViewById(R.id.root_view);
    }
}