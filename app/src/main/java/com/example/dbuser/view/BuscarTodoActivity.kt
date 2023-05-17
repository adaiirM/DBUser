package com.example.dbuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbuser.R
import com.example.dbuser.view.interfaces.IBuscarTodo
import com.example.dbuser.adapters.AdapterUser
import com.example.dbuser.dtoUser.UserDto
import com.example.dbuser.userDbHelper.InterfaceUserDao.IOperationUserDao
import com.example.dbuser.userDbHelper.userDao.OperationUserDao


class BuscarTodoActivity : AppCompatActivity(), IBuscarTodo {
    private lateinit var recView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_todo)
        recView = findViewById(R.id.recyclerView)

        val users = buscarTodo()

        val adaptador = AdapterUser(users){
            Log.i("Prueba de usuarios", "Pulsando el elemento ${it.name}")
        }
        recView.setHasFixedSize(true)

        recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        recView.itemAnimator = DefaultItemAnimator()

        recView.adapter = adaptador

    }

    override fun buscarTodo(): ArrayList<UserDto> {
        val iOperationUserDao : IOperationUserDao = OperationUserDao(applicationContext)
        return iOperationUserDao.selectUsers()
    }
}