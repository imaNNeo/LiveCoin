package com.base.baseproject.data.db.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.base.baseproject.viewhelper.adapter.BaseRecyclerAdapter;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
@Entity(tableName = "sampleItem")
public class SampleItemEntity implements BaseRecyclerAdapter.IDiff{

    @PrimaryKey()
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @Override
    public boolean areContentsTheSame(Object thisType) {
        return false;
    }

    @Override
    public boolean areItemsTheSame(Object thisType) {
        return id == ((SampleItemEntity)thisType).id;
    }
}