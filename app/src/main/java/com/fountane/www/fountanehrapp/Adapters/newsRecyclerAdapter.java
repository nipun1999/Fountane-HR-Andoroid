package com.fountane.www.fountanehrapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.module.AppGlideModule;


import com.bumptech.glide.request.RequestOptions;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.News;

import java.util.List;

public class newsRecyclerAdapter extends RecyclerView.Adapter<newsRecyclerAdapter.MyViewHolder> {
    private List<News> newsList;
    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,date,month,time,publishedBy;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleTxtView);
            date = view.findViewById(R.id.dateTxtView);
            month = view.findViewById(R.id.monthTxtView);
            time = view.findViewById(R.id.timeTxtView);
            publishedBy = view.findViewById(R.id.locationTxtView);
            imageView = view.findViewById(R.id.newsImageView);


        }
    }

    public newsRecyclerAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_only_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.time.setText(news.getTime());
        holder.month.setText(news.getMonth());
        holder.publishedBy.setText(news.getPublishedby());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_1)
                .fitCenter()
                .override(250,161);
        Glide.with(itemView.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(news.getImageUrl())
                .into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
