package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(tableName = "job")
@Data
public class Job {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int groupId;
    private LocalDateTime deadline;
    @ColumnInfo(name = "created_date")
    private LocalDateTime createdDate;
    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;
}
