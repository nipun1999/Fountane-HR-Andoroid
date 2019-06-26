package com.fountane.www.fountanehrapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Grievances;
import com.fountane.www.fountanehrapp.models.Leaves;

import java.util.List;

public class leaveRecyclerAdapter extends  RecyclerView.Adapter<leaveRecyclerAdapter.MyViewHolder> {

    private List<Leaves> leavesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView type,detail,date,month,time,status;

        public MyViewHolder(View view) {
            super(view);
            type = view.findViewById(R.id.leaveSummaryTxtView);
            date = view.findViewById(R.id.leaveDateTxtView);
            month = view.findViewById(R.id.leaveMonthTxtView);
            time = view.findViewById(R.id.timeLeaveTxtView);
            status = view.findViewById(R.id.leaveStatusTxtView);
            detail = view.findViewById(R.id.leaveDetailTxtView);


        }
    }
    public leaveRecyclerAdapter(List<Leaves> leavesList) {
        this.leavesList = leavesList;
    }

    @Override
    public leaveRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leave_card, parent, false);
        return new leaveRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(leaveRecyclerAdapter.MyViewHolder holder, int position) {
        Leaves leaves = leavesList.get(position);
        holder.type.setText(leaves.getType());
        holder.date.setText(leaves.getDate());
        holder.time.setText(leaves.getTime());
        holder.month.setText(leaves.getMonth());
        holder.status.setText(leaves.getStatus());
        holder.detail.setText(leaves.getDetail());
    }
    @Override
    public int getItemCount() {
        return leavesList.size();
    }


}
