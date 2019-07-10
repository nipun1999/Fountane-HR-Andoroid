package com.fountane.www.fountanehrapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fountane.www.fountanehrapp.Activities.employeeDetailActivity;
import com.fountane.www.fountanehrapp.Fragments.ProfileFragment;
import com.fountane.www.fountanehrapp.Fragments.UserProfileFragment;
import com.fountane.www.fountanehrapp.Fragments.fieldRelatedFragment;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Leaves;
import com.fountane.www.fountanehrapp.models.peopleField;

import java.util.List;

public class peopleFieldsRecyclerAdapter extends  RecyclerView.Adapter<peopleFieldsRecyclerAdapter.MyViewHolder> {
    private List<peopleField> peopleList;
    private View itemView;
    private Context mcontext;
    private String field;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.directoryNameTxtView);
            imageView = view.findViewById(R.id.personalImageView);
        }
    }

    public peopleFieldsRecyclerAdapter(List<peopleField> peopleList,Context context,String field) {
        this.peopleList = peopleList;
        this.mcontext = context;
        this.field = field;

    }

    @Override
    public peopleFieldsRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_card, parent, false);
        return new peopleFieldsRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(peopleFieldsRecyclerAdapter.MyViewHolder holder, final int position) {
        peopleField people = peopleList.get(position);

        holder.name.setText(people.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),employeeDetailActivity.class);
                intent.putExtra("field",field);
                intent.putExtra("empCode",peopleList.get(position).getEmpCode());
                v.getContext().startActivity(intent);
            }
        });

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.profile_pic)
                .fitCenter();
        Glide.with(itemView.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(people.getImageLink())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }


}
