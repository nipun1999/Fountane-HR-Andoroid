package com.fountane.www.fountanehrapp.Retrofit;

import com.fountane.www.fountanehrapp.ApiModels.AddCommentAttendance;
import com.fountane.www.fountanehrapp.ApiModels.CreateAttendanceApiModel;
import com.fountane.www.fountanehrapp.ApiModels.EmployeeDetails;
import com.fountane.www.fountanehrapp.ApiModels.createLeaveApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getAttendanceApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getEventsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getGrievancesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getLeavesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getMonthlyAttendanceApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getNewsApiModel;
import com.fountane.www.fountanehrapp.ApiModels.getPeopleDepartmentWise;
import com.fountane.www.fountanehrapp.ApiModels.googleLoginApiModel;
import com.fountane.www.fountanehrapp.ApiModels.createGrievancesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;
import com.fountane.www.fountanehrapp.ApiModels.registrationApiModel;
import com.fountane.www.fountanehrapp.ApiModels.signInApiModel;
import com.fountane.www.fountanehrapp.ApiModels.updateAttendanceApiModel;

import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @POST("api/v1/checkUser/signInGoogle")
    Call<googleLoginApiModel> signInGoogle(@Body Map<String, String> body);

    @GET("api/v1/get/employeeprofile")
    Call<personalEmployeeProfileApiModel> personalProfile(@Query("empCode") String empCode);

    @POST("api/v1/create/addGrievance")
    Call<createGrievancesApiModel> createGrievance(@Body Map<String,String> body);

    @GET("api/v1/get/grievance")
    Call<getGrievancesApiModel> getGrievances(@Query("empCode") String empCode);

    @POST("api/v1/create/attendance")
    Call<CreateAttendanceApiModel> createAttendance(@Body Map<String,String> body);

    @POST("api/v1/updateCheckOut/attendance")
    Call<updateAttendanceApiModel> updateAttendance(@Body Map<String,String> body);

    @GET("api/v1/get/attendance")
    Call<getAttendanceApiModel> getAttendance(@Query("empCode") String empCode);

    @GET("api/v1/getByMonth")
    Call<getMonthlyAttendanceApiModel> getMonthlyAttendance(@Query("empCode") String empcode,@Query("month") String month,@Query("year") String year);

    @POST("api/v1/leaves/create")
    Call<createLeaveApiModel> createLeave(@Body Map<String,String> body);

    @GET("api/v1/leaves/get")
    Call<getLeavesApiModel> getLeaves(@Query("empCode") String empCode);

    @GET("api/v1/get/news")
    Call<getNewsApiModel> getNews();

    @GET("api/v1/get/event")
    Call<getEventsApiModel> getEvents();

    @POST("api/v1/signup")
    Call<registrationApiModel> createNewUser(@Body Map<String,String> body);

    @POST("api/v1/checkUser/signIn")
    Call<signInApiModel> signIn(@Body Map<String,String> body);

    @GET("api/v1/get/departmentWise")
    Call<getPeopleDepartmentWise> getPeople(@Query("role_responsibility")String responsibility,@Query("department")String department);

    @GET("api/v1/get/employeeProfile")
    Call<EmployeeDetails> getAllEmployees();

    @POST("api/v1/addComment/attendance")
    Call<AddCommentAttendance> addComment(@Body Map<String,String> body);

    @POST("api/v1/get/attendance")
    Call<getAttendanceApiModel> getAttandanceDate(@Query("empCode") String empCode,@Query("date")Date date);


}
