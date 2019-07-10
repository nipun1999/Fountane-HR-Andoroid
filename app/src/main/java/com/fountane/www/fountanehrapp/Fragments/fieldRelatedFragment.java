package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.leaveRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.peopleFieldsRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.getPeopleDepartmentWise;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.models.Leaves;
import com.fountane.www.fountanehrapp.models.peopleField;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class fieldRelatedFragment extends Fragment {


    private List<peopleField> peopleList = new ArrayList<>();

    private RecyclerView fieldRecycler;
    private peopleFieldsRecyclerAdapter peopleRecyclerAdapter;
    private String field;
    private String department;
    private LinearLayout noImageLayout;
    public fieldRelatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_field_related, container, false);
        fieldRecycler = view.findViewById(R.id.fieldPeopleRecycler);

        Bundle arguments = getArguments();
        field = arguments.getString("field");
        department = arguments.getString("department");

        noImageLayout = view.findViewById(R.id.noImageLayout);

        peopleRecyclerAdapter = new peopleFieldsRecyclerAdapter(peopleList,getActivity(),field);
        fieldRecycler.setAdapter(peopleRecyclerAdapter);

        RecyclerView.LayoutManager leavesLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        fieldRecycler.setLayoutManager(leavesLayoutManager);
        fieldRecycler.setItemAnimator(new DefaultItemAnimator());
        fieldRecycler.setAdapter(peopleRecyclerAdapter);
        fieldRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        prepareData();

        return view;
    }

    private void prepareData() {


        retrofit2.Call<getPeopleDepartmentWise> call= ApiClient.getClient().getPeople(field,department);
        call.enqueue(new Callback<getPeopleDepartmentWise>() {
            @Override
            public void onResponse(retrofit2.Call<getPeopleDepartmentWise> call, Response<getPeopleDepartmentWise> response) {
                if(response.code()==200){

                    if(response.body().getProfile().size()!=0){
                        noImageLayout.setVisibility(View.GONE);
                        for(int i=0;i<response.body().getProfile().size();i++){
                            peopleField people = new peopleField();
                            people = new peopleField();
                            people.setEmpCode(response.body().getProfile().get(i).getEmpCode());
                            people.setName(response.body().getProfile().get(i).getName());
                            people.setImageLink(response.body().getProfile().get(i).getProfilePic());
                            peopleList.add(people);
                            peopleRecyclerAdapter.notifyDataSetChanged();
                        }


                    }else{
                        noImageLayout.setVisibility(View.VISIBLE);
                    }

                }else{
                    Toast.makeText(getActivity(), "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<getPeopleDepartmentWise> call, Throwable t) {
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });
//        peopleField people = new peopleField("Nipun","jefejf","90");
//        peopleList.add(people);
//
//        peopleField people1 = new peopleField("varun","jefejf","90");
//        peopleList.add(people1);
//
//        peopleField people2 = new peopleField("Bhavya","jefejf","90");
//        peopleList.add(people2);
//
//        peopleField people3 = new peopleField("Nipun","jefejf","90");
//        peopleList.add(people3);
//
//        peopleField people4 = new peopleField("varun","jefejf","90");
//        peopleList.add(people4);
//
//        peopleField people5 = new peopleField("Bhavya","jefejf","90");
//        peopleList.add(people5);
//
//        peopleField people6 = new peopleField("Nipun","jefejf","90");
//        peopleList.add(people6);
//
//        peopleField people8 = new peopleField("varun","jefejf","90");
//        peopleList.add(people8);


    }

}
