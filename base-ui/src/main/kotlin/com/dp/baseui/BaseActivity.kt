package com.dp.baseui

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dp.baseui.loading.DpLoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    @get:StringRes
    abstract val toolbarTitle: Int

    @get:LayoutRes
    abstract val layoutResource: Int

    lateinit var viewDataBinding: ViewDataBinding

    private val ctLoadingDialog: DpLoadingDialog = DpLoadingDialog.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResource)
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    open fun enableBackButton(): Boolean {
        return false
    }

    open fun enableCloseButton(): Boolean {
        return false
    }

    open fun navigateUp() {
        NavUtils.navigateUpFromSameTask(this)
    }

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val inputManager: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = null
            setupToolbarTitle(toolbar)
            setupNavigationButton(actionBar)
        }
    }

    private fun setupNavigationButton(actionBar: ActionBar) {
        actionBar.setDisplayHomeAsUpEnabled(enableBackButton() || enableCloseButton())

        when {
            enableCloseButton() -> {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_close)
            }
            enableBackButton() -> {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_backbutton)
            }
            else -> {
                actionBar.setHomeAsUpIndicator(null)
            }
        }
    }

    private fun setupToolbarTitle(toolbar: Toolbar) {
        val toolbarTitleView = toolbar.findViewById<TextView>(R.id.toolbar_title)
        if (toolbarTitle != 0) {
            toolbarTitleView.text = getString(toolbarTitle)
        } else {
            toolbarTitleView.text = null
        }
    }

    fun showCtLoadingDialog() {
        showCtLoadingDialog(true)
    }

    fun showNonCancellableCtLoadingDialog() {
        showCtLoadingDialog(false)
    }

    fun showCtLoadingDialog(isCancelable: Boolean) {
        hideCtLoadingDialog()
        ctLoadingDialog.show(this)
        ctLoadingDialog.isCancelable = isCancelable
    }

    fun hideCtLoadingDialog() {
        if (ctLoadingDialog.isVisible)
            ctLoadingDialog.dismissAllowingStateLoss()
    }
}
