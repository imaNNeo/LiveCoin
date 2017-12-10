package com.base.baseproject.data.db.room;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.base.baseproject.data.db.room.db.AppDatabase;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class RoomDbHandler {
    private static RoomDbHandler mRoomDbHandler;
    public static RoomDbHandler getInstance(Context ctx){
        ctx = ctx.getApplicationContext();
        if(mRoomDbHandler ==null)
            mRoomDbHandler = new RoomDbHandler(ctx);

        return mRoomDbHandler;
    }


    AppDatabase db;
    Context mContext;
    private RoomDbHandler(Context ctx){
        mContext = ctx;
        db = Room.databaseBuilder(mContext,AppDatabase.class,"my-database").allowMainThreadQueries().build();
    }


    public AppDatabase getAppDatabase(){
        return db;
    }
}
