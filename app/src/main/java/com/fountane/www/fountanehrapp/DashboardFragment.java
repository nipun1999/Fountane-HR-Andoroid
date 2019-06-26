package com.fountane.www.fountanehrapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.newsRecyclerAdapter;
import com.fountane.www.fountanehrapp.models.News;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private RelativeLayout grievancesBtn,leavesBtn,payslipsBtn,documentsBtn;
    private RecyclerView newsRecycler,eventsRecycler;
    private newsRecyclerAdapter newsRecyclerAdapter,eventsRecyclerAdapter;
    private List<News> newsList = new ArrayList<>();
    private List<News> eventsList = new ArrayList<>();
    private FloatingActionButton checkInBtn;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        getActivity().setTitle("Dashboard");
        grievancesBtn = view.findViewById(R.id.grievancesLayout);
        leavesBtn = view.findViewById(R.id.leavesLayout);
        payslipsBtn = view.findViewById(R.id.payslipsLayout);
        documentsBtn = view.findViewById(R.id.documentsLayout);
        newsRecycler = view.findViewById(R.id.newsRecyclerView);
        eventsRecycler = view.findViewById(R.id.eventsRecyclerView);
        checkInBtn = view.findViewById(R.id.checkInBtn);

        newsRecyclerAdapter = new newsRecyclerAdapter(newsList);
        eventsRecyclerAdapter = new newsRecyclerAdapter(eventsList);

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager eventsLayoutmanager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

        newsRecycler.setLayoutManager(newsLayoutManager);
        newsRecycler.setItemAnimator(new DefaultItemAnimator());
        newsRecycler.setAdapter(newsRecyclerAdapter);

        eventsRecycler.setLayoutManager(eventsLayoutmanager);
        eventsRecycler.setItemAnimator(new DefaultItemAnimator());
        eventsRecycler.setAdapter(eventsRecyclerAdapter);

        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not so early dude", Toast.LENGTH_SHORT).show();
            }
        });

        grievancesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,new GrievancesFragment()).addToBackStack("tag").commit();

            }
        });

        leavesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.content,new LeavesFragment()).addToBackStack("tag").commit();
            }
        });
        payslipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,new PayslipsAndOthersFragment()).addToBackStack("tag").commit();
            }
        });
        documentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,new DocumentListFragment()).addToBackStack("tag").commit();
            }
        });

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

        News events1 = new News("Outdoor Sports event, Cricket and Volleyball","25","Founane,India","09:42 AM","www.image.com","Jan");
        eventsList.add(events1);

        News events2 = new News("Outdoor Sports event, Cricket and Volleyball","25","Founane,India","09:42 AM","www.image.com","Jan");
        eventsList.add(events2);

        News events3 = new News("Outdoor Sports event, Cricket and Volleyball","25","Founane,India","09:42 AM","www.image.com","Jan");
        eventsList.add(events3);



    }

}
