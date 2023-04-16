package com.nhom5.appdulich.data.body.account

data class ChangePasswordBody(val email: String, val password: String, val newPassword: String)