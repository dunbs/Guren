package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(
        tableName = "group_member",
        primaryKeys = {
                "user_id",
                "group_id"
        },
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Group.class, parentColumns = "id", childColumns = "group_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Role.class, parentColumns = "id", childColumns = "role_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
        })
@Data
public class GroupMember {
    @ColumnInfo(name = "user_id", index = true)
    private int userId;
    @ColumnInfo(name = "group_id", index = true)
    private int groupId;
    @ColumnInfo(name = "role_id", index = true)
    private int roleId;
    @ColumnInfo(name = "joined_date")
    private LocalDateTime joinedDate;
    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;
}
