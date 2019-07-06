package com.fountane.www.fountanehrapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class employeeDetailActivity extends AppCompatActivity {

    private String empCode;
    private TextView nameTxtView,designationTxtView,empCodeTxtView,contactNoTxtView,emailTxtView,dobTxtView,addressTxtView,branchTxtView,departmentTxtView;
    private SessionManager sessionManager;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        Intent intent = getIntent();
        empCode = intent.getStringExtra("empCode");

        nameTxtView = findViewById(R.id.profileNameTxtView);
        designationTxtView = findViewById(R.id.designationProfileTxtView);
        empCodeTxtView = findViewById(R.id.profileEmpCodeTxtView);
        contactNoTxtView = findViewById(R.id.profileContactTxtView);
        emailTxtView = findViewById(R.id.profileEmailTxtView);
        dobTxtView = findViewById(R.id.profileDOBTxtView);
        addressTxtView = findViewById(R.id.addressProfileTxtView);
        branchTxtView = findViewById(R.id.profileBranchTxtView);
        departmentTxtView = findViewById(R.id.profileDepartmentTxtView);

        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.setCancelable(false);

        getProfileData();

    }

    private void getProfileData() {
        pd.show();
        Call<personalEmployeeProfileApiModel> call = ApiClient.getClient().personalProfile(empCode);
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
                    Toast.makeText(employeeDetailActivity.this, "Could not load profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<personalEmployeeProfileApiModel> call, Throwable t) {
                Toast.makeText(employeeDetailActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}
