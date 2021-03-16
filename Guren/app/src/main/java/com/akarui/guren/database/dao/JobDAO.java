package com.akarui.guren.database.dao;

import androidx.room.Query;

import com.akarui.guren.database.entity.Job;

import java.time.LocalDateTime;
import java.util.List;

public interface JobDAO extends BaseDAO<Job> {

    @Query("SELECT * FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId")
    List<Job> findJobsByUser(int userId);
    
    @Query("SELECT * FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId AND j.title LIKE :titleSearchString")
    List<Job> findJobsByUserAndTitle(int userId, String titleSearchString);
    
    @Query("SELECT * FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId AND j.priority IN (:priorities)")
    List<Job> findJobsByUserAndPriorities(int userId, int[] priorities);
    
    @Query("SELECT * FROM job WHERE group_id = :groupId")
    List<Job> findJobsByGroup(int groupId);
}
