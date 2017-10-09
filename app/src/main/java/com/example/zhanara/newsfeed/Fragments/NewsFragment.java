package com.example.zhanara.newsfeed.Fragments;

/**
 * Created by zhanara on 02.10.17.
 */
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zhanara.newsfeed.Adapters.NewsAdapter;
import com.example.zhanara.newsfeed.Db.MyDatabase;
import com.example.zhanara.newsfeed.Db.News;
import com.example.zhanara.newsfeed.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsFragment extends Fragment {

    private RecyclerView rv;
    private ImageView img;
    private NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();
    MyDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, "newsfeed")
                .fallbackToDestructiveMigration()
                .build();
        new ShowNewsAsync().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfragment, container, false);
        rv = view.findViewById(R.id.rvNews);
        img = view.findViewById(R.id.item_image);
        return view;
    }

    private class ShowNewsAsync extends AsyncTask<Void, Void, List <News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List <News> doInBackground(Void... voids) {
            return db.newsDao().getAll();
        }

        @Override
        protected void onPostExecute(List <News> myList) {
            super.onPostExecute(myList);
            setToRecyclerView(myList);
        }
    }

    void setToRecyclerView(List <News> myNewsList) {
        newsList = myNewsList;
        adapter = new NewsAdapter(this.getContext(), newsList);
        rv.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }
}
