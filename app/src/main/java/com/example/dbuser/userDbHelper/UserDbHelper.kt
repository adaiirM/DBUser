package com.example.dbuser.userDbHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbuser.userDbHelper.contrato.UserDbContract.Entry.SQL_CREATE_ENTRIES_USER
import com.example.dbuser.userDbHelper.contrato.UserDbContract.Entry.SQL_DELETE_ENTRIES_USER

open class UserDbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES_USER)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES_USER)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "agenda.db"
    }

}