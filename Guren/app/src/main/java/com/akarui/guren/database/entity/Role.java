package com.akarui.guren.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "role")
@Data
public class Role {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
}
