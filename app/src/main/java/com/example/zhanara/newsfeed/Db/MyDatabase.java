package com.example.zhanara.newsfeed.Db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by zhanara on 02.10.17.
 */
@Database(entities = {News.class}, version = 3, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}