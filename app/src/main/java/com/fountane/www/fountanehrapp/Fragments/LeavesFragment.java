package com.fountane.www.fountanehrapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.grievanceRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.leaveRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.getLeavesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.models.Grievances;
import com.fountane.www.fountanehrapp.models.Leaves;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeavesFragment extends Fragment {

    private FloatingActionButton fBtn;
    private RecyclerView leavesRecycler;
    private leaveRecyclerAdapter leaveRecyclerAdapter;
    private List<Leaves> leavesList = new ArrayList<>();
    private TextView casualTxtView,sickTxtView,paidTxtView,optionalTxtView;
    private SessionManager sessionManager;
    private ProgressDialog pd;
    private Date mydate;
    private ImageView imageView;


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
        casualTxtView = view.findViewById(R.id.no_of_leaves_casual);
        sickTxtView = view.findViewById(R.id.no_of_leaves_sick);
        paidTxtView = view.findViewById(R.id.no_of_leaves_paid);
        optionalTxtView = view.findViewById(R.id.no_of_leaves_optional);
        sessionManager = new SessionManager(getActivity());
        imageView = view.findViewById(R.id.leaveImageView);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);


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
        getNoOfLeavesData();
        return view;

    }

    private void getNoOfLeavesData() {
        pd.show();
        Call<personalEmployeeProfileApiModel> call = ApiClient.getClient().personalProfile(sessionManager.getEMP_Code());
        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
            @Override
            public void onResponse(Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
                pd.hide();
                if(response.code()==200){
                    casualTxtView.setText(Integer.toString(response.body().getProfile().get(0).getCasualLeave()));
                    sickTxtView.setText(Integer.toString(response.body().getProfile().get(0).getSickLeave()));
                    optionalTxtView.setText(Integer.toString(response.body().getProfile().get(0).getOtherLeave()));
                    paidTxtView.setText(Integer.toString(response.body().getProfile().get(0).getPaidLeave()));
                }else{
                    Toast.makeText(getActivity(), "Some error occured in retrieving leaves data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<personalEmployeeProfileApiModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareLeavesData() {
        pd.show();
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Call<getLeavesApiModel> call = ApiClient.getClient().getLeaves(sessionManager.getEMP_Code());
        call.enqueue(new Callback<getLeavesApiModel>() {
            @Override
            public void onResponse(Call<getLeavesApiModel> call, Response<getLeavesApiModel> response) {
                pd.hide();
                if(response.code()==200){
                    if(response.body().getLeaves().size()!=0){
                        leavesList.clear();
                        for(int i=0;i<response.body().getLeaves().size();i++){
                            Leaves leave = new Leaves();
                            try {
                                mydate = df.parse(response.body().getLeaves().get(i).getCreatedAt());
                                String month = parseMonth(mydate.getMonth()+1);
                                leave.setDate(Integer.toString(mydate.getDate()));
                                Log.e("grievanced",Integer.toString(mydate.getDate()));
                                Log.e("grievancet",Long.toString(mydate.getTime()));
                                Log.e("grievancem",Integer.toString(mydate.getMonth()));
                                leave.setMonth(month);
                                leave.setStatus(response.body().getLeaves().get(i).getStatus());
                                if(response.body().getLeaves().get(i).getLeaveType().equals("sickLeave")){
                                    leave.setType("Sick Leave");
                                }else if(response.body().getLeaves().get(i).getLeaveType().equals("casualLeave")){
                                    leave.setType("Casual Leave");
                                }else if(response.body().getLeaves().get(i).getLeaveType().equals("otherLeave")){
                                    leave.setType("Other Leave");
                                }else if(response.body().getLeaves().get(i).getLeaveType().equals("paidLeave")){
                                    leave.setType("Paid Leave");
                                }

                                leave.setDetail(response.body().getLeaves().get(i).getFromDate()+" "+"To"+" "+response.body().getLeaves().get(i).getToDate());

                            } catch (ParseException e) {
                                leave.setDate("00");
                                leave.setMonth("00");
                                e.printStackTrace();
                            }
                            leavesList.add(leave);
                            leaveRecyclerAdapter.notifyDataSetChanged();
                        }
                    }else{
                        imageView.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<getLeavesApiModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });


//        Leaves leave1 = new Leaves("Casual Leave - 3 days","12th-14th March","26","Jan","09:42 am","pending");
//        leavesList.add(leave1);
//
//        Leaves leave2 = new Leaves("Sick Leave - 3 days","12th-14th March","26","Jan","09:42 am","Resolved");
//        leavesList.add(leave2);
    }

    private String parseMonth(int month) {
        if(month==1){
            return "JAN";
        }else if(month==2){
            return "FEB";
        }else if(month==3){
            return "MAR";
        }else if(month==4){
            return "APR";
        }else if(month==5){
            return "MAY";
        }else if(month==6){
            return "JUN";
        }else if(month==7){
            return "JUL";
        }else if (month==8){
            return "AUG";
        }else if(month==9){
            return "SEP";
        }else if(month==10){
            return "OCT";
        }else if(month==11){
            return "NOV";
        }else{
            return "DEC";
        }
    }

}
