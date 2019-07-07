package com.fountane.www.fountanehrapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.fountane.www.fountanehrapp.Fragments.clubsFragment;
import com.fountane.www.fountanehrapp.Fragments.consultancyFragment;
import com.fountane.www.fountanehrapp.R;

public class HierarchyTabbedActivity extends AppCompatActivity {

    private FrameLayout simpleFrameLayout;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hierarchy_tabbed);

        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
// Create a new Tab named "First"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Consultancy"); // set the Text for the first Tab// set an icon for the
// first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout
// Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Clubs"); // set the Text for the second Tab// set an icon for the second tab
        tabLayout.addTab(secondTab); // add  the tab  in the TabLayout
// Create a new Tab named "Third"/
// add  the tab at in the TabLayout


        Fragment consultancyFragment = new consultancyFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, consultancyFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();


// perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new consultancyFragment();
                        break;
                    case 1:
                        fragment = new clubsFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
