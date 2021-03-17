package com.akarui.guren.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.akarui.guren.database.dao.GroupDAO;
import com.akarui.guren.database.dao.GroupMemberDAO;
import com.akarui.guren.database.dao.JobAssigneeDAO;
import com.akarui.guren.database.dao.JobDAO;
import com.akarui.guren.database.dao.RoleDAO;
import com.akarui.guren.database.dao.TransactionDAO;
import com.akarui.guren.database.dao.UserDAO;
import com.akarui.guren.database.entity.Group;
import com.akarui.guren.database.entity.GroupMember;
import com.akarui.guren.database.entity.Job;
import com.akarui.guren.database.entity.JobAssignee;
import com.akarui.guren.database.entity.Permission;
import com.akarui.guren.database.entity.Role;
import com.akarui.guren.database.entity.RolePermission;
import com.akarui.guren.database.entity.Transaction;
import com.akarui.guren.database.entity.User;

@Database(entities = {
                        User.class,
                        Group.class,
                        GroupMember.class,
                        Transaction.class,
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
    public abstract GroupDAO groupDAO();
    public abstract GroupMemberDAO groupMemberDAO();
    public abstract JobAssigneeDAO jobAssigneeDAO();
    public abstract JobDAO jobDAO();
    public abstract RoleDAO roleDAO();
    public abstract TransactionDAO transactionDAO();

    public static GurenDatabase getInstance(Context applicationContext)
    {
        if (instance == null){
            instance = Room.databaseBuilder(applicationContext, GurenDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
