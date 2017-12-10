package com.base.baseproject.data.db;

import android.content.Context;

import com.base.baseproject.data.db.room.RoomDbHandler;
import com.base.baseproject.data.db.room.entity.SampleItemEntity;
import com.base.baseproject.di.annotation.ApplicationContext;

import java.util.List;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

public class AppDbHelper extends DbHelper {

    Context mContext;
    public AppDbHelper(@ApplicationContext Context ctx){
        ctx = ctx.getApplicationContext();
        mContext = ctx;
    }

    @Override
    public List<SampleItemEntity> getAllSampleItems() {
        return RoomDbHandler.getInstance(mContext).getAppDatabase().sampleItemDao().getAll();
    }

    @Override
    public List<SampleItemEntity> getSampleItemsByIds(int[] findingIds) {
        return RoomDbHandler.getInstance(mContext).getAppDatabase().sampleItemDao().loadAllByIds(findingIds);
    }

    @Override
    public SampleItemEntity findSampleItemByName(String name) {
        return RoomDbHandler.getInstance(mContext).getAppDatabase().sampleItemDao().findByNane(name);
    }

    @Override
    public void insertSampleItems(SampleItemEntity... entities) {
        RoomDbHandler.getInstance(mContext).getAppDatabase().sampleItemDao().insertAll(entities);
    }

    @Override
    public void insertSampleItem(SampleItemEntity entity) {
        RoomDbHandler.getInstance(mContext).getAppDatabase().sampleItemDao().insert(entity);
    }

    @Override
    public void deleteSampleItem(SampleItemEntity entity) {
        RoomDbHandler.getInstance(mContext).getAppDatabase().sampleItemDao().deleteUser(entity);
    }
}
