package com.fountane.www.fountanehrapp.Fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.ColorUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.ApiModels.createLeaveApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.Utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewLeave extends Fragment {

    private Button casualBtn,sickBtn,paidBtn,wfhBtn,optionalBtn;
    String type = null;
    Calendar myCalendar;
    private TextView fromTxtView,toTxtView;
    private Spinner s;
    private Button applyNow;
    private SessionManager sessionManager;
    private ProgressDialog pd;
    private EditText reasonEdtTxt;


    public NewLeave() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_new_leave, container, false);
        getActivity().setTitle("New Leave");

        casualBtn = view.findViewById(R.id.casualBtn);
        sickBtn = view.findViewById(R.id.sickBtn);
        paidBtn = view.findViewById(R.id.paidBtn);
        wfhBtn = view.findViewById(R.id.wfhBtn);
        optionalBtn = view.findViewById(R.id.optionalBtn);
        fromTxtView = view.findViewById(R.id.fromTxtView);
        toTxtView = view.findViewById(R.id.toTxtView);
        applyNow = view.findViewById(R.id.applyBtn);
        reasonEdtTxt = view.findViewById(R.id.reasonLeave);
        sessionManager = new SessionManager(getActivity());

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);

        s = (Spinner) view.findViewById(R.id.approvalFromSpinner);

        String[] arraySpinner = new String[] {
                "H.R"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

            myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateFromTxtView();
            }
        };


        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateToTxtView();
            }
        };


        fromTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new DatePickerDialog(getActivity(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        toTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new DatePickerDialog(getActivity(), date2, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        casualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casualBtn.setBackgroundResource(R.drawable.new_casual_leave_button_selected);
                paidBtn.setBackgroundResource(R.drawable.new_paid_leave_button);
                optionalBtn.setBackgroundResource(R.drawable.new_optional_leave_button);
                wfhBtn.setBackgroundResource(R.drawable.new_wfh_leave_button);
                sickBtn.setBackgroundResource(R.drawable.new_sick_leave_button);
                casualBtn.setTextColor(Color.WHITE);
                paidBtn.setTextColor(getResources().getColor(R.color.paidColor));
                optionalBtn.setTextColor(getResources().getColor(R.color.optionslColor));
                wfhBtn.setTextColor(getResources().getColor(R.color.wfhColor));
                sickBtn.setTextColor(getResources().getColor(R.color.sickColor));
                type="casualLeave";
            }
        });

        sickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sickBtn.setBackgroundResource(R.drawable.new_sick_leave_button_selected);
                paidBtn.setBackgroundResource(R.drawable.new_paid_leave_button);
                optionalBtn.setBackgroundResource(R.drawable.new_optional_leave_button);
                wfhBtn.setBackgroundResource(R.drawable.new_wfh_leave_button);
                casualBtn.setBackgroundResource(R.drawable.new_casual_leave_button);
                sickBtn.setTextColor(Color.WHITE);
                paidBtn.setTextColor(getResources().getColor(R.color.paidColor));
                optionalBtn.setTextColor(getResources().getColor(R.color.optionslColor));
                wfhBtn.setTextColor(getResources().getColor(R.color.wfhColor));
                casualBtn.setTextColor(getResources().getColor(R.color.casualColor));
                type="sickLeave";
            }
        });

        paidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paidBtn.setBackgroundResource(R.drawable.new_paid_leave_button_selected);
                casualBtn.setBackgroundResource(R.drawable.new_casual_leave_button);
                optionalBtn.setBackgroundResource(R.drawable.new_optional_leave_button);
                wfhBtn.setBackgroundResource(R.drawable.new_wfh_leave_button);
                sickBtn.setBackgroundResource(R.drawable.new_sick_leave_button);
                paidBtn.setTextColor(Color.WHITE);
                casualBtn.setTextColor(getResources().getColor(R.color.casualColor));
                optionalBtn.setTextColor(getResources().getColor(R.color.optionslColor));
                wfhBtn.setTextColor(getResources().getColor(R.color.wfhColor));
                sickBtn.setTextColor(getResources().getColor(R.color.sickColor));
                type="paidLeave";
            }
        });

        wfhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wfhBtn.setBackgroundResource(R.drawable.new_wfh_leave_button_selected);
                paidBtn.setBackgroundResource(R.drawable.new_paid_leave_button);
                optionalBtn.setBackgroundResource(R.drawable.new_optional_leave_button);
                sickBtn.setBackgroundResource(R.drawable.new_sick_leave_button);
                casualBtn.setBackgroundResource(R.drawable.new_casual_leave_button);
                wfhBtn.setTextColor(Color.WHITE);
                paidBtn.setTextColor(getResources().getColor(R.color.paidColor));
                optionalBtn.setTextColor(getResources().getColor(R.color.optionslColor));
                casualBtn.setTextColor(getResources().getColor(R.color.casualColor));
                sickBtn.setTextColor(getResources().getColor(R.color.sickColor));
                type="otherLeave";
            }
        });

        optionalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionalBtn.setBackgroundResource(R.drawable.new_optional_leave_button_selected);
                paidBtn.setBackgroundResource(R.drawable.new_paid_leave_button);
                wfhBtn.setBackgroundResource(R.drawable.new_wfh_leave_button);
                sickBtn.setBackgroundResource(R.drawable.new_sick_leave_button);
                casualBtn.setBackgroundResource(R.drawable.new_casual_leave_button);
                optionalBtn.setTextColor(Color.WHITE);
                paidBtn.setTextColor(getResources().getColor(R.color.paidColor));
                casualBtn.setTextColor(getResources().getColor(R.color.casualColor));
                wfhBtn.setTextColor(getResources().getColor(R.color.wfhColor));
                sickBtn.setTextColor(getResources().getColor(R.color.sickColor));
                type="otherLeave";
            }
        });


        applyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String from = fromTxtView.getText().toString();
               String to = toTxtView.getText().toString();
               String reason = reasonEdtTxt.getText().toString();

                if(type!=null&&!StringUtils.isEmpty(from)&&!StringUtils.isEmpty(to)&&!StringUtils.isEmpty(reason)){
                    Map<String,String> map = new HashMap<>();
                    map.put("empCode",sessionManager.getEMP_Code());
                    map.put("leaveType",type);
                    map.put("fromDate",from);
                    map.put("toDate",to);
                    map.put("description",reason);
                    pd.show();
                    Call<createLeaveApiModel> call = ApiClient.getClient().createLeave(map);
                    call.enqueue(new Callback<createLeaveApiModel>() {
                        @Override
                        public void onResponse(Call<createLeaveApiModel> call, Response<createLeaveApiModel> response) {
                            pd.hide();
                            if(response.code()==200){
                                Toast.makeText(getActivity(), "Leave Successfully Submited", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.content,new LeavesFragment()).addToBackStack("tag").commit();
                            }else{
                                Toast.makeText(getActivity(), "You dont have sufficient leaves", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<createLeaveApiModel> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "Please Select every parameter", Toast.LENGTH_SHORT).show();
                }

            }
        });




        return view;



    }

    private void updateToTxtView() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            toTxtView.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateFromTxtView() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            fromTxtView.setText(sdf.format(myCalendar.getTime()));
    }

}
