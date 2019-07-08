package com.fountane.www.fountanehrapp.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.Adapters.EventsTabAdapter;
import com.fountane.www.fountanehrapp.R;

public class FountaneFragment extends Fragment {

    private EventsTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fountane, container, false);
        getActivity().setTitle("Fountane");

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        adapter = new EventsTabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new DirectoryTab(), "Directory");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

