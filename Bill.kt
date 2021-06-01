package com.example.logistics

class Bill {
    var s_tel:String
    var s_name:String
    var r_tel:String
    var r_name:String
    //lateinit var s_city:String
    var r_city:String
    //lateinit var r_address:String
    var kind:String
    var num:String
    var s_cost:String
    var r_cost:String

    constructor( s_tel:String,s_name:String,r_tel:String,
                 r_name:String,r_city:String,kind:String,num:String,s_cost:String,r_cost:String){
        this.s_tel = s_tel
        this.s_name = s_name
        this.r_tel = r_tel
        this.r_name = r_name
        this.r_city = r_city
        this.kind = kind
        this.num = num
        this.s_cost = s_cost
        this.r_cost = r_cost
    }

}

