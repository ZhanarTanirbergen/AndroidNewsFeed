package com.example.zhanara.newsfeed.Fragments;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.zhanara.newsfeed.Adapters.NewsAdapter;
import com.example.zhanara.newsfeed.Db.MyDatabase;
import com.example.zhanara.newsfeed.Db.News;
import com.example.zhanara.newsfeed.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class AddFragment extends Fragment implements View.OnClickListener {

    Button btnAdd, btnDeleteAll;
    EditText etTitle, etContent;
    MyDatabase db;
    int[] imgIds = {R.drawable.business, R.drawable.docs, R.drawable.educ, R.drawable.science, R.drawable.travel, R.drawable.sport, R.drawable.health, R.drawable.ornament};

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
//    String[] imgUrls = {"@drawable/ornament", "@drawable/business", "@drawable/docs", "@drawable/educ", "@drawable/health", "@drawable/scince", "@drawable/sport", "@drawable/travel"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, "newsfeed")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view       = inflater.inflate(R.layout.addnewitem, container, false);
        btnAdd          = view.findViewById(R.id.btnAdd);
        btnDeleteAll    = view.findViewById(R.id.btndeleteall);
        etTitle         = view.findViewById(R.id.title);
        etContent       = view.findViewById(R.id.content);
        btnAdd.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        Context context = getActivity().getApplicationContext();
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        Toast toast;

        switch(v.getId()) {

            case R.id.btnAdd:
                String name = etTitle.getText().toString();
                String content = etContent.getText().toString();

                new InsertAsync().execute(name, content);
                text = "Successfully added";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                etTitle.setText("");
                etContent.setText("");
                break;

            case R.id.btndeleteall:
                new DeleteAsync().execute();
                text = "All news were deleted";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
        }
    }

    private class InsertAsync extends AsyncTask<String, String,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... values) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String pubDate = dateFormat.format(calendar.getTime());

            News item = new News();
            item.setTitle(values[0]);
            item.setContent(values[1]);
            item.setPubDate(pubDate);
            item.setImgUrl(getRandom(imgIds));

            db.newsDao().insert(item);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private class DeleteAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.newsDao().deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
