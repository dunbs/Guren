package com.akarui.guren.database;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;

import com.akarui.guren.database.dao.UserDAO;
import com.akarui.guren.database.entity.Group;
import com.akarui.guren.database.entity.GroupMember;
import com.akarui.guren.database.entity.Job;
import com.akarui.guren.database.entity.JobAssignee;
import com.akarui.guren.database.entity.Permission;
import com.akarui.guren.database.entity.Role;
import com.akarui.guren.database.entity.RolePermission;
import com.akarui.guren.database.entity.User;

@Database(entities = {
                        User.class,
                        Group.class,
                        GroupMember.class,
                        Job.class,
                        JobAssignee.class,
                        Role.class,
                        Permission.class,
                        RolePermission.class
                     },
          version = 1,
          exportSchema = false)
@TypeConverters(Converter.class)
public abstract class GurenDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "guren";

    private static GurenDatabase instance;

    public abstract UserDAO userDAO();

    public static  GurenDatabase getInstance(Context applicationContext)
    {
        if (instance == null){
            instance = Room.databaseBuilder(applicationContext, GurenDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
