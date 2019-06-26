package com.fountane.www.fountanehrapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Grievances;
import com.fountane.www.fountanehrapp.models.News;

import java.util.List;

public class grievanceRecyclerAdapter extends RecyclerView.Adapter<grievanceRecyclerAdapter.MyViewHolder> {

    private List<Grievances> grievancesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView desc,date,month,time,status;

        public MyViewHolder(View view) {
            super(view);
            desc = view.findViewById(R.id.grievanceDescTxtView);
            date = view.findViewById(R.id.dateGrievanceTxtView);
            month = view.findViewById(R.id.monthGrievanceTxtView);
            time = view.findViewById(R.id.timeGrievanceTxtView);
            status = view.findViewById(R.id.grievanceStatusTxtView);



        }
    }

    public grievanceRecyclerAdapter(List<Grievances> grievancesList) {
        this.grievancesList = grievancesList;
    }

    @Override
    public grievanceRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grievances_card, parent, false);
        return new grievanceRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(grievanceRecyclerAdapter.MyViewHolder holder, int position) {
        Grievances grievances = grievancesList.get(position);
        holder.desc.setText(grievances.getDesc());
        holder.date.setText(grievances.getDate());
        holder.time.setText(grievances.getTime());
        holder.month.setText(grievances.getMonth());
        holder.status.setText(grievances.getStatus());
    }
    @Override
    public int getItemCount() {
        return grievancesList.size();
    }


}
