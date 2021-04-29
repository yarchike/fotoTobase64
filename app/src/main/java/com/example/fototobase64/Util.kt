package com.example.fototobase64

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
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