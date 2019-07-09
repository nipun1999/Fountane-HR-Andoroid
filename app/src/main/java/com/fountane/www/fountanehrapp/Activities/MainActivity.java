package com.fountane.www.fountanehrapp.Activities;

import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.telecom.Call;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.Fragments.AttendanceFragment;
import com.fountane.www.fountanehrapp.Fragments.CalenderFragment;
import com.fountane.www.fountanehrapp.Fragments.DashboardFragment;
import com.fountane.www.fountanehrapp.Fragments.FountaneFragment;
import com.fountane.www.fountanehrapp.Fragments.UserProfileFragment;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.AppConstants;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager sessionManager;
    private GoogleSignInClient mGoogleSignInClient;
    private ProgressDialog pd;
    private TextView nameNavTxt;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.nav0:
                    AppConstants.enableSearch = false;
                    transaction.replace(R.id.content, new DashboardFragment()).addToBackStack("tag").commit();
                    return true;
                case R.id.nav1:
                    AppConstants.enableSearch = false;
                    transaction.replace(R.id.content, new CalenderFragment()).addToBackStack("tag").commit();
                    return true;
                case R.id.nav2:
                    AppConstants.enableSearch = false;
                    transaction.replace(R.id.content, new AttendanceFragment()).addToBackStack("tag").commit();
                    return true;
                case R.id.nav3:
                    AppConstants.enableSearch = true;
                    transaction.replace(R.id.content, new FountaneFragment()).addToBackStack("tag").commit();
                    return true;
            }
            return false;
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        Menu menu = findViewById(R.menu.main);
        FirebaseMessaging.getInstance().subscribeToTopic("News");
        FirebaseMessaging.getInstance().subscribeToTopic("Events");


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new DashboardFragment()).commit();


//        loadFragment(new DashboardFragment());
        BottomNavigationView navView = findViewById(R.id.nav_view_bottom);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getProfile();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        Toast.makeText(this, sessionManager.getEMP_Code(), Toast.LENGTH_SHORT).show();


       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        nameNavTxt = headerView.findViewById(R.id.nameNavTxtView);

        if (sessionManager.getEMPLOYEE_NAME() != null) {
            nameNavTxt.setText(sessionManager.getEMPLOYEE_NAME());
        }

    }


//    private void getProfile() {
//        retrofit2.Call<personalEmployeeProfileApiModel> call =ApiClient.getClient().personalProfile(sessionManager.getEMP_Code());
//        call.enqueue(new Callback<personalEmployeeProfileApiModel>() {
//            @Override
//            public void onResponse(retrofit2.Call<personalEmployeeProfileApiModel> call, Response<personalEmployeeProfileApiModel> response) {
//                if(response.code()==200){
//                    Log.v("name",response.body().getProfile().get(0).getName());
//                    sessionManager.setEMPLOYEE_NAME(response.body().getProfile().get(0).getName());
//                }
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<personalEmployeeProfileApiModel> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            item.setVisible(false);
        }

        if (id == R.id.action_search && AppConstants.enableSearch) {
//            item.setVisible(true);
            Intent i = new Intent(MainActivity.this, DirectorySearchActivity.class);
            startActivity(i);
            return true;
        }
//        } else if (id == R.id.action_notifications) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.profile) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, new UserProfileFragment()).addToBackStack("tag").commit();

        } else if (id == R.id.settings) {

        } else if (id == R.id.about) {

        } else if (id == R.id.help_support) {

        } else if (id == R.id.privacy_policy) {

        } else if (id == R.id.terms_of_service) {

        } else if (id == R.id.logout) {
            signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        sessionManager.logoutUser();
                        Intent signIn = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(signIn);
                    }
                });
    }


}

