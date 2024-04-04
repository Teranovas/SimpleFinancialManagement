package com.example.simplefinancialmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.simplefinancialmanagement.model.TransactionViewModel;

public class MainActivity extends AppCompatActivity {

    private TransactionViewModel transactionVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}