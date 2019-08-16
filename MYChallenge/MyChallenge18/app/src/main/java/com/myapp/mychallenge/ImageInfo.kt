package com.myapp.mychallenge

class ImageInfo {
    var path :String? = null
    var displayName:String? = null
    var dateAdded :String? =null

    constructor(path : String, displayName : String, dateAdded : String){
        this.path = path
        this.displayName = displayName
        this.dateAdded = dateAdded
    }

    override fun toString(): String {
        return "ImageInfo{" +
                "path='" + path + '\'' +
                ", displatNaem = '" + displayName + '\''
                ", dateAdded = " + dateAdded + '\'' +
                        '}'
    }
}