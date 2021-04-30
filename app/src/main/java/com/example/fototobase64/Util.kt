package com.example.fototobase64

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import com.google.gson.Gson
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.ByteArrayOutputStream


fun decodeImage(input: String?): Bitmap {
    val decodedByte: ByteArray = Base64.decode(input, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    return bitmap
}

fun getStringImage(bmp: Bitmap): String {
    val baos = ByteArrayOutputStream()
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val imageBytes = baos.toByteArray()
    return Base64.encodeToString(imageBytes, Base64.DEFAULT)
}
fun uriToBitmap(uri: Uri,context:Context): Bitmap{
    return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
}

fun parceJson(json: String): User{
//    val obj = JSONParser().parse(json)
//    val jo = obj as JSONObject
//    Log.d("MyLog", "$jo")
//    val name = jo["имя"] as String
//    val age = jo["возраст"] as Long
//    val user = User(name, age.toInt())
    val gson = Gson()
    val user = gson.fromJson(json, User::class.java)
    return user
}
fun toJsom(user: User): String{
    val gson = Gson()
    val json = gson.toJson(user)
    return json
}
