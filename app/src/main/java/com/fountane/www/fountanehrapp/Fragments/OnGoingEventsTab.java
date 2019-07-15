package com.fountane.www.fountanehrapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OnGoingEventsTab extends Fragment {

    public RecyclerView recyclerView;
    private EventsRecyclerAdapter eventsRecyclerAdapter;
    public static List<News> events, ongoing = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoing_events, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        events = AppConstants.EVENTS;
        final DateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date strDate = null;
        ongoing.clear();
        for (int i = 0; i < events.size(); i++) {
            try {
                strDate = sdf.parse(events.get(i).getDate_gmt());

                if ((strDate.getTime() - System.currentTimeMillis()) < 24) {
                    ongoing.add(events.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        eventsRecyclerAdapter = new EventsRecyclerAdapter(ongoing, getActivity());
        RecyclerView.LayoutManager eventsLayoutmanager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(eventsLayoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventsRecyclerAdapter);
        return view;
    }
}
