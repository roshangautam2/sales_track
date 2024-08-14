package com.example.task_treeleaf.app_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.task_treeleaf.dao.SalesDao;
import com.example.task_treeleaf.model.SalesData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SalesData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SalesDao salesDao();

    private static volatile AppDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "sales_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }
}
