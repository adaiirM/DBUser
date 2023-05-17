package com.example.dbuser.view.interfaces

import com.example.dbuser.dtoUser.UserDto

interface IBuscarTodo {
    fun buscarTodo(): ArrayList<UserDto>
}