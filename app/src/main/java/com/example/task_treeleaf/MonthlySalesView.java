package com.example.task_treeleaf;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonthlySalesView extends AppCompatActivity {

    private SalesViewModel salesViewModel;
    private MonthlySalesAdapter monthlySalesAdapter;
    private SalesDataAdapter salesDataAdapter;
    private TextView amountInWords;
    private TextView grandTotal;
    private RecyclerView recyclerView;
    private Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montly_sales_view);

        recyclerView = findViewById(R.id.resultView);
        amountInWords = findViewById(R.id.amountInWords);
        grandTotal = findViewById(R.id.grandTotal);
        filterSpinner = findViewById(R.id.filterSpinner);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        salesViewModel = new ViewModelProvider(this).get(SalesViewModel.class);
        amountInWords.requestFocus();

        // Set up the spinner with filter options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filter_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // All Data
                        loadAllSales();
                        break;
                    case 1: // Monthly Data
                        loadMonthlySales();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when no item is selected if needed
            }
        });

        // Load data based on default spinner selection
        loadMonthlySales();
    }

    private void loadMonthlySales() {
        salesViewModel.getMonthlySales().observe(this, new Observer<List<MonthlySalesSummary>>() {
            @Override
            public void onChanged(List<MonthlySalesSummary> monthlySalesList) {
                if (monthlySalesList != null) {
                    monthlySalesAdapter = new MonthlySalesAdapter(monthlySalesList);
                    recyclerView.setAdapter(monthlySalesAdapter);

                    double totalAmount = 0;
                    for (MonthlySalesSummary summary : monthlySalesList) {
                        totalAmount += summary.getTotal_amount();
                    }
                    grandTotal.setText(String.format("NRS%.2f", totalAmount));

                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append("In Words: ");
                    builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "In Words: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(new RelativeSizeSpan(1.5f), 0, "In Words: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(AmountToWordsConverter.convertAmountToWords(totalAmount));
                    amountInWords.setText(builder);
                }
            }
        });
    }

    private void loadAllSales() {
        salesViewModel.getAllSales().observe(this, new Observer<List<SalesData>>() {
            @Override
            public void onChanged(List<SalesData> salesDataList) {
                if (salesDataList != null) {
                    salesDataAdapter = new SalesDataAdapter(salesDataList);
                    recyclerView.setAdapter(salesDataAdapter);

                    double totalAmount = 0;
                    for (SalesData data : salesDataList) {
                        totalAmount += data.getAmount();
                    }
                    grandTotal.setText(String.format("NRS.%.2f", totalAmount));

                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append("In Words: ");
                    builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "In Words: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(new RelativeSizeSpan(1.5f), 0, "In Words: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(AmountToWordsConverter.convertAmountToWords(totalAmount));
                    amountInWords.setText(builder);
                }
            }
        });
    }
}
