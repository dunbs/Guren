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
    @ColumnInfo(name = "group_id")
    private int groupId;
    private String title;
    private String detail;
    private LocalDateTime deadline;
    private int priority;
    @ColumnInfo(name = "created_date")
    private LocalDateTime createdDate;
    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;
}
