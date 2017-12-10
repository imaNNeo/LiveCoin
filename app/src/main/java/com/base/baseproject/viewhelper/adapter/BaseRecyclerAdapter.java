package com.base.baseproject.viewhelper.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class BaseRecyclerAdapter
        <V extends RecyclerView.ViewHolder, T extends BaseRecyclerAdapter.IDiff>
        extends RecyclerView.Adapter<V>{

    List<T> mItems;

    public BaseRecyclerAdapter(){
        mItems = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mItems!=null?mItems.size():0;
    }

    public void addItems(List<T> newItems){
        List<T> currList = new ArrayList<>(mItems);
        currList.addAll(newItems);
        resetItems(currList);
    }
    public T getItem(int pos){
        return mItems.get(pos);
    }
    public void addItemToStart(T item) {
        addItemTo(0,item);
        notifyItemInserted(0);
    }
    public void addItemToEnd(T item) {
        addItemTo(mItems.size(),item);
    }
    public void addItemTo(int pos,T item){
        mItems.add(pos,item);
        notifyItemInserted(pos);
    }
    public void resetItems(List<T> items) {
        if(items==null)return;

        List<T> oldList = mItems;
        List<T> newLIst = items;

        BaseRecyclerAdapter.BaseDiffCallback diffCallback = new BaseDiffCallback<>(oldList,newLIst);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback);

        mItems.clear();
        mItems.addAll(newLIst);
        result.dispatchUpdatesTo(this);
    }
    public void removeItem(T t) {
        if(t==null)return;

        for(int i=0;i<mItems.size();i++)
            if(mItems.get(i).areItemsTheSame(t)) {
                removeItem(i);
                break;
            }
    }
    public void removeItem(int pos) {
        if(pos<0 || pos > getItemCount())return;
        mItems.remove(pos);
        notifyItemRemoved(pos);
    }
    public boolean isItemExists(T t) {
        if(t==null)return false;

        for(int i=0;i<mItems.size();i++)
            if(mItems.get(i).areItemsTheSame(t)) {
                return true;
            }

        return false;
    }
    public ArrayList<T> getAllItems(){
        return new ArrayList<>(mItems);
    }
    public int getPos(T item){
        for(int i=0;i<mItems.size();i++){
            if(mItems.get(i).areItemsTheSame(item))
                return i;
        }

        return -1;
    }
    public void refreshItem(int pos,T item){
        mItems.set(pos,item);
        notifyItemChanged(pos);
    }
    public boolean isEmpty(){
        return mItems.size()==0;
    }

    private ArrayList<RecyclerView.ViewHolder> getVisibleViewHolders(RecyclerView rvParent){
        ArrayList<RecyclerView.ViewHolder> viewHolders = new ArrayList<>();
        if(rvParent==null || rvParent.getLayoutManager()==null)
            return viewHolders;

        if(rvParent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rvParent.getLayoutManager();
            final int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            final int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; ++i) {
                viewHolders.add(rvParent.findViewHolderForAdapterPosition(i));
            }
        }

        return viewHolders;
    }


    public class BaseDiffCallback <T extends IDiff> extends DiffUtil.Callback{

        List<T> mOldList;
        List<T> mNewList;

        public BaseDiffCallback(List<T> oldShopProductsList,
                                List<T> newShopProductsList) {
            this.mOldList = oldShopProductsList;
            this.mNewList = newShopProductsList;
        }

        @Override
        public int getOldListSize(){
            return mOldList.size();
        }

        @Override
        public int getNewListSize(){
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            T o = mOldList.get(oldItemPosition);
            T n = mNewList.get(newItemPosition);
            boolean b = o.areItemsTheSame(n);
            return b;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            T o = mOldList.get(oldItemPosition);
            T n = mNewList.get(newItemPosition);
            boolean b = o.areContentsTheSame(n);
            return b;
        }
    }

    public interface IDiff {
        boolean areContentsTheSame(Object thisType);
        boolean areItemsTheSame(Object thisType);
    }
}