package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.akarui.guren.database.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface TransactionDAO extends BaseDAO<Transaction>{
    @Query("SELECT * FROM `transaction` WHERE group_id = :groupId")
    List<Transaction> loadTransactionsByGroupId(int groupId);
    
    @Query("SELECT * FROM `transaction` " +
            "WHERE group_id = :groupId " +
            "AND transaction_date BETWEEN :from AND :to")
    List<Transaction> loadTransactionsByGroupId(int groupId, LocalDateTime from, LocalDateTime to);
    
    @Query("SELECT * FROM `transaction` WHERE id = :transactionId")
    Transaction loadTransactionById(int transactionId);
    
    @Query("SELECT * FROM `transaction` WHERE id IN (:transactionId)")
    List<Transaction> loadTransactionsByIds(int... transactionId);
}
