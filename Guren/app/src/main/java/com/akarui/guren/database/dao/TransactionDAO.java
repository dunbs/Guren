package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.akarui.guren.database.entity.Transaction;

import java.util.List;

@Dao
public interface TransactionDAO extends BaseDAO<Transaction>{
    @Query("SELECT * FROM `transaction` WHERE group_id = :groupId")
    List<Transaction> loadTransactionsByGroupId(int groupId);
}
