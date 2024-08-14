package com.example.task_treeleaf;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

@Dao
public interface SalesDao {

    @Insert
    void insert(SalesData salesData);

    @Query("SELECT strftime('%Y-%m', date) AS month, SUM(amount) AS total_amount FROM sales_data GROUP BY month")
    LiveData<List<MonthlySalesSummary>> getMonthlySales();

    @Query("SELECT * FROM sales_data")
    LiveData<List<SalesData>> getAllSales();
}
