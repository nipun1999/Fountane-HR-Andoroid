package com.fountane.www.fountanehrapp.Fragments;


import android.app.ProgressDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Activities.SignInActivity;
import com.fountane.www.fountanehrapp.Adapters.newsRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.CreateAttendanceApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getEventsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getNewsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.ApiModels.updateAttendanceApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.models.News;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private RelativeLayout grievancesBtn,leavesBtn,payslipsBtn,documentsBtn;
    private RecyclerView newsRecycler,eventsRecycler;
    private newsRecyclerAdapter newsRecyclerAdapter,eventsRecyclerAdapter;
    private TextView newsViewAll;
    private List<News> newsList = new ArrayList<>();
    private List<News> eventsList = new ArrayList<>();
    private ImageView checkInBtn;
    private SessionManager sessionManager;
    private String date;
    private ProgressDialog pd;
    private Date mydate;

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
        newsViewAll = view.findViewById(R.id.newsViewAll);

        sessionManager=new SessionManager(getActivity());

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);

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

        Log.e("attendance",Boolean.toString(sessionManager.getAttendanceStatus()));
        getAttendanceStatus();






        newsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,new NewsFragment()).addToBackStack("tag").commit();
            }
        });

        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!sessionManager.getAttendanceStatus()){
                    pd.show();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    }
                    Date currentTime = Calendar.getInstance().getTime();
                    Log.e("date",date);
                    Log.e("date",currentTime.toString());
                    Map<String,String> map = new HashMap<>();
                    map.put("empCode",sessionManager.getEMP_Code());
                    map.put("date",date);
                    map.put("checkIn",date+" "+currentTime.getHours()+":"+currentTime.getMinutes()+":"+currentTime.getSeconds());

                    retrofit2.Call<CreateAttendanceApiModel> call = ApiClient.getClient().createAttendance(map);
                    call.enqueue(new Callback<CreateAttendanceApiModel>() {
                        @Override
                        public void onResponse(retrofit2.Call<CreateAttendanceApiModel> call, Response<CreateAttendanceApiModel> response) {
                            pd.hide();
                            if(response.code()==200){
                                sessionManager.setATTENDANCE_ID(response.body().getAttendanceobj().getAttendanceId());
                                sessionManager.setATTENDANCE_STATUS(true);
                                checkInBtn.setImageResource(R.drawable.ic_check_out);
                                Toast.makeText(getActivity(), "Checked In Succesfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), "Failed to check in", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<CreateAttendanceApiModel> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(getActivity(), "Some error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{

                    Map<String,String> map = new HashMap<>();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    }
                    Date currentTime = Calendar.getInstance().getTime();
                    Log.e("date",date);
                    Log.e("date",currentTime.toString());

                    map.put("attendanceId",Integer.toString(sessionManager.getATTENDANCE_ID()));
                    map.put("checkOut",date+" "+currentTime.getHours()+":"+currentTime.getMinutes()+":"+currentTime.getSeconds());
                    Log.e("attendance",date+" "+currentTime.getHours()+":"+currentTime.getMinutes()+":"+currentTime.getSeconds());
                    pd.show();
                    retrofit2.Call<updateAttendanceApiModel> call = ApiClient.getClient().updateAttendance(map);
                    call.enqueue(new Callback<updateAttendanceApiModel>() {
                        @Override
                        public void onResponse(retrofit2.Call<updateAttendanceApiModel> call, Response<updateAttendanceApiModel> response) {
                            pd.hide();
                            if(response.code()==200){
                                sessionManager.setATTENDANCE_STATUS(false);
                                checkInBtn.setImageResource(R.drawable.ic_check_in);
                                Toast.makeText(getActivity(), "Checked Out Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.e("attendance",Integer.toString(response.code()));
                                Log.e("attendance",response.message().toString());
                                Toast.makeText(getActivity(), "Some error", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(retrofit2.Call<updateAttendanceApiModel> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


//                Toast.makeText(getActivity(), "Not so early dude", Toast.LENGTH_SHORT).show();
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

        getNews();

        getEvents();

        checkAttendanceStatus();

        return view;





    }

    private void getEvents() {

        final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        pd.show();
        eventsList.clear();
        retrofit2.Call<getEventsApiModel> call = ApiClient.getClient().getEvents();
        call.enqueue(new Callback<getEventsApiModel>() {
            @Override
            public void onResponse(retrofit2.Call<getEventsApiModel> call, Response<getEventsApiModel> response) {
                pd.hide();
                if(response.code()==200) {
                    for (int i = 0; i < response.body().getEvents().size(); i++) {
                        News news = new News();
                        try {
                            mydate = df.parse(response.body().getEvents().get(i).getEventDate());
                            String month = parseMonth(mydate.getMonth());
                            news.setDate(Integer.toString(mydate.getDate()));
                            news.setMonth(month);
                            news.setTime(Integer.toString(mydate.getHours()) + ":" + Integer.toString(mydate.getMinutes()));
                        } catch (ParseException e) {
                            news.setDate("00");
                            news.setTime("00");
                            news.setMonth("00");
                            e.printStackTrace();
                        }
                        news.setTitle(response.body().getEvents().get(i).getName());
                        news.setPublishedby(response.body().getEvents().get(i).getEventVenue());
                        eventsList.add(news);
                    }
                    eventsRecyclerAdapter.notifyDataSetChanged();
                }else{
                    pd.hide();
                    Toast.makeText(getActivity(), "Some Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<getEventsApiModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getNews() {
        final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        pd.show();
        newsList.clear();
        retrofit2.Call<getNewsApiModel> call = ApiClient.getClient().getNews();
        call.enqueue(new Callback<getNewsApiModel>() {
            @Override
            public void onResponse(retrofit2.Call<getNewsApiModel> call, Response<getNewsApiModel> response) {
                pd.hide();
                if(response.code()==200) {
                    for (int i = 0; i < response.body().getNewsobj().size(); i++) {
                        News news = new News();
                        try {
                            mydate = df.parse(response.body().getNewsobj().get(i).getDate());
                            String month = parseMonth(mydate.getMonth());
                            news.setDate(Integer.toString(mydate.getDate()));
                            news.setMonth(month);
                            news.setTime(Integer.toString(mydate.getHours()) + ":" + Integer.toString(mydate.getMinutes()));
                        } catch (ParseException e) {
                            news.setDate("00");
                            news.setTime("00");
                            news.setMonth("00");
                            e.printStackTrace();
                        }
                        news.setTitle(response.body().getNewsobj().get(i).getTitle());
                        news.setPublishedby(response.body().getNewsobj().get(i).getVenue());
                        newsList.add(news);
                    }
                    newsRecyclerAdapter.notifyDataSetChanged();
                }else{
                    pd.hide();
                    Toast.makeText(getActivity(), "Some Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<getNewsApiModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkAttendanceStatus() {
        Log.e("dashboardAttendance","inside");
//        Log.e("dashboard",Boolean.toString(sessionManager.getAttendanceStatus()));
        if(sessionManager.getAttendanceStatus()){
            checkInBtn.setImageResource(R.drawable.ic_check_out);
        }else{
            checkInBtn.setImageResource(R.drawable.ic_check_in);
        }
    }

    private void getAttendanceStatus() {

        retrofit2.Call<personalEmployeeProfileApiModel> call = ApiClient.getClient().personalProfile(sessionManager.getEMP_Code());
        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
            @Override
            public void onResponse(retrofit2.Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
                if(response.code()==200){
                    Boolean status = response.body().getProfile().get(0).getStatus();
                    Log.e("dashboardatttendance",Boolean.toString(status));
                    sessionManager.setATTENDANCE_STATUS(status);
                    sessionManager.setATTENDANCE_ID(response.body().getProfile().get(0).getAttendanceId());
                    Log.e("atttendance",Integer.toString(response.body().getProfile().get(0).getAttendanceId()));
                    checkAttendanceStatus();
                }else{
                    Toast.makeText(getActivity(), "Could not fetch checkIn data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<personalEmployeeProfileApiModel> call, Throwable t) {
                Log.e("profile",t.toString());
            }
        });
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
