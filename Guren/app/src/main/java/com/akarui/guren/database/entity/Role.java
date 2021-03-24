package com.akarui.guren.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "role")
public class Role {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
}
