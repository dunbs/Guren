package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(tableName = "transaction",
        foreignKeys = {@ForeignKey(entity = Group.class, parentColumns = "id", childColumns = "group_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)}
)
@Data
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "group_id", index = true)
    private int groupId;
    private float amount;
    private String reason;
    @ColumnInfo(name = "transaction_date")
    private LocalDateTime transactionDate;
}
