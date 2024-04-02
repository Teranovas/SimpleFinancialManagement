package com.example.simplefinancialmanagement.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
// Room 데이터베이스를 정의하는 클래스, Transaction 클래스를 데이터베이스 엔티티로 사용한다.
@Database(entities = {Transaction.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    // TransactionDAO에 대한 추상 메소드를 정의한다. DAO의 구현 인스턴스에 접근할 수 있습니다.
    public abstract TransactionDAO transactionDAO();

    // RoomDB의 싱글톤 인스턴스를 저장하는 변수
    private static RoomDB INSTANCE;

    // RoomDB의 싱글톤 인스턴스를 반환하는 메소드
    static RoomDB getDB(final Context context){
        if (INSTANCE == null){
            synchronized (RoomDB.class){
                if(INSTANCE == null){
                    // Room 데이터베이스 빌더를 사용하여 RoomDB 인스턴스를 생성
                    // 이 데이터베이스는 'transactionDB'라는 이름으로 앱의 파일 시스템에 저장된다.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "transactionDB").build();
                }
            }
        }
        return INSTANCE;
    }
}
