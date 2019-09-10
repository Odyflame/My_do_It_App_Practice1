package com.myapp.mychallenge22_again

class BookInfo {

    var name : String? =null
    var author :String? = null
    var contents : String? = null

    constructor(name :String, author :String, contents  :String){
        this.name = name
        this.author = author
        this.contents = contents
    }

    override fun toString(): String {

        return "BookInfo{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", contents='" + contents + '\'' +
                '}'
    }
}