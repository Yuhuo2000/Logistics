package com.example.logistics

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class QueryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var billList = ArrayList<String>()

        val printHelper = LocalDatabase(this,"ExpressBill",1)
        val db = printHelper.readableDatabase
        val cursor =db.rawQuery("select * from ExpressBill",null)
        while (cursor.moveToNext()){
            val s_name = cursor.getString( cursor.getColumnIndex("s_name") )
            val s_tel = cursor.getString( cursor.getColumnIndex("s_tel") )
            val r_tel = cursor.getString( cursor.getColumnIndex("r_tel") )
            val r_name = cursor.getString( cursor.getColumnIndex("r_name") )
            val r_city = cursor.getString( cursor.getColumnIndex("r_city") )
            val kind = cursor.getString( cursor.getColumnIndex("kind") )
            val num = cursor.getString( cursor.getColumnIndex("num") )
            val s_cost = cursor.getString( cursor.getColumnIndex("s_cost") )
            val r_cost = cursor.getString( cursor.getColumnIndex("r_cost") )
            var bill = "发货人：$s_name    发货号码：$s_tel \n" +
                       "收货人：$r_name    收货号码：$r_tel \n" +
                       "收货城市：$r_city  货物类别：$kind \n" +
                       "货物数量：$num   已付运费：$s_cost \n" +
                       "还需到付费用：$r_cost \n " +
                       "*********************************************"

            billList.add(bill)
        }
        setContentView(R.layout.list_layout)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,billList)
        val listView:ListView = findViewById(R.id.tv)
        listView.adapter = adapter
    }
}
