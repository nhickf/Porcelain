package com.nhick.porcelain.login

import android.text.TextWatcher
import com.hbb20.CountryCodePicker

interface ILoginContract {


    interface View{


        fun onDisplayError(message :String)

        fun onPhoneNumberChanges()

        fun onPhoneNumberEmpty()

        fun onProceedToDashboard()

    }

    interface Presenter{

       fun onTextChangeListener() : TextWatcher

       fun onClick(ccp : CountryCodePicker) : android.view.View.OnClickListener

       fun onClick() : android.view.View.OnClickListener

    }

}