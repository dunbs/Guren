package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.akarui.guren.database.entity.Group;

import java.util.List;

@Dao
public interface GroupDAO extends BaseDAO<Group>{
    @Query("SELECT * FROM `group`")
    List<Group> getAll();
    
    @Query("SELECT * FROM `group` WHERE id IN (:groupIds)")
    List<Group> loadAllByIds(int... groupIds);
    
    @Query("SELECT * FROM `group` WHERE creator_id = :userId AND is_single_user = true LIMIT 1")
    Group loadSingleUserGroup(int userId);
}