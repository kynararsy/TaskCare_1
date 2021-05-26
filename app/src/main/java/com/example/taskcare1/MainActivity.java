package com.example.taskcare1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.taskcare1.Adapter.RecyclerAdapter;
import com.example.taskcare1.Model.Task;
import com.example.taskcare1.View.AddDataActivity;
import com.example.taskcare1.View.MainViewModel;
import com.example.taskcare1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerAdapterCallback{

    private ActivityMainBinding binding;
    private RecyclerAdapter recyclerAdapter;
    private MainViewModel mainViewModel;

    private List<Task> mTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        observeData();

        initAction();
    }
    private void initAction() {
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDataActivity.startActivity(MainActivity.this, false,
                        null);
            }
        });

        binding.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.deleteAllData();
            }
        });
    }

    private void initAdapter() {
        recyclerAdapter = new RecyclerAdapter(this, mTasks, this);
        binding.rvTasks.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTasks.setItemAnimator(new DefaultItemAnimator());
        binding.rvTasks.setAdapter(recyclerAdapter);
    }
    private void observeData() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTasks().observe(this,
                new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        if (tasks.isEmpty()) {
                            binding.btnHapus.setVisibility(View.GONE);
                        } else {
                            binding.btnHapus.setVisibility(View.VISIBLE);
                        }

                        recyclerAdapter.addData(tasks);
                    }
                });
    }
    @Override
    public void onEdit(Task task) {
        AddDataActivity.startActivity(this, true, task);
    }

    @Override
    public void onDelete(Task task) {
        int nmr = task.nmr;
        mainViewModel.deleteSingleData(nmr);
    }
}