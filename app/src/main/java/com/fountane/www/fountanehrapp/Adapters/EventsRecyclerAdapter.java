package com.fountane.www.fountanehrapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.module.AppGlideModule;


import com.bumptech.glide.request.RequestOptions;
import com.fountane.www.fountanehrapp.Activities.EventsActivity;
import com.fountane.www.fountanehrapp.Activities.NewsActivity;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.News;
import com.google.common.net.InternetDomainName;

import java.util.List;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.MyViewHolder> {
    private List<News> newsList;
    private View itemView;
    public Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date,month,year;
        public ImageView imageView;
        public LinearLayout main_event;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.event_title);
            date = view.findViewById(R.id.date_title);
            month = view.findViewById(R.id.month_title);
            imageView = view.findViewById(R.id.event_img);
            main_event = view.findViewById(R.id.event);
        }
    }

    public EventsRecyclerAdapter(List<News> eventList, Context context) {
        this.newsList = eventList;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.month.setText(news.getMonth());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_1)
                .fitCenter()
                .override(250, 161);
        Glide.with(itemView.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(news.getImageUrl())
                .into(holder.imageView);

        holder.main_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, NewsActivity.class);
                i.putExtra("newsId", news.getId());
                i.putExtra("Headline", news.getTitle());
                i.putExtra("Data", news.getData());
                i.putExtra("Img", news.getImageUrl());
                i.putExtra("from","Events");
                mcontext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
