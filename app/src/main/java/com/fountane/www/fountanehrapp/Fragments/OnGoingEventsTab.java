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

public class OnGoingEventsTab extends Fragment {

    public RecyclerView recyclerView;
    private EventsRecyclerAdapter eventsRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoing_events, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        eventsRecyclerAdapter = new EventsRecyclerAdapter(AppConstants.EVENTS, getActivity());
        RecyclerView.LayoutManager eventsLayoutmanager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(eventsLayoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventsRecyclerAdapter);
        return view;
    }
}
