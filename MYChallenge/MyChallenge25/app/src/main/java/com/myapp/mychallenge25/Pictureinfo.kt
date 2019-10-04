package com.myapp.mychallenge25

class Pictureinfo {


    var path: String
    var displayName: String
    var dateAdded: String

    constructor(path :String, displayName :String, dateAdded:String ){
        this.path = path
        this.displayName = displayName
        this.dateAdded = dateAdded
    }

    override fun toString() :String{
        return ":PictureInfo{" +
                "path='" + path + '\'' +
                ", displayName='" + displayName + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                '}'
    }
}