package com.isra.sprint13.service

import com.isra.sprint13.model.Role

interface RoleService {
    fun findAll(): List<Role>

    fun findRoleById(id: Long): Role

    fun delete(id: Long)

    fun save(role: Role): Role
}