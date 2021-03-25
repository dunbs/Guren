package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.akarui.guren.database.entity.Group;
import com.akarui.guren.database.entity.User;

import java.util.List;

@Dao
public interface GroupDAO extends BaseDAO<Group>{
    @Query("SELECT * FROM `group`")
    List<Group> getAll();
    
    @Query("SELECT * FROM `group` WHERE id IN (:groupIds)")
    List<Group> loadAllByIds(int... groupIds);
}
