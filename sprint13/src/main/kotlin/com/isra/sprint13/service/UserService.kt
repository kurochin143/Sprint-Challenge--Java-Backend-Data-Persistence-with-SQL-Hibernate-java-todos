package com.isra.sprint13.service

import com.isra.sprint13.model.User

interface UserService {

    fun findCurrent(): User

    fun findAll(): List<User>

    fun findUserById(id: Long): User

    fun delete(id: Long)

    fun save(user: User): User

    fun update(user: User, id: Long): User
}