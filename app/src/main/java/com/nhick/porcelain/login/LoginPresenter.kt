package com.nhick.porcelain.login

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.google.android.material.button.MaterialButton
import com.hbb20.CountryCodePicker

class LoginPresenter(private val view:ILoginContract.View) : ILoginContract.Presenter {


    override fun onTextChangeListener(): TextWatcher {

        return object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrEmpty()){
                    view.onPhoneNumberChanges()
                }else{
                    view.onPhoneNumberEmpty()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        }
    }

    override fun onClick(ccp: CountryCodePicker): View.OnClickListener {
        return View.OnClickListener {
           validateNumber(ccp)
        }
    }

    override fun validateNumber(ccp: CountryCodePicker){
        if (ccp.isValidFullNumber){
            view.onProceedToOTP()
        }else{
            view.onDisplayError("Invalid number")
        }
    }

    override fun onClick(): View.OnClickListener {
        return View.OnClickListener {
            if (it is MaterialButton){
                view.onProceedToDashboard()
            }
        }
    }


}