package com.fountane.www.fountanehrapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.fountane.www.fountanehrapp.Adapters.EventsTabAdapter;
import com.fountane.www.fountanehrapp.Fragments.OlderEventsTab;
import com.fountane.www.fountanehrapp.Fragments.OnGoingEventsTab;
import com.fountane.www.fountanehrapp.Fragments.UpcomingEventsTab;
import com.fountane.www.fountanehrapp.R;

public class EventsActivity extends AppCompatActivity {

    private EventsTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Events");


        adapter = new EventsTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new OnGoingEventsTab(), "ONGOING");
        adapter.addFragment(new UpcomingEventsTab(), "UPCOMING");
        adapter.addFragment(new OlderEventsTab(), "OLDER");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
