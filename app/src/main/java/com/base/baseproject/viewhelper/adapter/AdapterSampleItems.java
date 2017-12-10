package com.base.baseproject.viewhelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.base.baseproject.R;
import com.base.baseproject.data.db.room.entity.SampleItemEntity;
import com.base.baseproject.listeners.OnAdapterViewClickedListener;
import com.base.baseproject.viewhelper.widget.AppTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Programmer Iman Khoshabi
 * iman.neofight@gmail.com
 */
public class AdapterSampleItems extends BaseRecyclerAdapter<AdapterSampleItems.ViewHolderString,SampleItemEntity>{
    Context mContext;
    OnAdapterViewClickedListener mListener;

    public AdapterSampleItems(Context ctx){
        mContext = ctx;
    }

    public void setListener(OnAdapterViewClickedListener listener){
        mListener = listener;
    }

    @Override
    public ViewHolderString onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.string_item_layout,parent,false);
        return new ViewHolderString(v,mListener);
    }
    @Override
    public void onBindViewHolder(ViewHolderString holder, final int position) {
        holder.tvTitle.setText(mItems.get(position).title);
    }

    public static final class ViewHolderString extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        public AppTextView tvTitle;
        @BindView(R.id.rl_container)
        public RelativeLayout rlContainer;
        OnAdapterViewClickedListener mListener;
        public ViewHolderString(View itemView,OnAdapterViewClickedListener listener){
            super(itemView);
            ButterKnife.bind(this,itemView);
            mListener = listener;
        }

        @OnClick(R.id.rl_container)
        void onClicke(){
            if(mListener!=null) mListener.onAdapterViewClicked(rlContainer,this,getAdapterPosition());
        }
    }

}