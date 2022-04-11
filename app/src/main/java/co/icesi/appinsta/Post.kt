package co.icesi.appinsta

import android.graphics.Bitmap

class Post {

    var picture : Bitmap
    var user : String
    var caption : String
    var place : String
    var date : String

    constructor(picture : Bitmap, user : String, caption : String, place : String, date : String){
        this.picture = picture
        this.user = user
        this.caption = caption
        this.place = place
        this.date= date
    }
}