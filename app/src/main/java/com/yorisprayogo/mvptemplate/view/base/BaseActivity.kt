package com.yorisprayogo.mvptemplate.view.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.yorisprayogo.mvptemplate.App
import com.yorisprayogo.mvptemplate.R
import com.yorisprayogo.mvptemplate.di.component.ActivityComponent
import com.yorisprayogo.mvptemplate.di.component.DaggerActivityComponent
import com.yorisprayogo.mvptemplate.di.module.ActivityModule
import com.yorisprayogo.mvptemplate.utils.CommonUtils
import com.yorisprayogo.mvptemplate.utils.NetworkUtils
import javax.xml.transform.Templates

open class BaseActivity: AppCompatActivity(), MvpView {

    private var mActivityComponent: ActivityComponent? = null

    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent((application as App).getComponent())
                .build()
    }

    fun getActivityComponent(): ActivityComponent? {
        return mActivityComponent
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }


    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(applicationContext)


    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (mProgressDialog?.isShowing == true) {
            mProgressDialog?.cancel()
        }
    }

    override fun openActivityOnTokenExpire() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onError(message: String) {
        if (message.isNotEmpty()) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.some_error))
        }
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById<View>(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        val textView = sbView
                .findViewById(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}