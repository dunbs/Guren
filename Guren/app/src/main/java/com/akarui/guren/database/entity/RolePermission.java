package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(
        tableName = "role_permission",
        primaryKeys = {
                "role_id",
                "permission_id"
        },
        foreignKeys = {
                @ForeignKey(entity = Role.class, parentColumns = "id", childColumns = "role_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Permission.class, parentColumns = "id", childColumns = "permission_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        }
)
@Data
public class RolePermission {
    @ColumnInfo(name = "role_id")
    private int roleId;
    @ColumnInfo(name = "permission_id", index = true)
    private int permissionId;
}
