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
public class consultancyFragment extends Fragment {


    private CardView androidBtn,visualsBtn,uiBtn,hrBtn,frontEndBtn,backEndBtn,iosBtn,pmBtn,contentWrBtn,qaBtn;
    public consultancyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultancy, container, false);
        androidBtn = view.findViewById(R.id.androidConsultancyCardView);
        visualsBtn = view.findViewById(R.id.visualConsultancyCardView);
        uiBtn = view.findViewById(R.id.uiConsultancyCardView);
        hrBtn = view.findViewById(R.id.hrConsultancyCardView);
        frontEndBtn = view.findViewById(R.id.frontEndConsultancyCardView);
        backEndBtn = view.findViewById(R.id.backEndConsultancyCardView);
        iosBtn = view.findViewById(R.id.iosConsultancyCardView);
        pmBtn = view.findViewById(R.id.projectManagerConsultancyCardView);
        contentWrBtn = view.findViewById(R.id.contentWritersConsultancyCardView);
        qaBtn = view.findViewById(R.id.qaConsultancyCardView);



        androidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fieldRelatedFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
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
                arguments.putString( "field" , "Consultancy");
                arguments.putString("department","manager");
                fragment.setArguments(arguments);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.simpleFrameLayout,fragment).addToBackStack("tag").commit();
            }
        });
        return  view;

    }

}
