package com.androiddemo.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context) {

    private var _prefs: SharedPreferences = context.getSharedPreferences(
        "AndroidDemo",
        Context.MODE_PRIVATE
    )
    private var _editor: SharedPreferences.Editor = this._prefs.edit()

    fun getString(key: String, defaultValue: String): String? {
        return this._prefs.getString(key, defaultValue)
    }

    fun setString(key: String, value: String) {
        this._editor.putString(key, value)
    }

    fun getBoolean(key: String, defaultValue: Boolean?): Boolean? {
        return this._prefs.getBoolean(key, defaultValue!!)
    }

    fun setBoolean(key: String, value: Boolean?) {
        this._editor.putBoolean(key, value!!)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return this._prefs.getInt(key, defaultValue)
    }

    fun setInt(key: String, value: Int) {
        this._editor.putInt(key, value)
    }

    fun clearAll() {
        this._editor.clear().commit()

    }

    fun removeOneItem(key: String) {
        this._editor.remove(key)
    }

    fun save() {
        this._editor.commit()
    }

}