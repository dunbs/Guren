package com.akarui.guren.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDAO<T> {
    
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(T... entity);
    
    /**
     * @return number of rows that were updated successfully.
     */
    @Update
    int update(T... entity);
    
    /**
     * @return number of rows that were deleted successfully.
     */
    @Delete
    int delete(T... entity);
}

