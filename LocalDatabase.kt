package com.example.logistics

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LocalDatabase(val context: Context, name: String, version: Int) :
        SQLiteOpenHelper(context,name,null,version) {

    private val createExpressBill = "create table ExpressBill("+
            "id integer primary key autoincrement,"+
            "s_tel text,"+
            "s_name text,"+
            "r_tel text,"+
            "r_name text,"+
            "s_city text,"+
            "r_city text,"+
            "r_address text,"+
            "kind text,"+
            "num integer,"+
            "s_cost integer,"+
            "r_cost integer)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createExpressBill)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}
