package com.akarui.guren.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.akarui.guren.database.entity.User;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UserDAO extends BaseDAO<User>{
    @Query("SELECT * FROM user")
    List<User> loadAll();
    
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int... userIds);

    @Query("SELECT * FROM user WHERE username LIKE :username LIMIT 1")
    User findByName(String username);

}
