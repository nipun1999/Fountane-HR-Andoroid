package com.fountane.www.fountanehrapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fountane.www.fountanehrapp.R;

public class NewsActivity extends AppCompatActivity {

    ImageButton back;
    TextView title, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        back = findViewById(R.id.back_but);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        title = findViewById(R.id.news_title);
        text = findViewById(R.id.text_news);

//        Call<News> call = ApiClient.getClient().personalProfile(empCode);
//        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
//            @Override
//            public void onResponse(Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
//                pd.dismiss();
//                if(response.code()==200){
//                    nameTxtView.setText(response.body().getProfile().get(0).getName());
//                    designationTxtView.setText(response.body().getProfile().get(0).getDesignation());
//                    empCodeTxtView.setText(response.body().getProfile().get(0).getEmpCode());
//                    contactNoTxtView.setText(response.body().getProfile().get(0).getMobileNo());
//                    dobTxtView.setText(response.body().getProfile().get(0).getDOB());
//                    addressTxtView.setText(response.body().getProfile().get(0).getProvince());
//                    branchTxtView.setText(response.body().getProfile().get(0).getBranchLocation());
//                    departmentTxtView.setText(response.body().getProfile().get(0).getDepartment());
//                    emailTxtView.setText(response.body().getProfile().get(0).getPersonalEmail());
//                }else{
//                    pd.dismiss();
//                    Toast.makeText(employeeDetailActivity.this, "Could not load profile", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<personalEmployeeProfileApiModel> call, Throwable t) {
//                Toast.makeText(employeeDetailActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
//                pd.dismiss();
//            }
//        });




    }
}
