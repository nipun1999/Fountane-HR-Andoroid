package com.fountane.www.fountanehrapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fountane.www.fountanehrapp.Activities.SignInActivity;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {



    private TextView nameTxtView,designationTxtView,empCodeTxtView,contactNoTxtView,emailTxtView,dobTxtView,addressTxtView,branchTxtView,departmentTxtView;
    private SessionManager sessionManager;
    private ProgressDialog pd;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Profile");

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        nameTxtView = view.findViewById(R.id.profileNameTxtView);
        designationTxtView = view.findViewById(R.id.designationProfileTxtView);
        empCodeTxtView = view.findViewById(R.id.profileEmpCodeTxtView);
        contactNoTxtView = view.findViewById(R.id.profileContactTxtView);
        emailTxtView = view.findViewById(R.id.profileEmailTxtView);
        dobTxtView = view.findViewById(R.id.profileDOBTxtView);
        addressTxtView = view.findViewById(R.id.addressProfileTxtView);
        branchTxtView = view.findViewById(R.id.profileBranchTxtView);
        departmentTxtView = view.findViewById(R.id.profileDepartmentTxtView);
        sessionManager = new SessionManager(getActivity());

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);


        getProfileData();
        return view;
    }

    private void getProfileData() {
        pd.show();
        Call<personalEmployeeProfileApiModel> call = ApiClient.getClient().personalProfile(sessionManager.getEMP_Code());
        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
            @Override
            public void onResponse(Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
                pd.dismiss();
                if(response.code()==200){
                    nameTxtView.setText(response.body().getProfile().get(0).getName());
                    designationTxtView.setText(response.body().getProfile().get(0).getDesignation());
                    empCodeTxtView.setText(response.body().getProfile().get(0).getEmpCode());
                    contactNoTxtView.setText(response.body().getProfile().get(0).getMobileNo());
                    dobTxtView.setText(response.body().getProfile().get(0).getDOB());
                    addressTxtView.setText(response.body().getProfile().get(0).getProvince());
                    branchTxtView.setText(response.body().getProfile().get(0).getBranchLocation());
                    departmentTxtView.setText(response.body().getProfile().get(0).getDepartment());
                    emailTxtView.setText(response.body().getProfile().get(0).getPersonalEmail());
                }else{
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<personalEmployeeProfileApiModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

}
