package com.example.zhanara.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.zhanara.newsfeed.Adapters.NewsAdapter;
import com.example.zhanara.newsfeed.Db.News;

/**
 * Created by zhanara on 06.10.17.
 */

public class ShowNewsItemActivity extends AppCompatActivity {

    private View v;
    private NewsAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    Intent intent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shownewsitem);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        v = getWindow().getDecorView().getRootView();
        intent = getIntent();

        TextView newsTitle = (TextView)findViewById(R.id.title);
        TextView newsBody = (TextView)findViewById(R.id.content);
        TextView newsDate = (TextView)findViewById(R.id.date);
        newsTitle.setText(intent.getStringExtra("title"));
        newsBody.setText(intent.getStringExtra("content"));
        newsDate.setText(intent.getStringExtra("date"));

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("News");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
