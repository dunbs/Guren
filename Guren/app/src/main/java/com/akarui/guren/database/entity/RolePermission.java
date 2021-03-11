package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(
        tableName = "role_permission",
        foreignKeys = {
                @ForeignKey(entity = Role.class, parentColumns = "id", childColumns = "role_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Permission.class, parentColumns = "id", childColumns = "permission_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        }
)
@Data
public class RolePermission {
    @PrimaryKey
    @ColumnInfo(name = "role_id")
    private int roleId;
    @PrimaryKey
    @ColumnInfo(name = "permission_id")
    private int permissionId;
}
