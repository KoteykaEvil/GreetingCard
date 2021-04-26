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
    private val keyAvatarRes = "avatarRes"

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun saveToSharedPrefs(data: UIDataModel) {
        with(sharedPref.edit()) {
            putString(keyName, data.name)
            putString(keyTitle, data.title)
            putString(keyText, data.text)
            putInt(keyBgRes, data.bgRes ?: 0)
            putInt(keyIconRes, data.iconRes ?: 0)
            putInt(keyAvatarRes, data.avatar ?: 0)
            apply()
        }
    }

    fun getFromSharedPrefs(): UIDataModel {
        val name = sharedPref.getString(keyName, "")
        val title = sharedPref.getString(keyTitle, "")
        val text = sharedPref.getString(keyText, "")
        val bgRes = sharedPref.getInt(keyBgRes, 0)
        val iconRes = sharedPref.getInt(keyIconRes, 0)
        val avatarRes = sharedPref.getInt(keyAvatarRes, 0)
        return UIDataModel(
            name = name,
            title = title,
            text = text,
            bgRes = bgRes,
            iconRes = iconRes,
            avatar = avatarRes
        )
    }

}