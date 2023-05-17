package com.example.dbuser.userDbHelper.contrato

import android.provider.BaseColumns

object UserDbContract {

    object UserEntry: BaseColumns{
        const val TABLE_NAME = "Usuarios"
        //const val COLUMN_USER_ID = "Id_user"
        const val COLUMN_USER_NAME = "Nombre"
        const val COLUMN_LAST_NAME = "Apellido"
        const val COLUMN_PHONE_NUMBER = "Telefono"
        const val COLUMN_USER_EMAIL = "Correo"
    }

    object Entry {
       internal const val  SQL_CREATE_ENTRIES_USER = "CREATE TABLE \"${UserEntry.TABLE_NAME}\" (\n" +
                "\t\"${BaseColumns._ID}\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"${UserEntry.COLUMN_USER_NAME}\"\tTEXT NOT NULL,\n" +
                "\t\"${UserEntry.COLUMN_LAST_NAME}\"\tTEXT NOT NULL,\n" +
                "\t\"${UserEntry.COLUMN_PHONE_NUMBER}\"\tTEXT UNIQUE,\n" +
                "\t\"${UserEntry.COLUMN_USER_EMAIL}\"\tTEXT UNIQUE,\n" +
                "\tPRIMARY KEY(\"${BaseColumns._ID}\" AUTOINCREMENT)\n" +
                ");"

        const val SQL_DELETE_ENTRIES_USER = "DROP TABLE IF EXISTS ${UserEntry.TABLE_NAME};"
    }


    object Querys {
        const val SQL_QUERY_SELECT_USER = "SELECT * FROM ${UserEntry.TABLE_NAME};"

    }





}