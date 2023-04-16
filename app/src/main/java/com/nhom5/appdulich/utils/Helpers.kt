package com.nhom5.appdulich.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.nhom5.appdulich.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Helpers @Inject constructor(
    @ApplicationContext private val _context: Context,
    private val _notification: Notification
) {
    private var _dialogLoading: Dialog? = null

    fun showAlertDialog(
        title: String? = _context.getString(R.string.lbl_error),
        msg: String,
        context: Context,
        onClick: (()->Unit)? = null
    ) {
        androidx.appcompat.app.AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(msg)
            setIcon(R.mipmap.ic_launcher)
            setPositiveButton(R.string.btn_ok) { dialog, _ -> onClick?.invoke() ?: dialog.cancel() }
            setCancelable(false)
            show()
        }
    }


    fun showProgressLoading(context: Context) {
        if (_dialogLoading?.isShowing == true) {
            _dialogLoading?.dismiss()
            _dialogLoading = null
        }
        _dialogLoading = Dialog(context).apply {
            setContentView(R.layout.progress)
            setCancelable(false)
            show()
        }
    }

    fun dismissProgress() {
        if (_dialogLoading?.isShowing == true) {
            _dialogLoading?.dismiss()
        }
    }

    fun showToast(message: String?) {
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show()
    }

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).replace(",", ".").toDouble()
    }

    fun showNotification(str: String) {
        val manager = _context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Const.ID_NOTIFICATION, _notification.createNotification(str))
    }

    fun showDatePickerDialog(
        context: Context,
        action: (str: String) -> Unit
    ) {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val date = calendar[Calendar.DATE]

        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                Log.d("AAA", "showDatePickerDialog: ${"$year-${month + 1}-$dayOfMonth"}")
                action("$year-${month + 1}-$dayOfMonth")
            },
            year,
            month,
            date
        )
        datePickerDialog.show()
    }
}