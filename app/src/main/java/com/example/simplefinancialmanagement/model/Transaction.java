package com.example.simplefinancialmanagement.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userTransaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)

    private int id; // 거래 식별자
    private String type; // 수입/지출
    private double payment; // 거래액
    private String category; // 거래 카테고리
    private String date; // 날짜

    public Transaction(int id, String type, double payment, String category, String date
    ) {

        this.id = id;
        this.type = type;
        this.payment = payment;
        this.category = category;
        this.date = date;

    }
    // Getter, Setter문 (생략)
    public int getId() {return id;}
    public String getType() {return type;}
    public double getPayment() {return payment;}
    public String getCategory() {return category;}
    public String getDate() {return date;}
    public void setId(int id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public void setPayment(double amount) {this.payment = payment;}
    public void setCategory(String category) {this.category = category;}
    public void setDate(String date) {this.date = date;}

}
