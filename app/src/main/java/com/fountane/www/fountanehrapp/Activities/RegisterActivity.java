package com.fountane.www.fountanehrapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.ApiModels.registrationApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView signInTxtView;
    private EditText emailEdtTxt, passwordEdtTxt;
    private Button registerBtn;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signInTxtView = findViewById(R.id.signInTxtView);

        emailEdtTxt = findViewById(R.id.emailEdtTxt);
        passwordEdtTxt = findViewById(R.id.passwordEdtTxt);
        registerBtn = findViewById(R.id.registerBtn);

        pd = new ProgressDialog(RegisterActivity.this);
        pd.setMessage("loading");


        pd.setCancelable(false);


        signInTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(RegisterActivity.this,SignInActivity.class);
                startActivity(signIn);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdtTxt.getText().toString();
                String password = passwordEdtTxt.getText().toString();
                if(!StringUtils.isEmpty(email)&&!StringUtils.isEmpty(password)){
                    registerUser(email,password);
                }else{
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void registerUser(String email,String password) {
        pd.show();
        Map<String,String> map = new HashMap<>();
        map.put("fountaneEmail",email+"@fountane.com");
        map.put("password",password);
        map.put("roleId","1");

        Call<registrationApiModel> call = ApiClient.getClient().createNewUser(map);
        call.enqueue(new Callback<registrationApiModel>() {
            @Override
            public void onResponse(Call<registrationApiModel> call, Response<registrationApiModel> response) {
                pd.hide();
                if(response.code()==200){
                    Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(RegisterActivity.this, "Registration could not be completed, Please contact HR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<registrationApiModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(RegisterActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
