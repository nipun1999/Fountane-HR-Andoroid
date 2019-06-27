package com.fountane.www.fountanehrapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.News;

import java.util.List;

public class allNewsRecyclerAdapter extends RecyclerView.Adapter<allNewsRecyclerAdapter.MyViewHolder> {

    private List<News> newsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,date,month,time,publishedBy;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.allTitleTxtVew);
            date = view.findViewById(R.id.allDatetxtView);
            month = view.findViewById(R.id.allMonthTxtView);
            time = view.findViewById(R.id.allTimeTxtView);
            publishedBy = view.findViewById(R.id.allLocationTxtView);


        }
    }

    public allNewsRecyclerAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public allNewsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
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
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
