package com.example.mvvmex;

import com.example.mvvmex.data.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("users")
    Call<DataModel> getDataModel();

}
