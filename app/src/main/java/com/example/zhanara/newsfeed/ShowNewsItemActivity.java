package com.example.zhanara.newsfeed;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhanara.newsfeed.Adapters.NewsAdapter;
import com.example.zhanara.newsfeed.Db.MyDatabase;
import com.example.zhanara.newsfeed.Db.News;
import com.example.zhanara.newsfeed.Fragments.AddFragment;
import com.example.zhanara.newsfeed.Fragments.NewsFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhanara on 06.10.17.
 */

public class ShowNewsItemActivity extends AppCompatActivity implements View.OnClickListener {

    private View v;
    private NewsAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    Intent intent;
    private Toolbar toolbar;
    private FloatingActionButton btnDelete;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shownewsitem);

        db        = Room.databaseBuilder(getApplicationContext(),
                    MyDatabase.class, "newsfeed")
                    .fallbackToDestructiveMigration()
                    .build();
        toolbar   = (Toolbar) findViewById(R.id.toolbar);
        btnDelete = (FloatingActionButton) findViewById(R.id.delete);
        btnDelete.setOnClickListener(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        v = getWindow().getDecorView().getRootView();
        intent = getIntent();

        TextView newsTitle  = (TextView)findViewById(R.id.title);
        TextView newsBody   = (TextView)findViewById(R.id.content);
        TextView newsDate   = (TextView)findViewById(R.id.date);
        ImageView newsImage = (ImageView)findViewById(R.id.image);
        newsTitle.setText(intent.getStringExtra("title"));
        newsBody.setText(intent.getStringExtra("content"));
        newsDate.setText(intent.getStringExtra("date"));
        newsImage.setBackgroundResource(intent.getIntExtra("imageId", 0));

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

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setMessage("Are you sure you want to delete this news item?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    new DeleteAsync(intent.getIntExtra("id", 0)).execute();

                    NewsFragment fragment = new NewsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment).commit();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };

    private class DeleteAsync extends AsyncTask<Void, Void, Void> {
        int id;

        public DeleteAsync(int id){
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... args) {

            db.newsDao().deleteById(id);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
