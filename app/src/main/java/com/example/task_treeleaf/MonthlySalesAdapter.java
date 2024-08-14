package com.example.task_treeleaf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonthlySalesAdapter extends RecyclerView.Adapter<MonthlySalesAdapter.ViewHolder> {
    private List<MonthlySalesSummary> monthlySalesList;

    public MonthlySalesAdapter(List<MonthlySalesSummary> monthlySalesList) {
        this.monthlySalesList = monthlySalesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monthly_sales, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MonthlySalesSummary summary = monthlySalesList.get(position);

        holder.monthTextView.setText(summary.getMonth());
        holder.totalAmountTextView.setText(String.valueOf(summary.getTotal_amount()));
    }

    @Override
    public int getItemCount() {
        return monthlySalesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView monthTextView;
        public TextView totalAmountTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            monthTextView = itemView.findViewById(R.id.monthTextView);
            totalAmountTextView = itemView.findViewById(R.id.totalAmountTextView);
        }
    }
}
