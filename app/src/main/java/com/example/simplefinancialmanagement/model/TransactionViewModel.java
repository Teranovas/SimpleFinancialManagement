package com.example.simplefinancialmanagement.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel { // 안드로이드 애플리케이션에서 사용되는 ViewModel 클래스

    private RoomDB roomDB; // RoomDB의 인스턴스를 저장하는 변수
    private LiveData<List<Transaction>> getAllTransactions; // LiveData를 사용하여 모든 거래 정보를 비동기적으로 관찰 가능한 상태로 유지한다.

    public TransactionViewModel(@NonNull Application application){
        super(application);
        roomDB = RoomDB.getDB(application);
        getAllTransactions = roomDB.transactionDAO().getAllTransactions();
        // 생성자에서는 애플리케이션의 컨텍스트를 받아 RoomDB 인스턴스를 초기화 및 LiveData에 모든 정보를 저장
    }

    // 모든 거래 정보를 반환하는 메소드
    public LiveData<List<Transaction>> getAllTransactions(){
        return getAllTransactions;
    }
    // 거래 정보를 데이터베이스에 삽입하는 메소드, AsyncTask를 사용한다.
    public void insert(Transaction transaction){
        new insertTask(roomDB).execute(transaction);
    }

    // AsyncTask를 상속받는 내부 클래스로, 비동기적으로 거래 정보를 데이터베이스에 삽입한다.
    public static class insertTask extends AsyncTask<Transaction, Void, Void> {
        private RoomDB database;

        // 생성자에서 RoomDB 인스턴스를 초기화
        public insertTask(RoomDB roomDB) {
            database = roomDB;
        }


        // 거래 정보 삽입 작업을 수행한다.
        @Override
        protected Void doInBackground(Transaction... transactions) {
            database.transactionDAO().insert(transactions[0]);
            return null;
        }
    }
}
