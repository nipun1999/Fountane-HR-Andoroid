package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.Adapters.grievanceRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.leaveRecyclerAdapter;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Grievances;
import com.fountane.www.fountanehrapp.models.Leaves;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeavesFragment extends Fragment {

    private FloatingActionButton fBtn;
    private RecyclerView leavesRecycler;
    private leaveRecyclerAdapter leaveRecyclerAdapter;
    private List<Leaves> leavesList = new ArrayList<>();


    public LeavesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaves, container, false);

        getActivity().setTitle("Leaves");

        leavesRecycler = view.findViewById(R.id.leavesRecycler);
        leaveRecyclerAdapter = new leaveRecyclerAdapter(leavesList);

        RecyclerView.LayoutManager leavesLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        leavesRecycler.setLayoutManager(leavesLayoutManager);
        leavesRecycler.setItemAnimator(new DefaultItemAnimator());
        leavesRecycler.setAdapter(leaveRecyclerAdapter);
        leavesRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        fBtn = view.findViewById(R.id.newLeave);
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,new NewLeave()).addToBackStack("tag").commit();
            }
        });

        prepareLeavesData();

        return view;

    }

    private void prepareLeavesData() {
        Leaves leave1 = new Leaves("Casual Leave - 3 days","12th-14th March","26","Jan","09:42 am","pending");
        leavesList.add(leave1);

        Leaves leave2 = new Leaves("Sick Leave - 3 days","12th-14th March","26","Jan","09:42 am","Resolved");
        leavesList.add(leave2);
    }

}
