package com.example.greetingcard.data

import android.content.Context
import android.content.SharedPreferences

class Repository(context: Context) {
    private val key = "KEY_SHARED_PREFS"

    private val keyName = "name"
    private val keyTitle = "title"
    private val keyText = "text"
    private val keyBgRes = "bgRes"
    private val keyIconRes = "iconRes"

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun saveToSharedPrefs(data: UIData) {
        with(sharedPref.edit()) {
            putString(keyName, data.name)
            putString(keyTitle, data.title)
            putString(keyText, data.text)
            putInt(keyBgRes, data.bgRes)
            putInt(keyIconRes, data.iconRes)
            apply()
        }
    }

    fun getFromSharedPrefs(): UIData {
        val name = sharedPref.getString(keyName, "").toString()
        val title = sharedPref.getString(keyTitle, "").toString()
        val text = sharedPref.getString(keyText, "").toString()
        val bgRes = sharedPref.getInt(keyBgRes, 0)
        val iconRes = sharedPref.getInt(keyIconRes, 0)
        return UIData(name, title, text, bgRes, iconRes)
    }

}