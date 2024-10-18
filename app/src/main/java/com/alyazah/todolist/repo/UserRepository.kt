package com.alyazah.todolist.repo

import androidx.lifecycle.LiveData
import com.alyazah.todolist.dao.UserDao
import com.alyazah.todolist.entity.User

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }
}
