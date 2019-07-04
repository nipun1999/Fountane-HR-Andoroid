package com.fountane.www.fountanehrapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.News;

import java.util.List;

public class allNewsRecyclerAdapter extends RecyclerView.Adapter<allNewsRecyclerAdapter.MyViewHolder> {

    private List<News> newsList;
    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,date,month,time,publishedBy;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.allTitleTxtVew);
            date = view.findViewById(R.id.allDatetxtView);
            month = view.findViewById(R.id.allMonthTxtView);
            time = view.findViewById(R.id.allTimeTxtView);
            imageView = view.findViewById(R.id.allnewsImageView);
            publishedBy = view.findViewById(R.id.allLocationTxtView);



        }
    }

    public allNewsRecyclerAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public allNewsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_event_card, parent, false);
        return new allNewsRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(allNewsRecyclerAdapter.MyViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.time.setText(news.getTime());
        holder.month.setText(news.getMonth());
        holder.publishedBy.setText(news.getPublishedby());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_1)
                .fitCenter();
        Glide.with(itemView.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(news.getImageUrl())
                .into(holder.imageView);

        Log.e("image",news.getImageUrl());

    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
