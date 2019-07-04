package com.fountane.www.fountanehrapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.grievanceRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.createGrievancesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getGrievancesApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.Utils.StringUtils;
import com.fountane.www.fountanehrapp.models.Grievances;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrievancesFragment extends Fragment {


    private RecyclerView grievanceRecycler;
    private grievanceRecyclerAdapter grievanceRecyclerAdapter;
    private List<Grievances> grievancesList = new ArrayList<>();
    private EditText grievanceEdtTxt;
    private Button postBtn;
    private SessionManager sessionManager;
    private ProgressDialog pd;
    private ProgressDialog pdLoading;
    private ImageView imageView;


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

        grievanceEdtTxt = view.findViewById(R.id.grievanceDescEdtTxt);
        postBtn = view.findViewById(R.id.grievancePostBtn);
        sessionManager = new SessionManager(getActivity());
        imageView = view.findViewById(R.id.grievanceImageView);

        RecyclerView.LayoutManager grievanceLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        grievanceRecycler.setLayoutManager(grievanceLayoutManager);
        grievanceRecycler.setItemAnimator(new DefaultItemAnimator());
        grievanceRecycler.setAdapter(grievanceRecyclerAdapter);
        grievanceRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        pd = new ProgressDialog(getActivity());
        pdLoading = new ProgressDialog(getActivity());
        pdLoading.setMessage("Loading Grievance");
        pdLoading.setCancelable(false);
        pd.setMessage("Creating Grievance");
        pd.setCancelable(false);


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = grievanceEdtTxt.getText().toString();
                if(!StringUtils.isEmpty(desc)){
                    createGrievance(desc);
                }else{
                    Toast.makeText(getActivity(), "Please Enter the description", Toast.LENGTH_SHORT).show();
                }
            }
        });

        prepareGrievancesData();
        return view;
    }

    private void createGrievance(String desc) {
        Map<String,String> map = new HashMap<>();
        pd.show();
        map.put("description",desc);
        map.put("empCode",sessionManager.getEMP_Code());

        Call<createGrievancesApiModel> call = ApiClient.getClient().createGrievance(map);
        call.enqueue(new Callback<createGrievancesApiModel>() {
            @Override
            public void onResponse(Call<createGrievancesApiModel> call, Response<createGrievancesApiModel> response) {
                if(response.code()==200){
                    pd.dismiss();
                    grievanceEdtTxt.setText("");
                    prepareGrievancesData();
                    Toast.makeText(getActivity(), "Grievance Successfully created", Toast.LENGTH_SHORT).show();
                }else{
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Failed to create grievance", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<createGrievancesApiModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Failed to create Grievance", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareGrievancesData() {

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        pdLoading.show();
        Call<getGrievancesApiModel> call = ApiClient.getClient().getGrievances(sessionManager.getEMP_Code());
        call.enqueue(new Callback<getGrievancesApiModel>() {
            @Override
            public void onResponse(Call<getGrievancesApiModel> call, Response<getGrievancesApiModel> response) {
                Date mydate;
                if(response.code()==200){
                    pdLoading.dismiss();
                    if(response.body().getGrievance().size()!=0){
                        imageView.setVisibility(View.GONE);
                        grievancesList.clear();
                        for(int i=0;i<response.body().getGrievance().size();i++){
                            Grievances grievance = new Grievances();
                            try {
                                mydate = df.parse(response.body().getGrievance().get(i).getCreatedAt());
                                String month = parseMonth(mydate.getMonth());
                                grievance.setDate(Integer.toString(mydate.getDate()));
                                Log.e("grievanced",Integer.toString(mydate.getDate()));
                                Log.e("grievancet",Long.toString(mydate.getTime()));
                                Log.e("grievancem",Integer.toString(mydate.getMonth()));
                                grievance.setMonth(month);
                                grievance.setTime(Integer.toString(mydate.getHours())+":"+Integer.toString(mydate.getMinutes()));
                            } catch (ParseException e) {
                                grievance.setDate("00");
                                grievance.setTime("00");
                                grievance.setMonth("00");
                                e.printStackTrace();
                            }
                            grievance.setDesc(response.body().getGrievance().get(i).getDescription());
                            if(response.body().getGrievance().get(i).getStatus()){
                                grievance.setStatus("Resolved");
                            }else{
                                grievance.setStatus("Pending");
                            }
                            grievancesList.add(grievance);
                        }

//                        grievancesList.notify();

                        grievanceRecyclerAdapter.notifyDataSetChanged();
                    }else{
                        imageView.setVisibility(View.VISIBLE);
                    }
                }else{
                    pdLoading.dismiss();
                    Toast.makeText(getActivity(), "Unable to load", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<getGrievancesApiModel> call, Throwable t) {
                pdLoading.dismiss();
                Toast.makeText(getActivity(), "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });

//        Grievances grievance1 = new Grievances("25","Jan","09:42 AM","Pending","TT Table Not Found iirihfiohf irhferiofheriohr ifhre ih igohreioh reigh irgh ern4tk4rl4lj3l4ji4tji4hji4 ipirotfu4h orf eriofheriofh reoighre oiher reoh eriogh reigheriogh eroihg oerihg reoighreigheroi gherio");
//        grievancesList.add(grievance1);
//
//        Grievances grievance2 = new Grievances("25","Jan","09:42 AM","Resolved","PlayStation is not working");
//        grievancesList.add(grievance2);
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
