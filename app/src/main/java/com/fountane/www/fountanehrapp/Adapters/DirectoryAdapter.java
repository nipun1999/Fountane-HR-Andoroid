package com.fountane.www.fountanehrapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.module.AppGlideModule;


import com.bumptech.glide.request.RequestOptions;
import com.fountane.www.fountanehrapp.Activities.EventsActivity;
import com.fountane.www.fountanehrapp.Activities.NewsActivity;
import com.fountane.www.fountanehrapp.Activities.employeeDetailActivity;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.DirectoryList;
import com.fountane.www.fountanehrapp.models.News;
import com.google.common.net.InternetDomainName;

import java.util.List;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.MyViewHolder> {
    private List<DirectoryList> directoryLists;
    private View itemView;
    public Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, position;
        public ImageView imageView;
        public RelativeLayout relative;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            position = view.findViewById(R.id.position);
            imageView = view.findViewById(R.id.img);
            relative = view.findViewById(R.id.relative);
        }
    }

    public DirectoryAdapter(List<DirectoryList> directoryLists, Context context) {
        this.directoryLists = directoryLists;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_directory, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DirectoryList directoryList = directoryLists.get(position);
        holder.name.setText(directoryList.getName());
        holder.position.setText(directoryList.getPosition());

        Glide.with(itemView.getContext())
                .load(directoryList.getImg())
                .into(holder.imageView);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),employeeDetailActivity.class);
                intent.putExtra("empCode",directoryLists.get(position).getEmpCode());
                v.getContext().startActivity(intent);
            }
        });

//        holder.relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(mcontext, .class);
////                i.putExtra("newsId", news.getId());
////                i.putExtra("Headline", news.getTitle());
////                i.putExtra("Data", news.getData());
////                i.putExtra("Img", news.getImageUrl());
////                i.putExtra("from", "Events");
////                mcontext.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return directoryLists.size();
    }

}
