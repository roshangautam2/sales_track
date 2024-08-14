package com.example.task_treeleaf;

public class MonthlySalesSummary {
    private String month;
    private double total_amount;

    public MonthlySalesSummary(String month, double total_amount) {
        this.month = month;
        this.total_amount = total_amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
}
