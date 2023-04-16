package com.nhom5.appdulich.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.response.DataResponse
import com.nhom5.appdulich.repositories.AccountRepository
import com.nhom5.appdulich.utils.Validations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingAccountViewModel @Inject constructor(
    private val _repository: AccountRepository,
    private val _validation: Validations,
    private val _application: Application
) :
    ViewModel() {
    var showError: ((String) -> Unit)? = null
    var loadingDialog: (() -> Unit)? = null

    val email = MutableLiveData("")
    val phone = MutableLiveData("")
    val birthDay = MutableLiveData("")
    val gender = MutableLiveData("")
    val name = MutableLiveData("")
    private val _id = MutableLiveData("")

    //fragment change password
    val oldPassword = MutableLiveData("")
    val newPassword = MutableLiveData("")
    val newPasswordAgain = MutableLiveData("")

    fun getAccount() = viewModelScope.launch {
        when (val value = _repository.getAccount()) {
            is DataResponse.Success -> {
                val account = value.data
                withContext(Dispatchers.Main) {
                    email.value = account.email.toString()
                    phone.value = account.phone.toString()
                    birthDay.value = account.age.toString()
                    gender.value = account.gender.toString()
                    name.value = account.name.toString()
                    _id.value = account.id.toString()
                }
            }
            is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
        }
    }

    fun updateProfile(onSuccess: () -> Unit) = viewModelScope.launch {
        _validation.updateProfile(
            _id.value.toString(),
            name.value.toString(),
            email.value.toString(),
            phone.value.toString(),
            birthDay.value.toString(),
            gender.value.toString()
        )?.let {
            loadingDialog?.invoke()
            when (val value = _repository.updateProfile(it)) {
                is DataResponse.Success -> when (value.data.statuscode) {
                    200 -> {
                        _repository.saveAccount(value.data.data!![0])
                        onSuccess()
                    }
                    else -> showError?.invoke(value.data.message.toString())
                }
                is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
            }
        } ?: showError?.invoke(_application.getString(R.string.lbl_update_profile_error))
    }

    fun logout(onSuccess: () -> Unit){
        _repository.removeAccountLocal()
        onSuccess()
    }

    //fragment change password
    fun changePassword(onSuccess: () -> Unit) = viewModelScope.launch {
        _validation.changePassword(
            email.value.toString(),
            oldPassword.value.toString(),
            newPassword.value.toString()
        )?.let {
            loadingDialog?.invoke()

            when (val value = _repository.changePassword(it)) {
                is DataResponse.Success ->
                    when (value.data.statuscode) {
                        200 -> onSuccess()
                        else -> showError?.invoke(value.data.message.toString())
                    }
                is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
            }
        } ?: showError?.invoke(_application.getString(R.string.lbl_error_password))
    }

    var setBirthDay: String = ""
        set(value) {
            birthDay.value = value
            field = value
        }

    var setGender: String = ""
        set(value) {
            gender.value = value
            field = value
        }
}