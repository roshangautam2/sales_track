package com.example.task_treeleaf.view_model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.task_treeleaf.repository.SalesRepository;
import com.example.task_treeleaf.model.MonthlySalesSummary;
import com.example.task_treeleaf.model.SalesData;

import java.util.List;

public class SalesViewModel extends AndroidViewModel {
    private final SalesRepository repository;

    public SalesViewModel(@NonNull Application application) {
        super(application);
        repository = new SalesRepository(application);
    }

    public void insertSale(SalesData salesData) {
        repository.insertSale(salesData);
    }

    public LiveData<List<MonthlySalesSummary>> getMonthlySales() {
        return repository.getMonthlySales();
    }

    public LiveData<List<SalesData>> getAllSales() {
        return repository.getAllSales();
    }
}
