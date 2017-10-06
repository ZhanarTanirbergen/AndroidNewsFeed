package com.example.zhanara.newsfeed.Db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhanara on 02.10.17.
 */
 @Entity (tableName = "news")
public class News implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "pub_date")
    private String pubDate;

    @ColumnInfo(name = "img_url")
    private String imgUrl;

    @ColumnInfo(name = "content")
    private String content;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPubDate(String date) {
        this.pubDate = date;
    }

    public String  getPubDate() {
       return pubDate;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

//    private static int lastContactId = 0;
//
//    public static ArrayList<News> createContactsList(int numContacts) {
//        ArrayList<News> newsArrayList = new ArrayList<News>();
//
//        for (int i = 1; i <= numContacts; i++) {
//            newsArrayList.add(new News());
//        }
//
//        return newsArrayList;
//    }

}
