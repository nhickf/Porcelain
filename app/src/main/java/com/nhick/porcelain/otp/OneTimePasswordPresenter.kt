package com.nhick.porcelain.otp

import android.os.CountDownTimer
import android.view.View
import java.util.concurrent.TimeUnit

class OneTimePasswordPresenter(private val view : IOtpContract.View) : IOtpContract.Presenter {


    override fun countDownTimer(milliSeconds: Long, interval: Long)  : CountDownTimer {
        return  object : CountDownTimer(milliSeconds,interval){
            override fun onFinish() {
                view.showTimerFinish("You can now request new code")
            }

            override fun onTick(p0: Long) {
                view.showTimerValue("Request new code in "+String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(p0),
                    TimeUnit.MILLISECONDS.toSeconds(p0)))

            }
        }
    }
}