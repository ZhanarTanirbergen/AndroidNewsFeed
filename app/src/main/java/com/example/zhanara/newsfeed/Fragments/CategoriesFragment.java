package com.example.zhanara.newsfeed.Fragments;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhanara.newsfeed.Db.MyDatabase;
import com.example.zhanara.newsfeed.R;

/**
 * Created by zhanara on 09.10.17.
 */

public class CategoriesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categoriesfragment, container, false);
        return view;
    }
}
