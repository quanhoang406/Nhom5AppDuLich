package com.nhom5.appdulich.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.nhom5.appdulich.data.model.Account
import javax.inject.Inject

class SharePrefs @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) {
    fun saveAccount(account: Account) {
        editor.putString(Const.KEY_ACCOUNT, Gson().toJson(account)).commit()
    }

    fun checkAccount(): Boolean {
        val data = sharedPreferences.getString(Const.KEY_ACCOUNT, "")
        if (data == "") {
            return false
        }
        return true
    }

    suspend fun getAccount(): Account =
        Gson().fromJson(sharedPreferences.getString(Const.KEY_ACCOUNT, ""), Account::class.java)

    fun removeAccount() {
        editor.remove(Const.KEY_ACCOUNT).commit()
    }

    fun saveStarted(boolean: Boolean) {
        editor.putBoolean(Const.KEY_STARTED, boolean).commit()
    }

    fun getStarted() = sharedPreferences.getBoolean(Const.KEY_STARTED, false)
}