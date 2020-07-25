package com.siren.docuved_admin.base

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.siren.docuved_admin.custom.Client
import com.siren.docuved_admin.custom.User
import com.siren.docuved_admin.ui.activity.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment: Fragment(), CoroutineScope {

    lateinit var job: Job
    lateinit var user: User
    lateinit var client: Client
    lateinit var loadingDialog: AlertDialog

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onAttach(context: Context) {
        super.onAttach(context)

        job             = Job()
        user            = (activity as DashboardActivity).user
        client          = (activity as DashboardActivity).client
        loadingDialog   = (activity as DashboardActivity).mProgressDialog
    }

    fun showMessage(message: String){

        launch(Dispatchers.Main) {

            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }

    fun isEmpty(inputText: EditText)    = TextUtils.isEmpty(inputText.text.toString())
}