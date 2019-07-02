package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.attendanceRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.leaveRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.getAttendanceApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getMonthlyAttendanceApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.EventDecorator;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.models.Attendance;
import com.fountane.www.fountanehrapp.models.Leaves;
import com.google.android.gms.common.api.Api;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {

    private RecyclerView attendanceRecycler;
    private attendanceRecyclerAdapter attendanceRecyclerAdapter;
    private List<Attendance> attendanceList = new ArrayList<>();
    private SessionManager sessionManager;
    private Date mydate;
    private MaterialCalendarView attendanceCalendarView;
    List<CalendarDay> list = new ArrayList<CalendarDay>();
    private List<CalendarDay> calendarDays;
    private ImageView imageView;
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
        sessionManager = new SessionManager(getActivity());
        RecyclerView.LayoutManager leavesLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        attendanceRecycler.setLayoutManager(leavesLayoutManager);
        attendanceRecycler.setItemAnimator(new DefaultItemAnimator());
        attendanceRecycler.setAdapter(attendanceRecyclerAdapter);
        attendanceRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        attendanceCalendarView = view.findViewById(R.id.attendanceCalendarView);
        imageView = view.findViewById(R.id.attendanceImageView);

        attendanceCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Integer month = date.getMonth() + 1;
                Integer year = date.getYear();
//                getMonthlyAttendance(month,year);
            }
        });
        prepareAttendanceData();


        return view;
    }

    private void getMonthlyAttendance(final Integer month, final Integer year) {
        Call<getMonthlyAttendanceApiModel> call = ApiClient.getClient().getMonthlyAttendance(sessionManager.getEMP_Code(),Integer.toString(month),Integer.toString(year));
        call.enqueue(new Callback<getMonthlyAttendanceApiModel>() {
            @Override
            public void onResponse(Call<getMonthlyAttendanceApiModel> call, Response<getMonthlyAttendanceApiModel> response) {

                for(int i=0;i<response.body().getAttendanceobj().size();i++){
                    Calendar calendar = Calendar.getInstance();
                    Integer date = response.body().getAttendanceobj().get(i).getDate();
                    String type = response.body().getAttendanceobj().get(i).getType();
                    calendar.set(year,month,date);
                    CalendarDay calendarDay = CalendarDay.from(calendar);
                    list.add(calendarDay);
                }

                calendarDays = list;
//                attendanceCalendarView.addDecorators(new EventDecorator(myColor, calendarDays));

            }

            @Override
            public void onFailure(Call<getMonthlyAttendanceApiModel> call, Throwable t) {

            }
        });
    }

    private void prepareAttendanceData() {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        attendanceList.clear();
        Call<getAttendanceApiModel> call = ApiClient.getClient().getAttendance(sessionManager.getEMP_Code());
        call.enqueue(new Callback<getAttendanceApiModel>() {
            @Override
            public void onResponse(Call<getAttendanceApiModel> call, Response<getAttendanceApiModel> response) {
                if(response.code()==200){

                    if(response.body().getAttendanceobj().size()!=0){
//                        Log.e("attendanceSize",Integer.toString(response.body().getAttendanceobj().size()));
                        for(int i=0;i<response.body().getAttendanceobj().size();i++){
                            Attendance attendance = new Attendance();
//                            Log.e("attendance",Integer.toString(response.body().getAttendanceobj().get(i).getAttendanceId()));

                            try {
                                mydate = df.parse(response.body().getAttendanceobj().get(i).getCheckIn());
                                String month = parseMonth(mydate.getMonth()+1);
                                attendance.setDate(Integer.toString(mydate.getDate()));
                                Log.e("grievanced",Integer.toString(mydate.getDate()));
                                Log.e("grievancet",Long.toString(mydate.getTime()));
                                Log.e("grievancem",Integer.toString(mydate.getMonth()));
                                attendance.setMonth(month);
                                attendance.setCheckIn("Check In: " + Integer.toString(mydate.getHours())+":"+Integer.toString(mydate.getMinutes()));
                            } catch (ParseException e) {
                                attendance.setDate("00");
                                attendance.setCheckIn("Check In: "+ "00");
                                attendance.setMonth("00");
                                e.printStackTrace();
                            }

                            if(response.body().getAttendanceobj().get(i).getCheckOut()!=null){
                                try {
                                    mydate = df.parse(response.body().getAttendanceobj().get(i).getCheckOut());
                                    String month = parseMonth(mydate.getMonth()+1);
//                                    attendance.setDate(Integer.toString(mydate.getDate()));
//                                    Log.e("grievanced",Integer.toString(mydate.getDate()));
//                                    Log.e("grievancet",Long.toString(mydate.getTime()));
//                                    Log.e("grievancem",Integer.toString(mydate.getMonth()));
//                                    attendance.setMonth(month);
                                    attendance.setCheckOut("Check Out: " + Integer.toString(mydate.getHours())+":"+Integer.toString(mydate.getMinutes()));
                                } catch (ParseException e) {
                                    attendance.setDate("00");
                                    attendance.setCheckIn("Check In: "+ "00");
                                    attendance.setMonth("00");
                                    e.printStackTrace();
                                }
                            }else{
                                attendance.setCheckOut("Check Out: ");
                            }

                            attendanceList.add(attendance);
//                            attendanceList.sort();
                        }

                        attendanceRecyclerAdapter.notifyDataSetChanged();
                    }else{
                        imageView.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                    Log.e("attendance",Integer.toString(response.code()));
//                    Log.e("attendance",response.message());
                }
            }

            @Override
            public void onFailure(Call<getAttendanceApiModel> call, Throwable t) {

            }
        });
//        Attendance attendance1 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
//        attendanceList.add(attendance1);
//
//        Attendance attendance2 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
//        attendanceList.add(attendance2);
//
//        Attendance attendance3 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
//        attendanceList.add(attendance3);
//
//        Attendance attendance4 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
//        attendanceList.add(attendance4);
//
//        Attendance attendance5 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
//        attendanceList.add(attendance5);
//
//        Attendance attendance6 = new Attendance("25","Jan","Check In- 09:45 Am","Check Out - 09:00 Pm");
//        attendanceList.add(attendance6);

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
