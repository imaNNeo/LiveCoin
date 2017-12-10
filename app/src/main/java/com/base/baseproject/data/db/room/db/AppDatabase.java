package com.base.baseproject.data.db.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@Database(entities = {com.base.baseproject.data.db.room.entity.SampleItemEntity.class},version = 3)
public abstract class AppDatabase extends RoomDatabase{
    public abstract com.base.baseproject.data.db.room.dao.SampleItemDao sampleItemDao();
}
