package com.example.simplefinancialmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.simplefinancialmanagement.model.Transaction;
import com.example.simplefinancialmanagement.model.TransactionViewModel;

import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity {

    private TransactionViewModel transactionViewModel;

    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        Button saveBtn = findViewById(R.id.buttonSave);
        RadioButton inComeText = findViewById(R.id.inCome);
        RadioButton outLayText = findViewById(R.id.outLay);
        EditText amountText = findViewById(R.id.editAmount);
        EditText categoryText = findViewById(R.id.editCategory);

        EditText dateText = findViewById(R.id.date);

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTransactionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                                // 월은 0부터 시작하므로 1을 더해야 합니다.
                                String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
                                dateText.setText(selectedDate);
                            }
                        },
                        year, month, day
                );
                datePickerDialog.setTitle("날짜 입력");
                datePickerDialog.show();
            }

        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inComeText.isChecked()){
                    String inCome = inComeText.getText().toString();
                    double amount = Double.parseDouble(amountText.getText().toString());
                    String category = categoryText.getText().toString();
                    String date = dateText.getText().toString();
                    Transaction transaction = new Transaction(0, inCome, amount, category,date);
                    transactionViewModel.insert(transaction);

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else {
                    String outLay = outLayText.getText().toString();
                    double amount = Double.parseDouble(amountText.getText().toString());
                    String category = categoryText.getText().toString();
                    String date = dateText.getText().toString();
                    Transaction transaction = new Transaction(0, outLay, amount, category,date);
                    transactionViewModel.insert(transaction);

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
