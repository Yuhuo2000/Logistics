package com.example.logistics

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class JSONActivity : BaseActivity() {
    private val dataList = ArrayList<String>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)


        val listView: ListView = findViewById(R.id.tv)
        val urlInput = intent.getStringExtra("URLINPUTJSON").toString()

        parseJSONWithJSONObject(urlInput)
        val adapter = ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter
        }

    private fun parseJSONWithJSONObject(jsonData: String) {
        try {
            val jsonText = JSONObject(jsonData)
            val context = jsonText.getString("waybillRecord")
            val jsonArray = JSONArray(context)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val waybillNo = jsonObject.getString("waybillNo")
                val consignor = jsonObject.getString("consignor")
                val consignorPhoneNumber = jsonObject.getString("consignorPhoneNumber")
                val consignee = jsonObject.getString("consignee")
                val consigneePhoneNumber = jsonObject.getString("consigneePhoneNumber")
                val transportationDepartureStation =
                    jsonObject.getString("transportationDepartureStation")
                val transportationArrivalStation =
                    jsonObject.getString("transportationArrivalStation")
                val goodsDistributionAddress = jsonObject.getString("goodsDistributionAddress")
                val goodsName = jsonObject.getString("goodsName")
                val numberOfPackages = jsonObject.getString("numberOfPackages")
                val freightPaidByTheReceivingParty =
                    jsonObject.getString("freightPaidByTheReceivingParty")
                val freightPaidByConsignor = jsonObject.getString("freightPaidByConsignor")
                dataList.add(
                    "No.$waybillNo \n " +
                            "发货人:$consignor    发货人电话:$consignorPhoneNumber \n " +
                            "收货人:$consignee    收货人电话:$consigneePhoneNumber \n " +
                            "发站:$transportationDepartureStation    到站:$transportationArrivalStation \n " +
                            "货物配送地址:$goodsDistributionAddress \n" +
                            "货物名称:$goodsName      件数:$numberOfPackages \n " +
                            "已付运费:$freightPaidByTheReceivingParty    到付运费:$freightPaidByConsignor \n " +
                            "********************************************"
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
