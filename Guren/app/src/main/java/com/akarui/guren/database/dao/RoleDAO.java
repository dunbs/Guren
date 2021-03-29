package com.akarui.guren.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.akarui.guren.dto.RoleDTO;

import java.util.List;

/**
 * Read-only <br>
 *     Chỉ hay đổi dữ liệu và permission của role thông qua server,
 *     client không được thay đổi thông tin về role
 */
@Dao
public interface RoleDAO {
    @Query("SELECT role.id, role.name, permission.id as permission_id, permission.name as permission_name FROM role " +
            "JOIN role_permission ON role.id = role_permission.role_id " +
            "JOIN permission ON role_permission.permission_id = permission.id " +
            "WHERE role_id = :roleId")
    List<RoleDTO> getRolePermission(int roleId);
}
