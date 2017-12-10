package com.base.baseproject.data.db;

import com.base.baseproject.data.db.room.entity.SampleItemEntity;

import java.util.List;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public abstract class DbHelper {
    public abstract List<SampleItemEntity> getAllSampleItems();
    public abstract List<SampleItemEntity> getSampleItemsByIds(int[] findingIds);
    public abstract SampleItemEntity findSampleItemByName(String name);
    public abstract void insertSampleItems(SampleItemEntity... entities);
    public abstract void insertSampleItem(SampleItemEntity entity);
    public abstract void deleteSampleItem(SampleItemEntity entity);
}