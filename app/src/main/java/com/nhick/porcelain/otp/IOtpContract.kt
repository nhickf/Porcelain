package com.nhick.porcelain.otp

import android.os.CountDownTimer
import android.view.View

interface IOtpContract {


    interface View{

        fun showTimerValue(value : String)

        fun showTimerFinish(value: String)


    }

    interface Presenter{

        fun countDownTimer(milliSeconds:Long, interval:Long) : CountDownTimer

    }

}