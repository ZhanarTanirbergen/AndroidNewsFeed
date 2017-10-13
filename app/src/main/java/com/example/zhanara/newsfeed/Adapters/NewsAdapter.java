package com.example.zhanara.newsfeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhanara.newsfeed.Db.News;
import com.example.zhanara.newsfeed.R;
import com.example.zhanara.newsfeed.ShowNewsItemActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhanara on 06.10.17.
 */

public class NewsAdapter extends
        RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private List<News> newsList;

    public NewsAdapter(Context _mContext, List<News> _newsList){
        this.mContext = _mContext;
        this.newsList = _newsList;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, parent, false);
        return new ViewHolder(newsItemView, mContext);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        holder.setPosition(position);
        holder.title.setText(newsList.get(position).getTitle());
        holder.date.setText(newsList.get(position).getPublishedAt());
        String newsImage = newsList.get(position).getUrlToImage();
        Picasso.with(mContext).load(newsImage).into(holder.img);
//        holder.img.setBackgroundResource(newsList.get(position).getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, News news) {
        newsList.add(position, news);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(int position) {
        newsList.remove(position);
        notifyItemRemoved(position);
    }


    //View Holder class
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title, date;
        public ImageView img;
        int position;

        public ViewHolder(View view, final Context event) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            date = (TextView) view.findViewById(R.id.item_pubDate);
            img = (ImageView) view.findViewById(R.id.item_image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    News clickedItem = newsList.get(pos);
                    Intent intent = new Intent(event, ShowNewsItemActivity.class);
                    intent.putExtra("id", clickedItem.getId());
                    intent.putExtra("title", clickedItem.getTitle());
                    intent.putExtra("date", clickedItem.getPublishedAt());
                    intent.putExtra("content", clickedItem.getDescription());
                    intent.putExtra("imageUrl", clickedItem.getUrlToImage());
                    event.startActivity(intent);
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
