package com.nhom5.appdulich.extension

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
import com.nhom5.appdulich.R
import com.nhom5.appdulich.utils.Validations
import kotlin.math.abs

@BindingAdapter("checkError")
fun checkError(editText: EditText, str: String) {
    val validation = Validations(editText.context)

    editText.doAfterTextChanged {
        editText.error = when (editText.id) {
            R.id.edtEmail -> validation.isEmailValid(it.toString())
            R.id.edtPassword -> validation.isPasswordValid(it.toString())
            R.id.edtName -> validation.isValidName(it.toString())
            R.id.edtPhone -> validation.isValidPhoneNumber(it.toString())
            else -> null
        }
    }
}

@BindingAdapter("confirm_password")
fun confirmPassword(editText: EditText, str: String) {
    val validation = Validations(editText.context)

    editText.doAfterTextChanged {
        editText.error = validation.isConfirmPass(it.toString(), str)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setTextMount")
fun setTextMount(textView: TextView,str : String){
    val list = str.split("-")
    textView.text = "TH ${list[1]}"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setTextDate")
fun setTextDate(textView: TextView,str : String){
    val list = str.split("-")
    textView.text = "TH ${list[2].substring(0,2)}"
}

fun View.navigate(action: Int, bundle: Bundle? = null) {
    Navigation.findNavController(this).navigate(action, bundle)
}

fun Toolbar.setUpToolbar(icon: Int? = null, onclick: () -> Unit) {
    setNavigationIcon(icon ?: R.drawable.ic_back)
    setNavigationOnClickListener {
        onclick()
    }
}

@BindingAdapter("setImageUrl")
fun setUrlImage(imageView: ImageView, src: String?) {
    Glide.with(imageView.context).load(src).error(R.drawable.img_error).placeholder(R.drawable.img_city)
        .into(imageView)
}

fun AppBarLayout.listener(onSuccess: () -> Unit, onFail: () -> Unit) {
    addOnOffsetChangedListener(BaseOnOffsetChangedListener<AppBarLayout> { appBarLayout, i ->
        if (abs(i) >= appBarLayout!!.totalScrollRange) {
            setBackgroundColor(context.getColor(R.color.colorPrimary))
            onSuccess()
        } else {
            setBackgroundColor(context.getColor(R.color.alpha))
            onFail()
        }
    })
}

