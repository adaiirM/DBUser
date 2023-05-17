package com.example.dbuser.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbuser.R
import com.example.dbuser.adapters.AdapterUser
import com.example.dbuser.dtoUser.UserDto
import com.example.dbuser.userDbHelper.InterfaceUserDao.IOperationUserDao
import com.example.dbuser.userDbHelper.userDao.OperationUserDao
import com.google.android.material.button.MaterialButton

class BuscarNombreActivity : AppCompatActivity() {
    private lateinit var edtName : EditText
    private lateinit var btnBuscar : MaterialButton
    private lateinit var btnCancelar : MaterialButton
    private lateinit var recView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_nombre)
        iniciarControles()
        botones()
    }

    fun iniciarControles(){
        edtName = findViewById(R.id.edtName)
        btnBuscar = findViewById(R.id.btn_buscar)
        btnCancelar = findViewById(R.id.btn_cancelar)
        recView = findViewById(R.id.recyclerView)
    }

    fun botones(){
        btnBuscar.setOnClickListener {
            val nombre = edtName.text.toString()

            val adaptador = AdapterUser(buscar(nombre)){
                Log.i("Prueba de usuarios", "Pulsando el elemento ${it.name}")
            }
            recView.setHasFixedSize(true)

            recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            recView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

            recView.itemAnimator = DefaultItemAnimator()

            recView.adapter = adaptador
        }

        btnCancelar.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun buscar(nombre : String) : ArrayList<UserDto>{
        val iOperationUserDao : IOperationUserDao = OperationUserDao(applicationContext)
        return iOperationUserDao.selectUserName(nombre)
    }
}