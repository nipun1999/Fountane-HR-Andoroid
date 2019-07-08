package com.fountane.www.fountanehrapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.ApiModels.googleLoginApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.ApiModels.registrationApiModel;
import com.fountane.www.fountanehrapp.ApiModels.signInApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.Utils.StringUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "signIn" ;
    private Button signInBtn,emailSignInBtn;
    private GoogleSignInClient mGoogleSignInClient;
    private SessionManager sessionManager;
    private ProgressDialog pd;
    private TextView newUserTxtView;
    private EditText emailEdtTxt,passwordEdtTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        sessionManager = new SessionManager(this);

        signInBtn = findViewById(R.id.googleSignInBtn);
        emailSignInBtn = findViewById(R.id.signInBtn);
        newUserTxtView = findViewById(R.id.newUserTxtView);
        emailEdtTxt = findViewById(R.id.emailSignEdtTxt);
        passwordEdtTxt = findViewById(R.id.passwordSignInEdtTxt);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        pd = new ProgressDialog(SignInActivity.this);
        pd.setMessage("loading");


        pd.setCancelable(false);

        emailSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithEmail();
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        newUserTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });

    }

    private void signInWithEmail() {
        pd.show();
        final String email = emailEdtTxt.getText().toString();
        String password = passwordEdtTxt.getText().toString();
        if(!StringUtils.isEmpty(email)&&!StringUtils.isEmpty(password)){
            Map<String,String>map = new HashMap<>();
            map.put("fountaneEmail",email+"@fountane.com");
            map.put("password",password);

            Call<signInApiModel> call = ApiClient.getClient().signIn(map);
            call.enqueue(new Callback<signInApiModel>() {
                @Override
                public void onResponse(Call<signInApiModel> call, Response<signInApiModel> response) {
                    pd.hide();
                    if(response.code()==200){
                        Boolean status = response.body().getStatus();
                        String token = response.body().getToken();
                        String empCode = response.body().getEmpCode();
                        String name = response.body().getName();
                        Log.e("signin",Boolean.toString(response.body().getStatus()));
                        Log.e("signin",response.body().getToken().toString());
                        Log.e("signin",response.body().getEmpCode());
                        Log.e("signin",response.body().getName());

                        sessionManager.setX_AUTH_TOKEN(token);
                        sessionManager.setLogInStatus(true);
                        sessionManager.setEMP_CODE(empCode);
                        sessionManager.setEMPLOYEE_NAME(name);
                        sessionManager.setEMAIL_ID(email+"@fountane.com");
//                        getAttendanceStatus();

                        if(status){
                            Intent walkthrough = new Intent(SignInActivity.this,WalkthroughActivity.class);
                            startActivity(walkthrough);
                        }else{
                            Intent dashboard = new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(dashboard);
                        }
                    }else{
                        pd.hide();
                        Toast.makeText(SignInActivity.this, "password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<signInApiModel> call, Throwable t) {
                    pd.hide();
                    Log.e("signin",t.getMessage().toString());
                    Toast.makeText(SignInActivity.this, "Some Error occured", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            Log.e("token",idToken);
            authenticateFromServer(idToken);

        } catch (ApiException e) {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    private void authenticateFromServer(String idToken) {
        pd.show();
        Map<String, String> map = new HashMap<>();

        map.put("idToken",idToken);
        Call<googleLoginApiModel> call = ApiClient.getClient().signInGoogle(map);
        call.enqueue(new Callback<googleLoginApiModel>() {
            @Override
            public void onResponse(Call<googleLoginApiModel> call, Response<googleLoginApiModel> response) {
                pd.dismiss();
                if(response.code()==200){
                    Boolean status = response.body().getStatus();
                    String token = response.body().getToken();
                    String empCode = response.body().getEmpCode();
                    String name = response.body().getName();
                    Log.e("empCode",empCode);
                    Log.e("accessToken",token);
                    Log.e("name",name);
                    sessionManager.setX_AUTH_TOKEN(token);
                    sessionManager.setLogInStatus(true);
                    sessionManager.setEMP_CODE(empCode);
                    sessionManager.setEMPLOYEE_NAME(name);
                    sessionManager.setEMAIL_ID(response.body().getEmail());
                    getAttendanceStatus();

                    if(status){
                        Intent walkthrough = new Intent(SignInActivity.this,WalkthroughActivity.class);
                        startActivity(walkthrough);
                    }else{
                        Intent dashboard = new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(dashboard);
                    }
                }else if(response.code()==500){
                    Log.e("signin",response.message());
                    Toast.makeText(SignInActivity.this,"Eamil not registered with fountane, Please contact HR", Toast.LENGTH_SHORT).show();
                    revokeAccess();
                }else{
                    Log.e("signin",response.message().toString());
                    Toast.makeText(SignInActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
                    revokeAccess();
                }
            }

            @Override
            public void onFailure(Call<googleLoginApiModel> call, Throwable t) {
                pd.dismiss();
                revokeAccess();
                Log.e("signin",t.toString());
                Toast.makeText(SignInActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAttendanceStatus() {
        pd.show();
        Call<personalEmployeeProfileApiModel> call = ApiClient.getClient().personalProfile(sessionManager.getEMP_Code());
        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
            @Override
            public void onResponse(Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
                if(response.code()==200){
                    pd.hide();
                    Boolean status = response.body().getProfile().get(0).getStatus();
                    Log.e("atttendance",Boolean.toString(status));
                    sessionManager.setATTENDANCE_STATUS(status);
                    sessionManager.setATTENDANCE_ID(response.body().getProfile().get(0).getAttendanceId());
                    Log.e("atttendanceId",Integer.toString(sessionManager.getATTENDANCE_ID()));
                    Log.e("attendanceId",Integer.toString(sessionManager.getATTENDANCE_ID()));
                }else{
                    Toast.makeText(SignInActivity.this, "error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<personalEmployeeProfileApiModel> call, Throwable t) {
                pd.hide();
                Log.e("profile",t.toString());
            }
        });
    }


    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

}
