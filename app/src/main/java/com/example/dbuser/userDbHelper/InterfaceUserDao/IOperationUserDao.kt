package com.example.dbuser.userDbHelper.InterfaceUserDao

import com.example.dbuser.dtoUser.UserDto

interface IOperationUserDao {
     fun insertUser(userDto: UserDto): Long
     fun updateUser(userDto: UserDto): Int
     fun selectUsers(): ArrayList<UserDto>
     fun selectUserName(name: String): ArrayList<UserDto>
     fun deleteUser(id: Long): Int

}