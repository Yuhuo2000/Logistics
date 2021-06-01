package com.example.logistics

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(val context: Context, name: String,version: Int) :
    SQLiteOpenHelper(context,name,null,version){
    /**
     * id（学号也就是账号）
     * name（姓名）
     * major（专业班级）
     * password（密码）
     */
    private val createAccount = "create table Account("+
            "id integer primary key,"+
            "name text,"+
            "major text,"+
            "password text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createAccount)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        
    }

}
