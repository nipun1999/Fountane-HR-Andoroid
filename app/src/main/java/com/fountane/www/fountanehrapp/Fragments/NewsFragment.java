package com.fountane.www.fountanehrapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.Adapters.allNewsRecyclerAdapter;
import com.fountane.www.fountanehrapp.Adapters.newsRecyclerAdapter;
import com.fountane.www.fountanehrapp.ApiModels.getNewsApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.models.News;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    private RecyclerView allNewsRecycler;
    private allNewsRecyclerAdapter newsRecyclerAdapter;
    private List<News> newsList = new ArrayList<>();
    private Date mydate;
    private ProgressDialog pd;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("News");

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        allNewsRecycler = view.findViewById(R.id.allNewsRecycler);
        newsRecyclerAdapter = new allNewsRecyclerAdapter(newsList);
        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        allNewsRecycler.setLayoutManager(newsLayoutManager);
        allNewsRecycler.setItemAnimator(new DefaultItemAnimator());
        allNewsRecycler.setAdapter(newsRecyclerAdapter);

        prepareNewsData();


        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);


        return view;

    }

    private void prepareNewsData() {
        final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        pd.show();
        newsList.clear();
        retrofit2.Call<getNewsApiModel> call = ApiClient.getClient().getNews();
        call.enqueue(new Callback<getNewsApiModel>() {
            @Override
            public void onResponse(retrofit2.Call<getNewsApiModel> call, Response<getNewsApiModel> response) {
//                pd.hide();
                if(response.code()==200) {
                    for (int i = 0; i < response.body().getNewsobj().size(); i++) {
                        News news = new News();
                        try {
                            mydate = df.parse(response.body().getNewsobj().get(i).getDate());
                            String month = parseMonth(mydate.getMonth());
                            news.setDate(Integer.toString(mydate.getDate()));
                            news.setMonth(month);
                            news.setImageUrl(response.body().getNewsobj().get(i).getImageFirebaseLink());
                            news.setTime(Integer.toString(mydate.getHours()) + ":" + Integer.toString(mydate.getMinutes()));
                        } catch (ParseException e) {
                            news.setDate("00");
                            news.setTime("00");
                            news.setMonth("00");
                            e.printStackTrace();
                        }
                        news.setTitle(response.body().getNewsobj().get(i).getTitle());
                        news.setPublishedby(response.body().getNewsobj().get(i).getVenue());
                        newsList.add(news);
                    }
                    newsRecyclerAdapter.notifyDataSetChanged();
                }else{
//                    pd.hide();
                    Toast.makeText(getActivity(), "Some Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<getNewsApiModel> call, Throwable t) {
//                pd.hide();
                Toast.makeText(getActivity(), "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });
//        News news1 = new News("Fountane Logo Launched","25","Founane,India","09:42 AM","www.image.com","Jan");
//        newsList.add(news1);
//
//        News news2 = new News("Fountane Logo Launched","25","Founane,India","09:42 AM","www.image.com","Jan");
//        newsList.add(news2);
//
//        News news3 = new News("Fountane Logo Launched","25","Founane,India","09:42 AM","www.image.com","Jan");
//        newsList.add(news3);
    }
    private String parseMonth(int month) {
        if(month==1){
            return "JAN";
        }else if(month==2){
            return "FEB";
        }else if(month==3){
            return "MAR";
        }else if(month==4){
            return "APR";
        }else if(month==5){
            return "MAY";
        }else if(month==6){
            return "JUN";
        }else if(month==7){
            return "JUL";
        }else if (month==8){
            return "AUG";
        }else if(month==9){
            return "SEP";
        }else if(month==10){
            return "OCT";
        }else if(month==11){
            return "NOV";
        }else{
            return "DEC";
        }
    }

}
