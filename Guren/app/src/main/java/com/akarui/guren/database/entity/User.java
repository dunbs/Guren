package com.akarui.guren.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(tableName = "user",
        indices = {@Index(value = {"username"}, unique = true)}
)
@Data
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    @ColumnInfo(name = "created_date")
    private LocalDateTime createdDate;
    private boolean isDeleted;
}
