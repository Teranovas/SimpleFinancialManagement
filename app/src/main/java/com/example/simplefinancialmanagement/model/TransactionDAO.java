package com.example.simplefinancialmanagement.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDAO {
    // Transaction 객체를 데이터베이스에 삽입
    @Insert
    void insert(Transaction transaction);

    // LiveData를 사용하여 데이터 변경 시 UI가 자동으로 업데이트되도록 한다.
    @Query("SELECT * FROM userTransaction")
    // 모든 Transaction 객체를 조회
    LiveData<List<Transaction>> getAllTransactions();

    // 주어진 Transaction 객체를 업데이트
    @Update
    void update(Transaction transaction);

    // 주어진 Transaction 객체를 데이터베이스에서 삭제
    @Delete
    void delete(Transaction transaction);
}