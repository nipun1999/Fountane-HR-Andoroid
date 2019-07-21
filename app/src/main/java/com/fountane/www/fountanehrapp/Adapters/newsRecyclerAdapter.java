package com.fountane.www.fountanehrapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
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
import com.fountane.www.fountanehrapp.Activities.NewsActivity;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.News;
import com.google.common.net.InternetDomainName;

import java.util.List;

public class newsRecyclerAdapter extends RecyclerView.Adapter<newsRecyclerAdapter.MyViewHolder> {
    private List<News> newsList;
    private View itemView;
    public Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, month, time, publishedBy;
        public ImageView imageView;
        public CardView card;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleTxtView);
            date = view.findViewById(R.id.dateTxtView);
            month = view.findViewById(R.id.monthTxtView);
            time = view.findViewById(R.id.timeTxtView);
            publishedBy = view.findViewById(R.id.locationTxtView);
            imageView = view.findViewById(R.id.newsImageView);
            card = view.findViewById(R.id.cardView_collapse);

        }
    }

    public newsRecyclerAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_only_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.time.setText(news.getTime());
        holder.month.setText(news.getMonth());
        holder.publishedBy.setText(news.getPublishedby());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_1)
                .fitCenter()
                .override(250, 161);

        if (position % 4 == 0) {
            holder.imageView.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_hr_card_shape_1));
        }
        if (position % 4 == 1) {
            holder.imageView.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_hr_card_shape_2));
        }
        if (position % 4 == 2) {
            holder.imageView.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_hr_card_shape_3));
        }
        if (position % 4 == 3) {
            holder.imageView.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_hr_card_shape_4));
        }
//        Glide.with(itemView.getContext())
//                .applyDefaultRequestOptions(requestOptions)
//                .load(news.getImageUrl())
//                .into(holder.imageView);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, NewsActivity.class);
                i.putExtra("newsId", news.getId());
                i.putExtra("Headline", news.getTitle());
                i.putExtra("Data", news.getData());
                i.putExtra("Img", news.getImageUrl());
                i.putExtra("from","News");
                mcontext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
