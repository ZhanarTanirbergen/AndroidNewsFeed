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

public class AddFragment extends Fragment implements View.OnClickListener {

    Button btnAdd;
    EditText etTitle, etContent;
    MyDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                MyDatabase.class, "newsfeed")
                .build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addnewitem, container, false);
        btnAdd    = view.findViewById(R.id.btnAdd);
        etTitle   = view.findViewById(R.id.title);
        etContent = view.findViewById(R.id.content);
        btnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        String name = etTitle.getText().toString();
        String content = etContent.getText().toString();

        new InsertAsync().execute(name, content);
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        Context context = getActivity().getApplicationContext();
        CharSequence text = "Succesfully added";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        etTitle.setText("");
        etContent.setText("");
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

            db.newsDao().insert(item);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
