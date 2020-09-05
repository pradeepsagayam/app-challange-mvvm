package com.dp.baseui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.dp.baseui.R

class DpLoadingDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Dialog_Loading)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    fun show(activity: FragmentActivity) {
        val supportFragmentManager =
            activity.supportFragmentManager
        show(supportFragmentManager, DpLoadingDialog::class.java.simpleName)
    }

    companion object {
        fun newInstance(): DpLoadingDialog {
            return DpLoadingDialog()
        }
    }
}
