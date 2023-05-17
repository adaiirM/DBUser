package com.example.dbuser.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.dbuser.R
import com.example.dbuser.userDbHelper.InterfaceUserDao.IOperationUserDao
import com.example.dbuser.userDbHelper.userDao.OperationUserDao
import com.example.dbuser.view.interfaces.IEliminar
import com.google.android.material.button.MaterialButton

class EliminarActivity : AppCompatActivity(), IEliminar {
    private lateinit var btnAtras : MaterialButton
    private lateinit var btnEliminar : MaterialButton
    private lateinit var edtIdUsuario : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)
        btnAtras = findViewById(R.id.btn_atras)
        btnEliminar = findViewById(R.id.btn_eliminar)
        edtIdUsuario = findViewById(R.id.edtIdUsuario)
        botones()
    }

    fun botones(){
        btnAtras.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnEliminar.setOnClickListener {
            if (edtIdUsuario.text.toString() != ""){
                val id = (edtIdUsuario.text.toString()).toLong()
                if(eliminarUsurio(id) > 0){
                    Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                    edtIdUsuario.text = ""
                }else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Ingresa un ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun eliminarUsurio(id: Long): Int {
        val iOperationUserDao: IOperationUserDao = OperationUserDao(applicationContext)
        return iOperationUserDao.deleteUser(id)
    }

}