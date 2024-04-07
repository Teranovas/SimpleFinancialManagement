package com.example.simplefinancialmanagement;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.simplefinancialmanagement.adapter.TransactionListAdapter;
import com.example.simplefinancialmanagement.model.Transaction;
import com.example.simplefinancialmanagement.model.TransactionViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TransactionViewModel transactionVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button editButton = findViewById(R.id.editBtn);
        final TransactionListAdapter adapter = new TransactionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transactionVM = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionVM.getAllTransactions().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable final List<Transaction> transactions) {
                // UI를 업데이트합니다.
                adapter.setTransactions(transactions);
                Log.d(TAG, "삽입 완료");
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddTransactionActivity.class);
                startActivity(intent);
            }
        });

    }
}