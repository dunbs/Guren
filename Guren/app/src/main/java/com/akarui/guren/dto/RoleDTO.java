package com.akarui.guren.dto;

import androidx.room.Embedded;

import com.akarui.guren.database.entity.Permission;

import lombok.Data;

@Data
public class RoleDTO {
    private int id;
    private String name;
    @Embedded(prefix = "permission_")
    private Permission permission;
}
