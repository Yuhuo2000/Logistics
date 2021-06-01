package com.example.logistics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.net.URL
import kotlin.concurrent.thread
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginActivity : BaseActivity() {

    private var urlXML = URL("http://60.12.122.142:6080/simulated-Waybills-db.xml")
    private var urlJSON = URL("http://60.12.122.142:6080/simulated-Waybills-db.json")
    var urlInputXML = ""
    var urlInputJSON = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        thread()

        val addButton: Button = findViewById(R.id.addButton)
        val switchButton: Button = findViewById(R.id.switchButton)
        val localButton:Button = findViewById(R.id.localButton)
        val xmlButton:Button = findViewById(R.id.xmlButton)
        val jsonButton:Button = findViewById(R.id.jsonButton)
        val outButton:Button = findViewById(R.id.outButton)




        //点击添加订单到本地
        addButton.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }
        //点击查询本地运单会跳到输出界面
        localButton.setOnClickListener {
            val intent = Intent(this,QueryActivity::class.java)
            startActivity(intent)
        }
        //点击xml订单查询会跳转到xml界面
        xmlButton.setOnClickListener {
            val intent = Intent(this, XMLActivity::class.java)
            intent.putExtra("URLINPUTXML", urlInputXML)
            startActivity(intent)
        }
        //点击json订单查询会跳转到json界面
        jsonButton.setOnClickListener {
            val intent = Intent(this, JSONActivity::class.java)
            intent.putExtra("URLINPUTJSON", urlInputJSON)
            startActivity(intent)
        }
        //点击切换用户会回到主界面
        switchButton.setOnClickListener {
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        //点击退出会退出程序
        outButton.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }

    private fun thread() {
        val thread1 = object : Thread() {
            override fun run() {
                super.run()
                val client = OkHttpClient()
                val requestXML = Request.Builder().get()
                    .url(urlXML)
                    .build()

                val responseXML = client.newCall(requestXML).execute()
                urlInputXML = responseXML.body?.string().toString()
            }
        }

        val thread2 = object : Thread() {
            override fun run() {
                super.run()
                val client = OkHttpClient()

                val requestJSON = Request.Builder().get()
                    .url(urlJSON)
                    .build()

                val responseJSON = client.newCall(requestJSON).execute()
                urlInputJSON = responseJSON.body?.string().toString()
            }
        }
        thread1.start()
        thread2.start()
    }
}
