package com.myapp.mychallenge22

class Bookinfo {

    var name : String? = null
    var writer : String? = null
    var contents : String? = null

    constructor(name :String, writer : String, contents : String){
        this.name = name
        this.writer = writer
        this.contents = contents
    }

    override fun toString(): String {

        return "BookInfo{" +
                "name='" + name + '\'' +
                ", author='" + writer + '\'' +
                ", contens='" + contents + '\'' +
                '}'
    }
}