package com.example.simplefinancialmanagement.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.simplefinancialmanagement.R;
import com.example.simplefinancialmanagement.model.Transaction;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Transaction> transactions; // 거래 데이터 목록

    // Constructor
    public TransactionListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        if (transactions != null && !transactions.isEmpty()) {
            Transaction current = transactions.get(position);
            holder.bindTo(current); // Bind the current transaction to the holder
            Log.d("TransactionAdapter", "Binding transaction at position " + position + ": " + current.toString());
        } else {
            Log.d("TransactionAdapter", "No data to bind at position " + position);
            // Data not ready yet
        }
    }

    @Override
    public int getItemCount() {
        return transactions != null ? transactions.size() : 0;
//        if (transactions != null) {
//            return transactions.size();
//
//        } else {
//            return 0;
//        }
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
        Log.d("TransactionAdapter", "Data set changed. Size: " + transactions.size());
        notifyDataSetChanged(); // Notify any registered observers that the data set has changed.
    }

    // ViewHolder class
    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final TextView transactionItemView;

        private TransactionViewHolder(View itemView) {
            super(itemView);
            transactionItemView = itemView.findViewById(R.id.transaction_text);

        }

        void bindTo(Transaction transaction) {
            // Assuming you have a TextView in your transaction_item layout to display the transaction details
            String displayText = transaction.getDate() + ": " +
                    transaction.getType() + ", " +
                    transaction.getPayment();
            transactionItemView.setText(displayText);
        }
    }
}