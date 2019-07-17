package com.fountane.www.fountanehrapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.Adapters.EventsRecyclerAdapter;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Utils.AppConstants;
import com.fountane.www.fountanehrapp.models.News;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class UpcomingEventsTab extends Fragment {
    public RecyclerView recyclerView;
    private EventsRecyclerAdapter eventsRecyclerAdapter;
    public static List<News> events, ongoing = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_events, container, false);
        events = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view);
        events = AppConstants.EVENTS;
        final DateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date strDate = null;
        ongoing.clear();
        for (int i = 0; i < events.size(); i++) {
            try {
                strDate = sdf.parse(events.get(i).getDate_gmt());
                if (new Date().before(strDate)) {
                    ongoing.add(events.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(ongoing, new Comparator<News>() {
            @Override
            public int compare(News lhs, News rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });

        eventsRecyclerAdapter = new EventsRecyclerAdapter(ongoing, getActivity());
        RecyclerView.LayoutManager eventsLayoutmanager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(eventsLayoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventsRecyclerAdapter);
        return view;
    }
}
