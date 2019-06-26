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

import com.fountane.www.fountanehrapp.Adapters.grievanceRecyclerAdapter;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Grievances;
import com.fountane.www.fountanehrapp.models.News;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrievancesFragment extends Fragment {


    private RecyclerView grievanceRecycler;
    private grievanceRecyclerAdapter grievanceRecyclerAdapter;
    private List<Grievances> grievancesList = new ArrayList<>();


    public GrievancesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Grievances");

        View view = inflater.inflate(R.layout.fragment_grievances, container, false);
        grievanceRecycler = view.findViewById(R.id.grievanceRecycler);
        grievanceRecyclerAdapter = new grievanceRecyclerAdapter(grievancesList);

        RecyclerView.LayoutManager grievanceLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        grievanceRecycler.setLayoutManager(grievanceLayoutManager);
        grievanceRecycler.setItemAnimator(new DefaultItemAnimator());
        grievanceRecycler.setAdapter(grievanceRecyclerAdapter);
        grievanceRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        prepareGrievancesData();
        return view;
    }

    private void prepareGrievancesData() {

        Grievances grievance1 = new Grievances("25","Jan","09:42 AM","Pending","TT Table Not Found iirihfiohf irhferiofheriohr ifhre ih igohreioh reigh irgh ern4tk4rl4lj3l4ji4tji4hji4 ipirotfu4h orf eriofheriofh reoighre oiher reoh eriogh reigheriogh eroihg oerihg reoighreigheroi gherio");
        grievancesList.add(grievance1);

        Grievances grievance2 = new Grievances("25","Jan","09:42 AM","Resolved","PlayStation is not working");
        grievancesList.add(grievance2);
    }

}
