package com.fountane.www.fountanehrapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fountane.www.fountanehrapp.ApiModels.AddCommentAttendance;
import com.fountane.www.fountanehrapp.R;
import com.fountane.www.fountanehrapp.Retrofit.ApiClient;
import com.fountane.www.fountanehrapp.Utils.AppConstants;
import com.fountane.www.fountanehrapp.Utils.SessionManager;
import com.fountane.www.fountanehrapp.models.Attendance;
import com.fountane.www.fountanehrapp.models.Grievances;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;

public class attendanceRecyclerAdapter extends RecyclerView.Adapter<attendanceRecyclerAdapter.MyViewHolder> {
    private List<Attendance> attendanceList;
    public Context context;
    private SessionManager sessionManager;
    private String comment_string;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, month, checkIn, checkOut, commentForAttendance;

        public RelativeLayout comment_but, comment_relative;

        public Button record_comment;

        public EditText comment;


        public MyViewHolder(View view) {
            super(view);
            checkIn = view.findViewById(R.id.attendanceCheckInTxtView);
            date = view.findViewById(R.id.attendanceDateTxtView);
            month = view.findViewById(R.id.attendanceMonthTxtView);
            checkOut = view.findViewById(R.id.attendanceCheckOutTxtView);
            comment_but = view.findViewById(R.id.comment_but);
            comment_relative = view.findViewById(R.id.comment_relative);
            record_comment = view.findViewById(R.id.record_comment);
            comment = view.findViewById(R.id.comment);
            commentForAttendance = view.findViewById(R.id.comment_for_attendance);
        }
    }

    public attendanceRecyclerAdapter(List<Attendance> attendanceList, Context context) {
        this.attendanceList = attendanceList;
        this.context = context;
    }

    @Override
    public attendanceRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_card, parent, false);
        sessionManager = new SessionManager(context);
        return new attendanceRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final attendanceRecyclerAdapter.MyViewHolder holder, int position) {
        final Attendance attendance = attendanceList.get(position);
        holder.date.setText(attendance.getDate());
        holder.checkOut.setText(attendance.getCheckOut());
        holder.checkIn.setText(attendance.getCheckIn());
        holder.month.setText(attendance.getMonth());
        holder.comment_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.comment_relative.getVisibility() == View.GONE)
                    holder.comment_relative.setVisibility(View.VISIBLE);
                else holder.comment_relative.setVisibility(View.GONE);
            }
        });

        if (attendance.getComment() != null) {
            holder.comment_but.setVisibility(View.GONE);
            holder.commentForAttendance.setText(attendance.getComment());
            holder.commentForAttendance.setVisibility(View.VISIBLE);
        }

        holder.record_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.comment.getText().equals("")) {
                    comment_string = holder.comment.getText().toString();
                }

                Map<String, String> map = new HashMap<>();
                map.put("attendanceId", attendance.getAttendanceId());
                map.put("comments", comment_string);
                map.put("empCode", sessionManager.getEMP_Code());


                retrofit2.Call<AddCommentAttendance> call = ApiClient.getClient().addComment(map);
                call.enqueue(new Callback<AddCommentAttendance>() {
                    @Override
                    public void onResponse(retrofit2.Call<AddCommentAttendance> call, Response<AddCommentAttendance> response) {

                        if (response.code() == 200) {
                            Toast.makeText(context, "Comment Recorded Successfully", Toast.LENGTH_SHORT).show();
                            holder.comment_relative.setVisibility(View.GONE);
                            holder.comment_but.setVisibility(View.GONE);
                            holder.commentForAttendance.setText(comment_string);
                            holder.commentForAttendance.setVisibility(View.VISIBLE);
                        } else {

                            Toast.makeText(context, "Some error", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(retrofit2.Call<AddCommentAttendance> call, Throwable t) {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

}
