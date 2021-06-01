package com.example.logistics

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UserActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_layout)

        //  创建数据库并且往里面写入两个账号信息

        val mydbHelper = MyDatabase(this,"loginSystem.db",1)
        val db = mydbHelper.writableDatabase
        val values1 = ContentValues().apply {
            put("id",20184539)
            put("name","LiuRJ")
            put("major","CS1808")
            put("password","20184539")
        }
        db.insert("Account",null,values1)
        val values2 = ContentValues().apply {
            put("id",20184504)
            put("name","XiaT")
            put("major","CS1808")
            put("password","20184504")

        }
        db.insert("Account",null,values2)



        val loginButton: Button = findViewById(R.id.loginButton)
        val exitButton: Button = findViewById(R.id.exitButton)
        val username: EditText = findViewById(R.id.username)
        val password:EditText = findViewById(R.id.password)





        //点击登录按钮
        loginButton.setOnClickListener{
            var corrector = false
            val name = username.text.toString()
            val word = password.text.toString()
            val db1 = mydbHelper.readableDatabase
            val cursor =db1.rawQuery("select * from Account",null)

            while(cursor.moveToNext()){
                val index1 = cursor.getColumnIndex("id")
                val getID = cursor.getString(index1)
                val index2 = cursor.getColumnIndex("password")
                val getPASSWORD = cursor.getString(index2)
                if( name.equals(getID) && word.equals(getPASSWORD) ){
                    //如果账号密码正确的话
                    Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                    corrector = true
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }
            }

            if(!corrector){
                Toast.makeText(this,"账号或密码错误请重新登录！",Toast.LENGTH_SHORT).show()
                username.setText("")
                password.setText("")
            }

        }

        //点击退出按钮，直接退出程序
        exitButton.setOnClickListener {
            ActivityCollector.finishAll()
        }


    }
}
