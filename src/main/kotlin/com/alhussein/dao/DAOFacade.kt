package com.alhussein.dao

import com.alhussein.models.User

interface DAOFacade {
    suspend fun allUsers(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(user: User): User?
    suspend fun editUser(id: Int, name: String, status: Int): Boolean
    suspend fun deleteUser(id: Int): Boolean
}
