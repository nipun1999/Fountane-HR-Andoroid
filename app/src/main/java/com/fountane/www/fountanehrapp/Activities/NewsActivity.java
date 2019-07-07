package com.fountane.www.fountanehrapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fountane.www.fountanehrapp.ApiModels.getNewsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.models.News;

import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    ImageButton back;
    TextView title, text, top_title;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        back = findViewById(R.id.back_but);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        title = findViewById(R.id.news_title);
        text = findViewById(R.id.news_text);
        img = findViewById(R.id.news_image);
        top_title = findViewById(R.id.text_news);

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("Headline"));
        text.setText(intent.getStringExtra("Data"));
        top_title.setText(intent.getStringExtra("from"));

        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("Img"))
                .into(img);

    }
}
