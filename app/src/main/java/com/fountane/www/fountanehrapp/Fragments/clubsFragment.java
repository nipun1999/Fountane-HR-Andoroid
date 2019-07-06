package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class clubsFragment extends Fragment {



    private CardView androidBtn,visualsBtn,uiBtn,hrBtn,frontEndBtn,backEndBtn,iosBtn,pmBtn,contentWrBtn,qaBtn;

    public clubsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
        androidBtn = view.findViewById(R.id.androidClubCardView);
        visualsBtn = view.findViewById(R.id.visualClubCardView);
        uiBtn = view.findViewById(R.id.uiClubCardView);
        hrBtn = view.findViewById(R.id.hrClubCardView);
        frontEndBtn = view.findViewById(R.id.frontEndClubCardView);
        backEndBtn = view.findViewById(R.id.backEndClubCardView);
        iosBtn = view.findViewById(R.id.iosClubCardView);
        pmBtn = view.findViewById(R.id.projectManagerClubCardView);
        contentWrBtn = view.findViewById(R.id.contentWritersClubCardView);
        qaBtn = view.findViewById(R.id.qaClubCardView);

        androidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","android");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });


        visualsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","visuals");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        uiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","UX");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        hrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","HR");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        frontEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","frontend");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        backEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","backend");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        iosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","IOS");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        contentWrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","content writer");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        qaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","QA");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        pmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Clubs");
                arguments.putString("department","manager");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });

        return view;
    }

}
