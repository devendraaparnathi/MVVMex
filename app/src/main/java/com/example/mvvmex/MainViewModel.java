package com.example.mvvmex;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmex.data.DataModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    Repository repository;

    public MainViewModel(Context context, RecyclerView recyclerView) {
        repository = new Repository(context,recyclerView);
    }

    public MutableLiveData<List<DataModel.Datum>> getTasks() {
        return repository.getTasks();
    }

}
