package com.nhick.porcelain.login

import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.snackbar.Snackbar
import com.nhick.porcelain.R
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
        onDisplayError("Proceed to Dashboard")
    }

}