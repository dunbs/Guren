package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.akarui.guren.database.entity.Job;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface JobDAO extends BaseDAO<Job> {

    @Query("SELECT * FROM job WHERE id = :jobId")
    Job findJobById(int jobId);
    
    @Query("SELECT j.* FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId")
    List<Job> findJobsByUser(int userId);
    
    @Query("SELECT j.* FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId AND j.title LIKE :titleSearchString")
    List<Job> findJobsByUserAndTitle(int userId, String titleSearchString);
    
    @Query("SELECT j.* FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId AND j.priority IN (:priorities)")
    List<Job> findJobsByUserAndPriorities(int userId, int[] priorities);
    
    @Query("SELECT j.* FROM job j " +
            "JOIN `group` g ON g.id = j.group_id " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId AND j.priority IN (:priorities) " +
            "AND j.deadline BETWEEN :fromDay AND :toDay " +
            "ORDER BY j.deadline DESC")
    List<Job> findJobsByDeadline(int userId, int[] priorities, LocalDateTime fromDay, LocalDateTime toDay);
    
    @Query("SELECT * FROM job WHERE group_id = :groupId")
    List<Job> findJobsByGroup(int groupId);
    
    @Query("SELECT * FROM job " +
            "WHERE group_id = :groupId " +
            "   AND (job.startDateTime BETWEEN :from AND :to " +
            "       OR job.deadline BETWEEN :from AND :to )")
    List<Job> findJobsByGroupAndDays(int groupId, LocalDateTime from, LocalDateTime to);
    
    
    @Query("SELECT * FROM job ORDER BY created_date DESC LIMIT 1")
    Job findNewestAddedJob();
}
