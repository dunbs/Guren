package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(
        tableName = "group_member",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Group.class, parentColumns = "id", childColumns = "group_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Role.class, parentColumns = "id", childColumns = "role_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
        })
@Data
public class GroupMember {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private int userId;
    @PrimaryKey
    @ColumnInfo(name = "group_id")
    private int groupId;
    @ColumnInfo(name = "role_id")
    private int roleId;
    @ColumnInfo(name = "joined_date")
    private LocalDateTime joinedDate;
    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;
}
