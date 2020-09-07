package com.dp.baseui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.annotation.CheckResult
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dp.baseui.R
import javax.inject.Inject

class AlertDialogFactory @Inject constructor() {
    @ColorRes
    private val dividerColor = R.color.pale_grey

    @CheckResult
    fun getDialog(
        context: Context,
        adapter: ListAdapter?,
        listener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context)
        builder.setAdapter(adapter, listener)
        val dialog = builder.create()
        setDivider(context, dialog, 0f)
        return dialog
    }

    @CheckResult
    fun getOkDialog(
        context: Context,
        @StringRes messageResId: Int
    ): AlertDialog {
        return getOkDialog(context, 0, messageResId)
    }

    @CheckResult
    fun getOkDialog(
        context: Context, @StringRes titleResId: Int,
        @StringRes messageResId: Int
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        if (titleResId != 0) {
            builder.setTitle(titleResId)
        }
        val dialog = builder
            .setMessage(messageResId)
            .setPositiveButton(R.string.okay, null)
            .setCancelable(false)
            .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getOkDialog(
        context: Context, @StringRes titleResId: Int,
        @StringRes messageResId: Int, cancelable: Boolean,
        onClickListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        if (titleResId != 0) {
            builder.setTitle(titleResId)
        }
        val dialog = builder
            .setMessage(messageResId)
            .setPositiveButton(
                R.string.okay
            ) { d: DialogInterface, which: Int ->
                onClickListener?.onClick(d, which)
                d.dismiss()
            }
            .setCancelable(cancelable)
            .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getOkDialog(
        context: Context, title: String?,
        message: String?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        if (title != null && !title.isEmpty()) {
            builder.setTitle(title)
        }
        val dialog = builder
            .setMessage(message)
            .setPositiveButton(R.string.okay, null)
            .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getOkDialog(
        context: Context, title: String?,
        message: String?, listener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        if (title != null && !title.isEmpty()) {
            builder.setTitle(title)
        }
        val dialog = builder
            .setMessage(message)
            .setPositiveButton(R.string.okay, listener)
            .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getOkDialog(
        context: Context,
        message: String?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        builder.setMessage(message).setPositiveButton(R.string.okay, null)
        val dialog = builder.create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getLeaveConfirmation(
        context: Context,
        @StringRes titleId: Int,
        @StringRes messageId: Int,
        positiveListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        return getAlertDialog(
            context, titleId, messageId,
            R.string.leave,
            R.string.stay, positiveListener
        )
    }

    @CheckResult
    fun getViewDialog(
        context: Context,
        view: View?,
        listener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        builder.setView(view).setPositiveButton(R.string.okay, listener)
        val dialog = builder.create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getViewDialog(
        context: Context,
        view: View?,
        titleId: Int,
        cancelable: Boolean,
        listener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        builder.setView(view).setPositiveButton(R.string.okay, listener)
        val dialog = builder.create()
        dialog.setCancelable(cancelable)
        dialog.setTitle(titleId)
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getAlertDialog(
        context: Context,
        @StringRes titleStringRes: Int,
        @StringRes messageStringRes: Int,
        @StringRes positiveButtonStringRes: Int,
        @StringRes negativeButtonStringRes: Int,
        positiveListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val dialog =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
                .setTitle(titleStringRes)
                .setMessage(messageStringRes)
                .setPositiveButton(positiveButtonStringRes, positiveListener)
                .setNegativeButton(negativeButtonStringRes, null)
                .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getAlertDialog(
        context: Context,
        titleStringRes: String?,
        messageStringRes: String?,
        @StringRes positiveButtonStringRes: Int,
        @StringRes negativeButtonStringRes: Int,
        positiveListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val dialog =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
                .setTitle(titleStringRes)
                .setMessage(messageStringRes)
                .setPositiveButton(positiveButtonStringRes, positiveListener)
                .setNegativeButton(negativeButtonStringRes, null)
                .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getAlertDialog(
        context: Context,
        @StringRes titleStringRes: Int,
        @StringRes messageStringRes: Int,
        @StringRes positiveButtonStringRes: Int,
        @StringRes negativeButtonStringRes: Int,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val dialog =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
                .setTitle(titleStringRes)
                .setMessage(messageStringRes)
                .setPositiveButton(positiveButtonStringRes, positiveListener)
                .setNegativeButton(negativeButtonStringRes, negativeListener)
                .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getAlertDialog(
        context: Context,
        @StringRes titleStringRes: Int,
        @StringRes messageStringRes: Int,
        @StringRes positiveButtonStringRes: Int,
        @StringRes negativeButtonStringRes: Int,
        positiveListener: DialogInterface.OnClickListener?,
        negativeListener: DialogInterface.OnClickListener?,
        cancelable: Boolean
    ): AlertDialog {
        val dialog = getAlertDialog(
            context, titleStringRes, messageStringRes,
            positiveButtonStringRes, negativeButtonStringRes, positiveListener, negativeListener
        )
        dialog.setCancelable(cancelable)
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getDialog(
        context: Context,
        @StringRes titleResId: Int,
        @StringRes messageResId: Int,
        @StringRes positiveButtonStringRes: Int,
        cancelable: Boolean,
        onClickListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context, R.style.Dialog_Ok)
        if (titleResId != 0) {
            builder.setTitle(titleResId)
        }
        val dialog = builder
            .setMessage(messageResId)
            .setPositiveButton(
                positiveButtonStringRes
            ) { d: DialogInterface, which: Int ->
                onClickListener?.onClick(d, which)
                d.dismiss()
            }
            .setCancelable(cancelable)
            .create()
        styleDialog(context, dialog)
        return dialog
    }

    @CheckResult
    fun getDialog(
        context: Context,
        adapter: ListAdapter?,
        listener: DialogInterface.OnClickListener?,
        hasDivider: Boolean
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(context)
        builder.setAdapter(adapter, listener)
        val dialog = builder.create()
        val dividerHeight = if (hasDivider) 1f else 0f
        setDivider(context, dialog, dividerHeight)
        return dialog
    }

    private fun styleDialog(
        context: Context,
        dialog: AlertDialog
    ) {
        dialog.setOnShowListener { dialog1: DialogInterface ->
            val titleTextView =
                (dialog1 as Dialog).window!!.findViewById<TextView>(R.id.alertTitle)
            titleTextView
                .setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    context.resources.getDimension(R.dimen.h2)
                )
            titleTextView.maxLines = 3
            titleTextView.typeface = ResourcesCompat.getFont(
                context,
                R.font.nova_bold
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 20, 0, 20)
            titleTextView.layoutParams = params
            val messageTextView = dialog1.window!!.findViewById<TextView>(android.R.id.message)
            messageTextView
                .setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    context.resources.getDimension(R.dimen.h3)
                )
            messageTextView.typeface = ResourcesCompat.getFont(
                context,
                R.font.nova_regular
            )
        }
    }

    private fun setDivider(
        context: Context,
        dialog: AlertDialog,
        dividerHeight: Float
    ) {
        val listView = dialog.listView
        listView.divider = ColorDrawable(ContextCompat.getColor(context, dividerColor))
        listView.dividerHeight = convertDpToPixel(dividerHeight, context)
        if (dividerHeight != 0f) {
            listView.setFooterDividersEnabled(false)
            listView.addFooterView(View(context))
        }
    }

    private fun convertDpToPixel(dp: Float, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return (dp * metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toInt()
    }

    private fun navigateToSettings(context: Context?): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { _: DialogInterface?, _: Int ->
            if (null != context) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri =
                    Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            }
        }
    }
}
