package com.example.dbuser.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbuser.R
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var btnInsertar: MaterialButton
    private lateinit var btnActualizar: MaterialButton
    private lateinit var btnBuscarTodos: MaterialButton
    private lateinit var btnBuscarNombre: MaterialButton
    private lateinit var btnEliminar: MaterialButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarC()
        botones()
    }

    fun iniciarC(){
        btnInsertar = findViewById(R.id.btn_insertar)
        btnActualizar = findViewById(R.id.btn_actualizar)
        btnBuscarTodos = findViewById(R.id.btn_buscar_todos)
        btnBuscarNombre = findViewById(R.id.btn_buscar_nombre)
        btnEliminar = findViewById(R.id.btn_eliminar)
    }

    fun botones(){
        btnInsertar.setOnClickListener {
            val intent = Intent(this, InsertarActivity::class.java)
            startActivity(intent)
        }
        btnBuscarTodos.setOnClickListener {
            val intent = Intent(this, BuscarTodoActivity::class.java)
            startActivity(intent)
        }

        btnActualizar.setOnClickListener {
            val intent = Intent(this, ActualizarActivity::class.java)
            startActivity(intent)
        }

        btnBuscarNombre.setOnClickListener {
            val intent = Intent(this, BuscarNombreActivity::class.java)
            startActivity(intent)
        }
        btnEliminar.setOnClickListener {
            val intent = Intent(this, EliminarActivity::class.java)
            startActivity(intent)
        }

    }
}