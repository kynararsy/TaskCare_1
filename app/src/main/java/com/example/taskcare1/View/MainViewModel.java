package com.example.taskcare1.View;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.taskcare1.Model.Task;
import com.example.taskcare1.Utils.DatabaseClient;
import com.example.taskcare1.Utils.TaskDAO;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Task>> mTasks;
    private TaskDAO taskDAO;

    public MainViewModel(@NonNull Application application) {
        super(application);

        taskDAO = DatabaseClient.getInstance(application).getAppDatabase().taskDAO();
        mTasks = taskDAO.getAll();
    }
    public LiveData<List<Task>> getTasks() {
        return mTasks;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                taskDAO.deleteAllData();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void deleteSingleData(final int no) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                taskDAO.deleteSingleData(no);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
