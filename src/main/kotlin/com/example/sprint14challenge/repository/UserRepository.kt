package com.example.sprint14challenge.repository

import com.example.sprint14challenge.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User
}
