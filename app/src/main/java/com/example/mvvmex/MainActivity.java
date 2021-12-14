package com.example.mvvmex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmex.data.CustomAdapter;
import com.example.mvvmex.data.DataModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    MutableLiveData<List<DataModel.Datum>> photoList = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        recyclerView = findViewById(R.id.customRecyclerView);
        setupViewModel();

    }

    private void setupViewModel() {

        MainViewModelFactory factory = new MainViewModelFactory(this, recyclerView);
        MainViewModel viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        viewModel.getTasks().observe(this, new Observer<List<DataModel.Datum>>() {
            @Override
            public void onChanged(List<DataModel.Datum> data) {
                if (data.isEmpty()) {
                    progressDoalog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                } else {
                    progressDoalog.dismiss();
                    generateDataList(data);
                }
            }
        });

    }

    private void generateDataList(List<DataModel.Datum> data) {
        adapter = new CustomAdapter(data, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}