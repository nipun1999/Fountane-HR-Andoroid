package com.fountane.www.fountanehrapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fountane.www.fountanehrapp.Adapters.DirectoryAdapter;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Utils.AppConstants;
import com.fountane.www.fountanehrapp.models.DirectoryList;

import java.util.ArrayList;
import java.util.List;

public class DirectorySearchActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public DirectoryAdapter directoryAdapter;
    public List<DirectoryList> directoryList = AppConstants.DIRECTORY;
    public List<DirectoryList> searchList = new ArrayList<>();

    public EditText search;
    public ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_search);
        Log.d("dev1234", String.valueOf(AppConstants.DIRECTORY.size()));

        searchButton = findViewById(R.id.search_button);
        search = findViewById(R.id.edit_search);
        searchList = directoryList;
        recyclerView = findViewById(R.id.recycler_view);
        directoryAdapter = new DirectoryAdapter(searchList, this);
        RecyclerView.LayoutManager directoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(directoryLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(directoryAdapter);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    void filter(String text) {
        List<DirectoryList> temp = new ArrayList();
        for (DirectoryList d : directoryList) {
            if (d.getName().contains(text)) {
                temp.add(d);
            }
        }
        directoryAdapter.updateList(temp);
    }
}
