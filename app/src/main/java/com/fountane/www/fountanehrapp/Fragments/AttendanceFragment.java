package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.Adapters.attendanceRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.leaveRecyclerAdapter;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Attendance;
import com.fountane.www.fountanehrapp.models.Leaves;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {

    private RecyclerView attendanceRecycler;
    private attendanceRecyclerAdapter attendanceRecyclerAdapter;
    private List<Attendance> attendanceList = new ArrayList<>();

    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Attendance");


        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        attendanceRecycler = view.findViewById(R.id.attendanceRecyclerView);
        attendanceRecyclerAdapter = new attendanceRecyclerAdapter(attendanceList);

        RecyclerView.LayoutManager leavesLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        attendanceRecycler.setLayoutManager(leavesLayoutManager);
        attendanceRecycler.setItemAnimator(new DefaultItemAnimator());
        attendanceRecycler.setAdapter(attendanceRecyclerAdapter);
        attendanceRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        prepareAttendanceData();


        return view;
    }

    private void prepareAttendanceData() {
        Attendance attendance1 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
        attendanceList.add(attendance1);

        Attendance attendance2 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
        attendanceList.add(attendance2);

        Attendance attendance3 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
        attendanceList.add(attendance3);

        Attendance attendance4 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
        attendanceList.add(attendance4);

        Attendance attendance5 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
        attendanceList.add(attendance5);

        Attendance attendance6 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
        attendanceList.add(attendance6);

    }

}
