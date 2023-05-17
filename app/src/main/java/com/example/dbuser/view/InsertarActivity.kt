package com.example.dbuser.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.dbuser.R
import com.example.dbuser.view.interfaces.Iinsertar
import com.example.dbuser.dtoUser.UserDto
import com.example.dbuser.userDbHelper.InterfaceUserDao.IOperationUserDao
import com.example.dbuser.userDbHelper.userDao.OperationUserDao
import com.google.android.material.button.MaterialButton

class InsertarActivity : AppCompatActivity(), Iinsertar {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextNumero: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var btnGuardaar : MaterialButton
    private lateinit var btnCancelar : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar)
        iniciarControler()
        guardarUsuario()
        cancelar()

    }

    fun iniciarControler(){
        editTextNombre = findViewById(R.id.edtName)
        editTextApellidos = findViewById(R.id.edtLastName)
        editTextNumero = findViewById(R.id.edtPhoneNumber)
        editTextEmail = findViewById(R.id.edtUserEmail)
        btnGuardaar = findViewById(R.id.btn_insertar)
        btnCancelar = findViewById(R.id.btn_cancelar)
    }

    fun guardarUsuario(){
        btnGuardaar.setOnClickListener {
            if(camposCorrectos()){
                val user = UserDto(editTextNombre.text.toString(), editTextApellidos.text.toString(),
                    editTextNumero.text.toString(), editTextEmail.text.toString())
                val r = insrtarUsuario(user)
                if(r > 0){
                    Toast.makeText(this, "Insercion correcta", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } else
                    Toast.makeText(this, "Insercion incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun cancelar(){
        btnCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun limpiarCampos(){
        editTextNombre.setText("")
        editTextApellidos.setText("")
        editTextNumero.setText("")
        editTextEmail.setText("")
    }

    fun camposCorrectos(): Boolean{
        var b = true
        if(editTextNombre.text.toString() == "" || editTextApellidos.text.toString() == ""
            || editTextNumero.text.toString() == "" || editTextEmail.text.toString() == ""){
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                b = false
        }
        return b
    }

    override fun insrtarUsuario(userDto: UserDto): Long {
        val iOperationUserDao: IOperationUserDao = OperationUserDao(applicationContext)
        return iOperationUserDao.insertUser(userDto)
    }
}