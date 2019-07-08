package com.fountane.www.fountanehrapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.DirectoryAdapter;
import com.fountane.www.fountanehrapp.ApiModels.EmployeeDetails;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.models.DirectoryList;
import com.fountane.www.fountanehrapp.models.peopleField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class DirectoryTab extends Fragment {

    public RecyclerView recyclerView;
    public DirectoryAdapter directoryAdapter;
    public List<DirectoryList> directoryList = new ArrayList<>();
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_directory, container, false);
        recyclerView = view.findViewById(R.id.recycler_view1);

        directoryAdapter = new DirectoryAdapter(directoryList, getActivity());
        RecyclerView.LayoutManager directoryLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(directoryLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(directoryAdapter);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);


        pd.show();
        retrofit2.Call<EmployeeDetails> call = ApiClient.getClient().getAllEmployees();
        call.enqueue(new Callback<EmployeeDetails>() {
            @Override
            public void onResponse(retrofit2.Call<EmployeeDetails> call, Response<EmployeeDetails> response) {
                pd.dismiss();
                if (response.code() == 200) {

                    if (response.body().getProfile().size() != 0) {
                        for (int i = 0; i < response.body().getProfile().size(); i++) {
                            DirectoryList directoryList1 = new DirectoryList();
                            directoryList1.setName(response.body().getProfile().get(i).getName());
                            directoryList1.setPosition(response.body().getProfile().get(i).getDesignation());
                            directoryList1.setImg(response.body().getProfile().get(i).getProfilePic());
                            directoryList1.setEmpCode(response.body().getProfile().get(i).getEmpCode());
                            directoryList.add(directoryList1);
                            directoryAdapter.notifyDataSetChanged();
                        }
                        Collections.sort(directoryList, new Comparator<DirectoryList>() {
                            @Override
                            public int compare(DirectoryList o1, DirectoryList o2) {
                                String s1 = o1.getName();
                                String s2 = o2.getName();
                                return s1.compareToIgnoreCase(s2);
                            }

                        });
                        directoryAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<EmployeeDetails> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


}

