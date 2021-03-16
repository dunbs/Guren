package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.akarui.guren.database.entity.Group;
import com.akarui.guren.database.entity.GroupMember;
import com.akarui.guren.database.entity.User;

import java.util.List;

@Dao
public interface GroupMemberDAO extends BaseDAO<GroupMember> {
    
    @Query("SELECT * FROM user u " +
            "JOIN group_member gm ON gm.user_id = u.id " +
            "WHERE gm.group_id = group_id")
    List<User> loadGroupMembers(int groupId);
    
    @Query("SELECT * FROM `group` g " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE gm.user_id = :userId")
    List<Group> loadUserGroups(int userId);
    
    @Query("SELECT g.* FROM `group` g " +
            "JOIN group_member gm ON gm.group_id = g.id " +
            "WHERE name LIKE :groupName AND gm.user_id = :userId")
    List<Group> findGroupsOfUserByName(String groupName, int userId);
    
    @Query("SELECT * FROM user u " +
            "JOIN group_member gm ON gm.user_id = u.id " +
            "WHERE gm.group_id = :groupId AND gm.role_id = :roleId")
    List<User> findMemberOfGroupByRole(int groupId, int roleId);
}
