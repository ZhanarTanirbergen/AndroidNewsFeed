package com.example.zhanara.newsfeed;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.zhanara.newsfeed.Db.MyDatabase;
import com.example.zhanara.newsfeed.Fragments.AddFragment;
import com.example.zhanara.newsfeed.Fragments.NewsFragment;
import com.example.zhanara.newsfeed.Fragments.CategoriesFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar   toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new CategoriesFragment(), "Categories");
        adapter.addFragment(new AddFragment(), "Add new item");
        viewPager.setAdapter(adapter);
    }

}
