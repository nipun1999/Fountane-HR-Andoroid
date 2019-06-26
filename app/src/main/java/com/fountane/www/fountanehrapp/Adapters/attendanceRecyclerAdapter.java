package com.fountane.www.fountanehrapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.models.Attendance;
import com.fountane.www.fountanehrapp.models.Grievances;

import java.util.List;

public class attendanceRecyclerAdapter extends RecyclerView.Adapter<attendanceRecyclerAdapter.MyViewHolder> {
    private List<Attendance> attendanceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date,month,checkIn,checkOut;

        public MyViewHolder(View view) {
            super(view);
            checkIn = view.findViewById(R.id.attendanceCheckInTxtView);
            date = view.findViewById(R.id.attendanceDateTxtView);
            month = view.findViewById(R.id.attendanceMonthTxtView);
            checkOut = view.findViewById(R.id.attendanceCheckOutTxtView);
        }
    }

    public attendanceRecyclerAdapter(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @Override
    public attendanceRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_card, parent, false);
        return new attendanceRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(attendanceRecyclerAdapter.MyViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);
        holder.date.setText(attendance.getDate());
        holder.checkOut.setText(attendance.getCheckOut());
        holder.checkIn.setText(attendance.getCheckIn());
        holder.month.setText(attendance.getMonth());
    }
    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

}
