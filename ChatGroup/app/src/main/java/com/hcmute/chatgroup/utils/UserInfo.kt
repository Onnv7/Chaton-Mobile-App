package com.hcmute.chatgroup.utils

import android.content.Context
import android.content.SharedPreferences

class UserInfo(val context : Context) {
    private val sharedPreferences = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE)

    fun getInfo(key : String): String? {
        return sharedPreferences.getString(key, "")
    }

}