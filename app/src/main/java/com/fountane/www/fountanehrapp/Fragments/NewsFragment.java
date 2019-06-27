package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.Adapters.allNewsRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.newsRecyclerAdapter;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.News;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    private RecyclerView allNewsRecycler;
    private allNewsRecyclerAdapter newsRecyclerAdapter;
    private List<News> newsList = new ArrayList<>();

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("News");

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        allNewsRecycler = view.findViewById(R.id.allNewsRecycler);
        newsRecyclerAdapter = new allNewsRecyclerAdapter(newsList);
        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        allNewsRecycler.setLayoutManager(newsLayoutManager);
        allNewsRecycler.setItemAnimator(new DefaultItemAnimator());
        allNewsRecycler.setAdapter(newsRecyclerAdapter);

        prepareNewsData();


        return view;

    }

    private void prepareNewsData() {
        News news1 = new News("Fountane Logo Launched","25","Founane,India","09:42 AM","www.image.com","Jan");
        newsList.add(news1);

        News news2 = new News("Fountane Logo Launched","25","Founane,India","09:42 AM","www.image.com","Jan");
        newsList.add(news2);

        News news3 = new News("Fountane Logo Launched","25","Founane,India","09:42 AM","www.image.com","Jan");
        newsList.add(news3);
    }

}
