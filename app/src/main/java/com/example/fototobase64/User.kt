package com.example.fototobase64

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("имя") val name: String,
    @SerializedName("возраст") val age: Int
){
    override fun toString(): String {
        return "name $name, age $age"
    }
}