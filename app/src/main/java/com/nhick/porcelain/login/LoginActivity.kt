package com.nhick.porcelain.login

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.snackbar.Snackbar
import com.nhick.porcelain.otp.OneTimePasswordActivity
import com.nhick.porcelain.R
import com.nhick.porcelain.Utility.Companion.NUMBER_CODE_KEY
import com.nhick.porcelain.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_initial_login.*

class LoginActivity  :  ILoginContract.View , AppCompatActivity()  {

    private lateinit var presenter : ILoginContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_login)

        presenter = LoginPresenter(this)

        Handler().postDelayed(initiateAnimation(),2000)

        ccp.registerCarrierNumberEditText(edit_text_number)

        edit_text_number.addTextChangedListener(presenter.onTextChangeListener())

        text_view_choose_social.setOnClickListener(presenter.onClick(ccp))

        button_facebook.setOnClickListener(presenter.onClick())

        button_google.setOnClickListener(presenter.onClick())


        edit_text_number.setOnEditorActionListener { textView, actionID, keyEvent ->
            return@setOnEditorActionListener when (actionID) {
                EditorInfo.IME_ACTION_GO -> {
                    Log.e("editor","send")
                    presenter.validateNumber(ccp)
                    true
                }
                else -> false
            }
        }

    }


    private fun initiateAnimation() = Runnable{


        var set = false

        val firstSet = ConstraintSet()
        firstSet.clone(container)

        val secondSet = ConstraintSet()
        secondSet.clone(this, R.layout.activity_final_login)

        TransitionManager.beginDelayedTransition(container)

        val mainSet = if (set) firstSet else secondSet

        mainSet.applyTo(container)

    }

    override fun onDisplayError(message: String) {
        Snackbar.make(container,message,Snackbar.LENGTH_SHORT).show()
    }

    override fun onPhoneNumberChanges() {
        text_view_choose_social.text = getString(R.string.continue_lbl)
    }

    override fun onPhoneNumberEmpty() {
        text_view_choose_social.text = getString(R.string.option_login_lbl)
    }

    override fun onProceedToDashboard() {
        startActivity(Intent(this,DashboardActivity::class.java))
        finish()
    }

    override fun onProceedToOTP() {

       val builder = AlertDialog.Builder(this,R.style.AlerDialogCustomStyle)
            .setTitle("OTP Code")
            .setMessage("You have successfully receive the OTP CODE : 1234")
            .setCancelable(false)
            .setPositiveButton("Confirm") { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(this,
                    OneTimePasswordActivity::class.java)
                intent.putExtra(NUMBER_CODE_KEY,ccp.formattedFullNumber)
                startActivity(intent)
            }

        builder.create()
        builder.show()

    }

}