package com.example.task_treeleaf.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.task_treeleaf.R;
import com.example.task_treeleaf.model.SalesData;
import com.example.task_treeleaf.view_model.SalesViewModel;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private SalesViewModel viewModel;
    private EditText amountInput,itemName;
    private Button addButton, calculateButton;
    private DatePicker datePicker;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountInput = findViewById(R.id.edtAmountInput);
        itemName = findViewById(R.id.edtItemName);
        addButton = findViewById(R.id.addButton);
        calculateButton = findViewById(R.id.calculateButton);
        datePicker = findViewById(R.id.datePicker);

        viewModel = new ViewModelProvider(this).get(SalesViewModel.class);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Set the DatePicker to today's date
        datePicker.init(year, month, day, (view, year1, monthOfYear, dayOfMonth) -> {
            selectedDate = year1 + "-" + String.format("%02d", monthOfYear + 1) + "-" + String.format("%02d", dayOfMonth);
        });

        // Set selectedDate initially to today's date
        selectedDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", day);

        addButton.setOnClickListener(v -> {
            String amountText = amountInput.getText().toString();
            String productName = itemName.getText().toString();


            if (selectedDate == null || selectedDate.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                return;
            }

            if (amountText == null || amountText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (productName == null || productName.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }
            double amount;
            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid amount format", Toast.LENGTH_SHORT).show();
                return;
            }

            SalesData salesData = new SalesData(selectedDate,productName, amount);
            viewModel.insertSale(salesData);
            Toast.makeText(MainActivity.this, "Sale added successfully", Toast.LENGTH_SHORT).show();
             amountInput.setText("");
            itemName.setText("");
        });

        calculateButton.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MonthlySalesView.class);
            startActivity(i);
        });
    }
}
