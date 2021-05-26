package com.example.taskcare1.Utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskcare1.Model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
}
