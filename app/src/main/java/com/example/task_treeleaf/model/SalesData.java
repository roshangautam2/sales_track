package com.example.task_treeleaf.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sales_data")
public class SalesData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private double amount;
    private String productName;

    public SalesData(String date,String productName, double amount) {
        this.date = date;
        this.productName = productName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
