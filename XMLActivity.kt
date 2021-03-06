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
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader


class XMLActivity : BaseActivity() {
    private val dataList = ArrayList<String>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        val listView: ListView = findViewById(R.id.tv)
        val urlInput : String = intent.getStringExtra("URLINPUTXML").toString()
        this.parseXMLWithXMLObject(urlInput)
        val adapter = ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter

    }

    private fun parseXMLWithXMLObject(xmlData: String) {
        try{
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(StringReader(xmlData))
            var currentType = parser.eventType
            var waybillNo = ""
            var consignor = ""
            var consignorPhoneNumber = ""
            var consignee = ""
            var consigneePhoneNumber = ""
            var transportationDepartureStation = ""
            var transportationArrivalStation = ""
            var goodsDistributionAddress = ""
            var goodsName = ""
            var numberOfPackages = ""
            var freightPaidByTheReceivingParty = ""
            var freightPaidByConsignor = ""

            while(currentType != XmlPullParser.END_DOCUMENT){
                when(currentType){
                    XmlPullParser.START_TAG ->
                        when(parser.name){
                            "waybillNo" -> waybillNo = parser.nextText()
                            "consignor" -> consignor = parser.nextText()
                            "consignorPhoneNumber" -> consignorPhoneNumber = parser.nextText()
                            "consignee" -> consignee = parser.nextText()
                            "consigneePhoneNumber" -> consigneePhoneNumber = parser.nextText()
                            "transportationDepartureStation" -> transportationDepartureStation = parser.nextText()
                            "transportationArrivalStation" -> transportationArrivalStation = parser.nextText()
                            "goodsDistributionAddress" -> goodsDistributionAddress = parser.nextText()
                            "goodsName" -> goodsName = parser.nextText()
                            "numberOfPackages" -> numberOfPackages = parser.nextText()
                            "freightPaidByTheReceivingParty" -> freightPaidByTheReceivingParty = parser.nextText()
                            "freightPaidByConsignor" -> freightPaidByConsignor = parser.nextText()
                        }
                    XmlPullParser.END_TAG ->
                        if(parser.name == "waybillRecord"){
                            dataList.add("No.$waybillNo \n " +
                                    "?????????:$consignor    ???????????????:$consignorPhoneNumber \n " +
                                    "?????????:$consignee    ???????????????:$consigneePhoneNumber \n " +
                                    "??????:$transportationDepartureStation     ??????:$transportationArrivalStation \n " +
                                    "??????????????????:$goodsDistributionAddress \n" +
                                    "????????????:$goodsName    ??????:$numberOfPackages \n " +
                                    "????????????:$freightPaidByTheReceivingParty     ????????????:$freightPaidByConsignor \n " +
                                    "********************************************")
                        }
                }
                currentType = parser.next()
            }
        }catch(e:Exception){
            e.printStackTrace()
        }
    }

}




