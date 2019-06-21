package com.example.sprint14challenge.service

import com.example.sprint14challenge.model.Role

interface RoleService {
    fun findAll(): List<Role>

    fun findRoleById(id: Long): Role

    fun delete(id: Long)

    fun save(role: Role): Role

    fun findByName(name: String): Role
}