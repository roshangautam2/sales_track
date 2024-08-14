package com.example.task_treeleaf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class SalesDataAdapter extends RecyclerView.Adapter<SalesDataAdapter.SalesViewHolder> {

    private List<SalesData> salesDataList = new ArrayList<>(); // Initialize with an empty list

    public static class SalesViewHolder extends RecyclerView.ViewHolder {
        public TextView serialNumberTextView;
        public TextView dateTextView;
        public TextView amountTextView;

        public SalesViewHolder(View view) {
            super(view);
            serialNumberTextView = view.findViewById(R.id.serialNumber);
            dateTextView = view.findViewById(R.id.date);
            amountTextView = view.findViewById(R.id.amount);
        }
    }

    public SalesDataAdapter(List<SalesData> salesDataList) {
        if (salesDataList != null) {
            this.salesDataList = salesDataList;
        }
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new SalesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {
        SalesData salesData = salesDataList.get(position);
        holder.serialNumberTextView.setText(String.valueOf(position + 1));
        holder.dateTextView.setText(salesData.getDate());
        holder.amountTextView.setText(String.valueOf(salesData.getAmount()));
    }

    @Override
    public int getItemCount() {
        return salesDataList != null ? salesDataList.size() : 0; // Ensure not to return size on null list
    }

    public void setSalesDataList(List<SalesData> salesDataList) {
        if (salesDataList != null) {
            this.salesDataList = salesDataList;
            notifyDataSetChanged();
        }
    }
}
