package com.example.zhanara.newsfeed.Db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by zhanara on 02.10.17.
 */

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert
    void insert(News newsItem);

    @Delete
    void delete(News newsItem);
}
