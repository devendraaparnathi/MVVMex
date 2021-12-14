package com.example.mvvmex;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmex.data.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    Context context;
    RecyclerView recyclerView;

    public Repository(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public MutableLiveData<List<DataModel.Datum>> getTasks() {

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DataModel> call = service.getDataModel();
        final MutableLiveData<List<DataModel.Datum>> newData = new MutableLiveData<>();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                DataModel dataModel = response.body();
                if (dataModel != null) {
                    List<DataModel.Datum> datumList = dataModel.getData();
                    newData.setValue(datumList);
                }

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                System.out.println("onFailure");
                newData.setValue(null);
            }
        });
        return newData;
    }

}
