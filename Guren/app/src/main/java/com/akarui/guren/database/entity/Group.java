package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(tableName = "group")
@Data
public class Group {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float fund;
    /**
     * true: sử dụng cho 1 user (creator = member)
     * false: sử dụng cho nhiều user
     */
    @ColumnInfo(name = "is_single_user")
    private boolean isSingleUser;
    @ColumnInfo(name = "creator_id")
    private int creatorId;
    @ColumnInfo(name = "created_date")
    private LocalDateTime createdDate;
    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;
}
