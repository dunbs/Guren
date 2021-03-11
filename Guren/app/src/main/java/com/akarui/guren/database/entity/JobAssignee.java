package com.akarui.guren.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.time.LocalDateTime;

import lombok.Data;

@Entity(
        tableName = "job_assignee",
        foreignKeys = {
                @ForeignKey(entity = Job.class, parentColumns = "id", childColumns = "job_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class, parentColumns = {"id", "assigner_id"}, childColumns = {"user_id", "user_id"}, onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        }
)
@Data
public class JobAssignee {
    @ColumnInfo(name = "job_id")
    private int jobId;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "assigner_id")
    private int assignerId;
    @ColumnInfo(name = "created_date")
    private LocalDateTime createdDate;
    @ColumnInfo(name = "is_deleted")
    private boolean isDeleted;
}
