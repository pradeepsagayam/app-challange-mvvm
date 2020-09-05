package com.dp.baseui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dp.baseui.BaseActivity
import com.dp.baseui.R

abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @StringRes
    abstract fun getToolbarTitle(): Int

    @DrawableRes
    protected fun getToolbarLogo(): Int {
        return 0
    }

    fun enableBackButton(): Boolean {
        return false
    }

    protected fun showCtLoadingDialog() {
        (requireActivity() as BaseActivity).showCtLoadingDialog()
    }

    protected fun showNonCancellableCtLoadingDialog() {
        (requireActivity() as BaseActivity).showNonCancellableCtLoadingDialog()
    }

    protected fun hideCtLoadingDialog() {
        (requireActivity() as BaseActivity).hideCtLoadingDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutResId(), parent, false)
        setupToolbarTitle(getToolbarTitle())
        setupToolbarLogo(getToolbarLogo())
        setupBackButton(enableBackButton())
        return view
    }

    fun getSupportActionBar(): ActionBar? {
        return (activity as AppCompatActivity?)!!.supportActionBar
    }

    fun setupToolbarTitle(@StringRes string: Int) {
        val actionBar = getSupportActionBar()
        if (actionBar != null && string != 0) {
            actionBar.setTitle(string)
        }
    }

    private fun setupToolbarLogo(@DrawableRes drawable: Int) {
        val actionBar = getSupportActionBar()
        if (actionBar != null) {
            if (drawable != 0) {
                actionBar.setLogo(drawable)
                actionBar.setDisplayUseLogoEnabled(true)
            } else {
                actionBar.setDisplayUseLogoEnabled(false)
            }
        }
    }

    private fun setupBackButton(setupBackButton: Boolean) {
        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(setupBackButton)
    }

    fun setupCloseButton(setupCloseButton: Boolean) {
        val actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(setupCloseButton)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close)
        }
    }

}
