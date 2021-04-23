package com.contact.biometric.activity


import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.contact.biometric.R
import com.contact.biometric.activity.MyProgress.alertDialog


object MyProgress {
    var alertDialog: Dialog? = null


        fun show(context: Activity) {
            val alert = AlertDialog.Builder(context)
            val mview: View = context.layoutInflater.inflate(R.layout.dialog_progress, null)
            alert.setView(mview)
            alertDialog = alert.create()
            alertDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog!!.setCanceledOnTouchOutside(false)
            alertDialog!!.show()
        }


        fun cancel() {
            alertDialog!!.cancel()
        }


}