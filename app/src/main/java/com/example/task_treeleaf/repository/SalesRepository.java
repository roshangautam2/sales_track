package com.example.task_treeleaf.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.task_treeleaf.app_database.AppDatabase;
import com.example.task_treeleaf.dao.SalesDao;
import com.example.task_treeleaf.model.MonthlySalesSummary;
import com.example.task_treeleaf.model.SalesData;

import java.util.List;

public class SalesRepository {
    private final SalesDao salesDao;

    public SalesRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        salesDao = db.salesDao();
    }

    public void insertSale(SalesData salesData) {
        AppDatabase.getDatabaseWriteExecutor().execute(() -> salesDao.insert(salesData));
    }

    public LiveData<List<MonthlySalesSummary>> getMonthlySales() {
        return salesDao.getMonthlySales();
    }

    public LiveData<List<SalesData>> getAllSales() {
        return salesDao.getAllSales();
    }
}
