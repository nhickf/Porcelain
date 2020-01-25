package com.nhick.porcelain.otp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.nhick.porcelain.R
import com.nhick.porcelain.Utility
import com.nhick.porcelain.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_initial_login.*
import kotlinx.android.synthetic.main.activity_otp.*


class OneTimePasswordActivity : AppCompatActivity() , IOtpContract.View {

    private val presenter: IOtpContract.Presenter by lazy { OneTimePasswordPresenter(this) }
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        setSupportActionBar(tool_bar)

        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP)

        val bundle = intent

        text_view_user_number.text = bundle.extras?.get(Utility.NUMBER_CODE_KEY).toString()

        countDownTimer = presenter.countDownTimer(60000,1000).start()

        text_view_resend.setOnClickListener{
            presenter.countDownTimer(60000,1000).start()
        }


        edit_text_code.setOnEditorActionListener { textView, i, keyEvent ->
            return@setOnEditorActionListener when (i) {
                EditorInfo.IME_ACTION_DONE -> {
                    val intent = Intent(this,
                        DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

    }

    override fun showTimerValue(value: String) {
        text_view_timer.text = value
    }

    override fun showTimerFinish(value: String) {
        text_view_timer.text = value
        text_view_resend.isEnabled = true
    }

}