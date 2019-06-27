package com.fountane.www.fountanehrapp.Retrofit;

import com.fountane.www.fountanehrapp.ApiModels.getGrievancesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.googleLoginApiModel;
import com.fountane.www.fountanehrapp.ApiModels.createGrievancesApiModel;
import com.fountane.www.fountanehrapp.ApiModels.personalEmployeeProfileApiModel;

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


}
