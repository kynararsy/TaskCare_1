package com.example.taskcare1.View;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.taskcare1.Model.Task;
import com.example.taskcare1.Utils.DatabaseClient;
import com.example.taskcare1.Utils.TaskDAO;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AddDataViewModel extends AndroidViewModel {

    private TaskDAO taskDAO;

    public AddDataViewModel(@NonNull Application application) {
        super(application);

        taskDAO = DatabaseClient.getInstance(application).getAppDatabase().taskDAO();
    }
    public void addTugas(final String tugas, final String deskripsi, final String tenggat, final String waktu) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Task task = new Task();
                task.tugas = tugas;
                task.deskripsi = deskripsi;
                task.tenggat = tenggat;
                task.waktu = waktu;
                taskDAO.insertData(task);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void updateTugas(final int nmr, final String tenggat, final String waktu){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                taskDAO.updateData(tenggat, waktu, nmr);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
