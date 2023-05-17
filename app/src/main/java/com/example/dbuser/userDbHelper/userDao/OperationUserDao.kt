package com.example.dbuser.userDbHelper.userDao

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.dbuser.dtoUser.UserDto
import com.example.dbuser.userDbHelper.InterfaceUserDao.IOperationUserDao
import com.example.dbuser.userDbHelper.UserDbHelper
import com.example.dbuser.userDbHelper.contrato.UserDbContract.UserEntry
import com.example.dbuser.userDbHelper.contrato.UserDbContract.Querys

class OperationUserDao(private var context: Context) : UserDbHelper(context), IOperationUserDao {
    private val userDbHelper = UserDbHelper(context)

    override fun insertUser(userDto: UserDto): Long {
        var id: Long = 0
        try {
            val db = userDbHelper.writableDatabase
            //Creamos un map de valores donde el nombre de cada columna es la clave
            val values = ContentValues().apply {
                put(UserEntry.COLUMN_USER_NAME, userDto.name)
                put(UserEntry.COLUMN_LAST_NAME, userDto.lastName)
                put(UserEntry.COLUMN_PHONE_NUMBER, userDto.phoneNumber)
                put(UserEntry.COLUMN_USER_EMAIL, userDto.userEmail)
            }
            id = db.insert(UserEntry.TABLE_NAME, null, values)
        } catch (ex: Exception) {
            Log.e("Ha ocurrido un error: ", ex.toString())
        }
        return id
    }

    //Actualizar los campos de un registro
    override fun updateUser(userDto: UserDto): Int {
        var affectedRows = 0
        //Nuevos valores de la columna que serán actualizados
        val values = ContentValues().apply {
            put(UserEntry.COLUMN_USER_NAME,userDto.name)
            put(UserEntry.COLUMN_LAST_NAME,userDto.lastName)
            put(UserEntry.COLUMN_PHONE_NUMBER,userDto.phoneNumber)
            put(UserEntry.COLUMN_USER_EMAIL,userDto.userEmail)
        }
        val selection = "${UserEntry.COLUMN_USER_NAME} LIKE ?"
        val selectionArgs = arrayOf(userDto.lastName)
        try {
            val db = userDbHelper.writableDatabase
            affectedRows = db.update(UserEntry.TABLE_NAME,values,selection,selectionArgs)


        }catch (ex: Exception){
            Log.e("Error en la actualización", ex.toString())
        }
        return affectedRows
    }

    override fun selectUsers(): ArrayList<UserDto> {
        val userElement = ArrayList<UserDto>()
        try{
            val db  = userDbHelper.readableDatabase
            var userDto: UserDto
            val cursor = db.rawQuery(Querys.SQL_QUERY_SELECT_USER,null)
            while (cursor.moveToNext()){
                userDto = UserDto(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                userElement.add(userDto)
            }
            cursor.close()
        }catch (ex:java.lang.Exception){
            Log.e("Ha ocurrido un error en la consulta: ", ex.toString())
        }

        return userElement
    }

    override fun selectUserName(name: String): ArrayList<UserDto> {
        val listName = ArrayList<UserDto>()
        var userDto: UserDto
        //Creamos una proyección que especifica las columnas de la db que se van a utilizar
        val queryProjection =  arrayOf(UserEntry.COLUMN_USER_NAME,UserEntry.COLUMN_LAST_NAME,UserEntry.COLUMN_PHONE_NUMBER,UserEntry.COLUMN_USER_EMAIL)
        //Creamos las restricciiones para filtrar el resultado
        val selection = "${UserEntry.COLUMN_USER_NAME} = ?"
        val selectionArgs = arrayOf(name)
        //Cómo se ordenaran los resultados en el Cursor resultante
        val sortOrder = "${UserEntry.COLUMN_LAST_NAME} DESC"
        try{
            val db  = userDbHelper.readableDatabase
            val cursor = db.query(
                UserEntry.TABLE_NAME,
                queryProjection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            with(cursor){
                while (moveToNext()){
                   userDto = UserDto(
                       cursor.getString(0),
                       cursor.getString(1),
                       cursor.getString(2),
                       cursor.getString(3)
                   )
                    listName.add(userDto)
                }
            }

        }catch (ex: Exception){
            Log.e("Ha ocurrido un error al búscar elementos",ex.toString())
        }
        return listName
    }

    override fun deleteUser(id: Long): Int {
        //Definimos parte del query where
        val selection = "${UserEntry.TABLE_NAME}._id LIKE ?"
        //Espcificamos los argumentos restricciones
        val selectionArgs = arrayOf(id.toString())
        var deleteRow = 0
        try {
            val db = userDbHelper.readableDatabase
            //Lanzamos la sentencia sql
            deleteRow = db.delete(UserEntry.TABLE_NAME,selection,selectionArgs)

        }catch (ex: Exception){
            Log.e("Error al eliminar", ex.toString())
        }
        return deleteRow
    }



}