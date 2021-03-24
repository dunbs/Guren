package com.akarui.guren.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "permission")
@Data
public class Permission {
    @PrimaryKey
    private int id;
    private String name;
}
