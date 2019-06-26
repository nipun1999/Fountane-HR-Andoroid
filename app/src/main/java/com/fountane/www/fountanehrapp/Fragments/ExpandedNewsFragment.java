package com.fountane.www.fountanehrapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fountane.www.fountanehrapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandedNewsFragment extends Fragment {


    public ExpandedNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("News");
        return inflater.inflate(R.layout.fragment_expanded_news, container, false);
    }

}
