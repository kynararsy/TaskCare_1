package com.example.taskcare1.Utils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.taskcare1.Model.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM tbl_tugas")
    LiveData<List<Task>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Task tasks);

    @Query("DELETE FROM tbl_tugas")
    void deleteAllData();

    @Query("DELETE FROM tbl_tugas WHERE nmr= :nmr")
    void deleteSingleData(int nmr);

    @Query("UPDATE tbl_tugas SET tenggat = :tenggat, waktu = :waktu WHERE nmr = :nmr")
    void updateData(String tenggat, String waktu, int nmr);
}
