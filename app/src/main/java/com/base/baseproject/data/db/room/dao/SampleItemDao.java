package com.base.baseproject.data.db.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@Dao
public interface SampleItemDao {

    @Query("SELECT * FROM sampleItem")
    List<com.base.baseproject.data.db.room.entity.SampleItemEntity> getAll();

    @Query("SELECT * FROM sampleItem WHERE id IN (:findingIds)")
    List<com.base.baseproject.data.db.room.entity.SampleItemEntity> loadAllByIds(int[] findingIds);

    @Query("SELECT * FROM sampleItem WHERE title LIKE :key")
    com.base.baseproject.data.db.room.entity.SampleItemEntity findByNane(String key);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(com.base.baseproject.data.db.room.entity.SampleItemEntity... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(com.base.baseproject.data.db.room.entity.SampleItemEntity user);

    @Delete
    void deleteUser(com.base.baseproject.data.db.room.entity.SampleItemEntity ue);
}
