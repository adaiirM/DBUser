package com.example.dbuser.view.interfaces

import com.example.dbuser.dtoUser.UserDto

interface Iinsertar {
    fun insrtarUsuario(userDto: UserDto): Long
}