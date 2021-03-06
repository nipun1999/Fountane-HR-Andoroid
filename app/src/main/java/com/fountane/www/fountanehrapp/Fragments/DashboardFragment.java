package com.fountane.www.fountanehrapp.Fragments;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Activities.EventsActivity;
import com.fountane.www.fountanehrapp.Activities.HierarchyTabbedActivity;
import com.fountane.www.fountanehrapp.Adapters.EventsRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.newsRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.CreateAttendanceApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getEventsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getNewsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.ApiModels.updateAttendanceApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.AppConstants;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.models.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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

    private RelativeLayout hrBut;
    private CardView grievancesBtn, leavesBtn, payslipsBtn, documentsBtn, addBtn;
    private RecyclerView newsRecycler, eventsRecycler;
    private newsRecyclerAdapter newsRecyclerAdapter;
    private EventsRecyclerAdapter eventsRecyclerAdapter;
    private TextView newsViewAll, eventsViewAll;
    private List<News> newsList = new ArrayList<>();
    private List<News> eventsList = new ArrayList<>();
    private ImageView checkInBtn;
    private SessionManager sessionManager;
    private String date;
    private ProgressDialog pd;
    private Date mydate;
    private DatabaseReference mdatabase;
    private ImageView noNewsImageView, noEventsImageView;
    long cacheExpiration = 43200;
    private FirebaseRemoteConfig firebaseRemoteConfig;
    private String adminEmail;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private boolean doubleBackToExitPressedOnce = false;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        TextView pageTitle = view.findViewById(R.id.toolbar_title);
//        pageTitle.setText("Home");
        grievancesBtn = view.findViewById(R.id.grievancesLayout);
        hrBut = view.findViewById(R.id.hr_but);
        leavesBtn = view.findViewById(R.id.leavesLayout);
        payslipsBtn = view.findViewById(R.id.payslipsLayout);
        documentsBtn = view.findViewById(R.id.adminLayout);
        newsRecycler = view.findViewById(R.id.newsRecyclerView);
        eventsRecycler = view.findViewById(R.id.eventsRecyclerView);
        checkInBtn = view.findViewById(R.id.checkInBtn);
        newsViewAll = view.findViewById(R.id.newsViewAll);
        noNewsImageView = view.findViewById(R.id.noNewsImageView);
        noEventsImageView = view.findViewById(R.id.noEventImageView);
        eventsViewAll = view.findViewById(R.id.eventsViewAll);
        addBtn = view.findViewById(R.id.addLayout);

        sessionManager = new SessionManager(getActivity());

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("admin");
        pd.show();
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                adminEmail = dataSnapshot.child("email").getValue().toString();
                Log.e("email", adminEmail);
                Log.e("email", sessionManager.getEMAIL_ID());
                if (adminEmail.equals(sessionManager.getEMAIL_ID())) {
                    documentsBtn.setVisibility(View.VISIBLE);
                    hrBut.setVisibility(View.VISIBLE);
                    addBtn.setVisibility(View.GONE);
                } else {
                    addBtn.setVisibility(View.VISIBLE);
                    documentsBtn.setVisibility(View.GONE);
                    hrBut.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();
            }
        });


//        adminEmail = firebaseRemoteConfig.getString("admin_email");


//        firebaseRemoteConfig.fetch(getCacheExpiration())
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            firebaseRemoteConfig.activateFetched();
//                            adminEmail = firebaseRemoteConfig.getString("admin_email");
//                            Toast.makeText(getActivity(), "Task Successful", Toast.LENGTH_SHORT).show();
//                            // We got our config, let's do something with it!
//                        } else {
//                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();// Looks like there was a problem getting the config...
//                        }
//
//                    }
//                });


        newsRecyclerAdapter = new newsRecyclerAdapter(newsList, getActivity());
        eventsRecyclerAdapter = new EventsRecyclerAdapter(eventsList, getActivity());

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager eventsLayoutmanager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        newsRecycler.setLayoutManager(newsLayoutManager);
        newsRecycler.setItemAnimator(new DefaultItemAnimator());
        newsRecycler.setAdapter(newsRecyclerAdapter);

        eventsRecycler.setLayoutManager(eventsLayoutmanager);
        eventsRecycler.setItemAnimator(new DefaultItemAnimator());
        eventsRecycler.setAdapter(eventsRecyclerAdapter);

        Log.e("attendance", Boolean.toString(sessionManager.getAttendanceStatus()));
        getAttendanceStatus();


        newsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, new NewsFragment()).addToBackStack("tag").commit();
            }
        });

        eventsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EventsActivity.class);
                startActivity(i);
            }
        });

        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!sessionManager.getAttendanceStatus()) {
                    pd.show();
                    date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Date currentTime = Calendar.getInstance().getTime();
                    Log.e("date", date);
                    Log.e("date", currentTime.toString());
                    Map<String, String> map = new HashMap<>();
                    map.put("empCode", sessionManager.getEMP_Code());
                    map.put("date", date);
                    map.put("checkIn", date + " " + currentTime.getHours() + ":" + currentTime.getMinutes() + ":" + currentTime.getSeconds());

                    retrofit2.Call<CreateAttendanceApiModel> call = ApiClient.getClient().createAttendance(map);
                    call.enqueue(new Callback<CreateAttendanceApiModel>() {
                        @Override
                        public void onResponse(retrofit2.Call<CreateAttendanceApiModel> call, Response<CreateAttendanceApiModel> response) {
                            pd.hide();
                            if (response.code() == 200) {
                                sessionManager.setATTENDANCE_ID(response.body().getAttendanceobj().getAttendanceId());
                                sessionManager.setATTENDANCE_STATUS(true);
                                checkInBtn.setImageResource(R.drawable.ic_check_out);
                                Toast.makeText(getActivity(), "Checked In Succesfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Failed to check in", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<CreateAttendanceApiModel> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(getActivity(), "Some error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    Map<String, String> map = new HashMap<>();
                    date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Date currentTime = Calendar.getInstance().getTime();
//                    Log.e("date",date);
//                    Log.e("date",currentTime.toString());

                    map.put("attendanceId", Integer.toString(sessionManager.getATTENDANCE_ID()));
                    map.put("checkOut", date + " " + currentTime.getHours() + ":" + currentTime.getMinutes() + ":" + currentTime.getSeconds());
                    Log.e("attendance", date + " " + currentTime.getHours() + ":" + currentTime.getMinutes() + ":" + currentTime.getSeconds());
                    pd.show();
                    retrofit2.Call<updateAttendanceApiModel> call = ApiClient.getClient().updateAttendance(map);
                    call.enqueue(new Callback<updateAttendanceApiModel>() {
                        @Override
                        public void onResponse(retrofit2.Call<updateAttendanceApiModel> call, Response<updateAttendanceApiModel> response) {
                            pd.hide();
                            if (response.code() == 200) {
                                sessionManager.setATTENDANCE_STATUS(false);
                                checkInBtn.setImageResource(R.drawable.ic_check_in);
                                Toast.makeText(getActivity(), "Checked Out Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("attendance", Integer.toString(response.code()));
                                Log.e("attendance", response.message().toString());
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
                transaction.replace(R.id.content, new GrievancesFragment()).addToBackStack("tag").commit();

            }
        });

        leavesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, new LeavesFragment()).addToBackStack("tag").commit();
            }
        });
        payslipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, new PayslipsAndOthersFragment()).addToBackStack("tag").commit();
            }
        });
        documentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent adminScreen = new Intent(getActivity(), HierarchyTabbedActivity.class);
                startActivity(adminScreen);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.content,new DocumentListFragment()).addToBackStack("tag").commit();
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
        AppConstants.EVENTS.clear();
        retrofit2.Call<getEventsApiModel> call = ApiClient.getClient().getEvents();
        call.enqueue(new Callback<getEventsApiModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(retrofit2.Call<getEventsApiModel> call, Response<getEventsApiModel> response) {
                pd.hide();
                if (response.code() == 200) {

                    if (response.body().getEvents().size() != 0) {

                        noEventsImageView.setVisibility(View.GONE);
                        for (int i = 0; i < response.body().getEvents().size(); i++) {
                            News news = new News();
                            try {
                                mydate = df.parse(response.body().getEvents().get(i).getEventDate());
                                String temp = String.format("%02d", mydate.getDate());
                                String month = parseMonth(mydate.getMonth() + 1);
                                news.setDate(temp);
                                news.setMonth(month);
//                                news.setImageUrl(response.body().getEvents().get(i).getImageFirebaseLink().toString());
                                news.setTime(Integer.toString(mydate.getHours()) + ":" + Integer.toString(mydate.getMinutes()));
                            } catch (ParseException e) {
                                news.setDate("00");
                                news.setTime("00");
                                news.setMonth("00");
                                e.printStackTrace();
                            }
                            news.setTitle(response.body().getEvents().get(i).getName());
                            news.setData(response.body().getEvents().get(i).getEventVenue());
                            news.setPublishedby(response.body().getEvents().get(i).getEventVenue());
                            news.setDate_gmt(response.body().getEvents().get(i).getEventDate());
                            AppConstants.EVENTS.add(news);
                            final DateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                            Date strDate = null;
                            try {
                                strDate = sdf.parse(response.body().getEvents().get(i).getEventDate());
                                if (new Date().before(strDate) && eventsList.size() < 3) {
                                    eventsList.add(news);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }
                        Collections.sort(eventsList, new Comparator<News>() {
                            @Override
                            public int compare(News o1, News o2) {
                                final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                Integer s1 = Integer.parseInt(o1.getDate());
                                Integer s2 = Integer.parseInt(o2.getDate());
                                return s1.compareTo(s2);
                            }
                        });
                        eventsRecyclerAdapter.notifyDataSetChanged();

                    } else {

                        noEventsImageView.setVisibility(View.VISIBLE);
                    }
                } else {
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
                if (response.code() == 200) {
                    if (response.body().getNewsobj().size() != 0) {
                        noNewsImageView.setVisibility(View.GONE);
                        for (int i = 0; i < response.body().getNewsobj().size(); i++) {
                            News news = new News();
                            try {
                                mydate = df.parse(response.body().getNewsobj().get(i).getDate());
                                String month = parseMonth(mydate.getMonth() + 1);
                                news.setDate(Integer.toString(mydate.getDate()));
                                news.setMonth(month);
                                news.setImageUrl(response.body().getNewsobj().get(i).getImageFirebaseLink());
                                news.setTime(Integer.toString(mydate.getHours()) + ":" + Integer.toString(mydate.getMinutes()));
                            } catch (ParseException e) {
                                news.setDate("00");
                                news.setTime("00");
                                news.setMonth("00");
                                e.printStackTrace();
                            }
                            news.setData(response.body().getNewsobj().get(i).getText());
                            news.setTitle(response.body().getNewsobj().get(i).getTitle());
                            news.setPublishedby(response.body().getNewsobj().get(i).getVenue());
                            newsList.add(news);
                        }
                        Collections.reverse(newsList);
                        newsRecyclerAdapter.notifyDataSetChanged();
                    } else {
                        noNewsImageView.setVisibility(View.VISIBLE);
                    }
                } else {
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
        Log.e("dashboardAttendance", "inside");
//        Log.e("dashboard",Boolean.toString(sessionManager.getAttendanceStatus()));
        if (sessionManager.getAttendanceStatus()) {
            checkInBtn.setImageResource(R.drawable.ic_check_out);
        } else {
            checkInBtn.setImageResource(R.drawable.ic_check_in);
        }
    }

    private void getAttendanceStatus() {

        retrofit2.Call<personalEmployeeProfileApiModel> call = ApiClient.getClient().personalProfile(sessionManager.getEMP_Code());
        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
            @Override
            public void onResponse(retrofit2.Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
                if (response.code() == 200) {
                    Boolean status = response.body().getProfile().get(0).getStatus();
                    Log.e("dashboardatttendance", Boolean.toString(status));
                    sessionManager.setATTENDANCE_STATUS(status);
                    sessionManager.setATTENDANCE_ID(response.body().getProfile().get(0).getAttendanceId());
                    Log.e("atttendance", Integer.toString(response.body().getProfile().get(0).getAttendanceId()));
                    checkAttendanceStatus();
                } else {
                    Toast.makeText(getActivity(), "Could not fetch checkIn data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<personalEmployeeProfileApiModel> call, Throwable t) {
                Log.e("profile", t.toString());
            }
        });
    }


    private String parseMonth(int month) {
        if (month == 1) {
            return "JAN";
        } else if (month == 2) {
            return "FEB";
        } else if (month == 3) {
            return "MAR";
        } else if (month == 4) {
            return "APR";
        } else if (month == 5) {
            return "MAY";
        } else if (month == 6) {
            return "JUN";
        } else if (month == 7) {
            return "JUL";
        } else if (month == 8) {
            return "AUG";
        } else if (month == 9) {
            return "SEP";
        } else if (month == 10) {
            return "OCT";
        } else if (month == 11) {
            return "NOV";
        } else {
            return "DEC";
        }
    }


}
