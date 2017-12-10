package com.base.baseproject.viewhelper.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.viewhelper.widget.AppTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class DialogMessage extends AppDialog {

    View rlPositive;
    @BindView(R.id.tv_title)
    AppTextView tvTitle;
    @BindView(R.id.tv_message)
    AppTextView tvMessage;
    @BindView(R.id.tv_positiveText)
    AppTextView tvPositive;

    OnDialogButtonClickListener mListener;


    Unbinder mUnbinder;

    public DialogMessage(@NonNull Context context,String title, String message, String positiveText, final OnDialogButtonClickListener listener) {
        super(context);

        setContentView(R.layout.dialog_message);

        mListener = listener;

        mUnbinder = ButterKnife.bind(this);

        tvPositive.setText(positiveText);

        if(TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else{
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        if(!TextUtils.isEmpty(message)){
            tvMessage.setText(message);
        }
    }


    @OnClick(R.id.rl_positive)
    void onPositiveClicked(){
        dismiss();
        if(mListener!=null) mListener.onPositive(DialogMessage.this);
    }

    @Override
    public View getViewContainer() {
        return findViewById(R.id.root_view);
    }


    public interface  OnDialogButtonClickListener{
        void onPositive(AppDialog dialog);
    }
}