package com.rankin.adam.cookingmaster.dialog

import android.widget.PopupWindow
import android.annotation.SuppressLint
import com.rankin.adam.cookingmaster.R
import android.widget.TextView
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AlphaAnimation
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.view.View
import android.widget.Button

class RecipeTimerPopup(
    var popupView: View,
    width: Int,
    height: Int,
    var time: Int,
    var anchor: View,
    var name: String
) : PopupWindow(
    popupView, width, height
) {
    var pos = 0

    init {
        initialize()
    }

    @SuppressLint("SetTextI18n")
    private fun initialize() {
        val btnDismiss = popupView.findViewById<Button>(R.id.cookTimerPopup_btn_close)
        val timerTitleTextView = popupView.findViewById<TextView>(R.id.cookTimerPopup_txt_title)
        val timerTextView = popupView.findViewById<TextView>(R.id.cookTimerPopup_txt_timer)
        timerTitleTextView.text = name
        timerTextView.text = time.toString()
        //timerSeconds = timerMinutes * 60;
        object : CountDownTimer((time * 1000).toLong(), 1000) {
            // we are just doing a countdown timer, so default locale will work
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                //format time to HH:MM:SS
                timerTextView.text = String.format(
                    "%02d:%02d:%02d", seconds / 3600,
                    seconds % 3600 / 60, seconds % 60
                )
            }

            override fun onFinish() {
                timerTextView.text = "00:00"

                //add blinking animation to the textview when complete
                val anim: Animation = AlphaAnimation(0.0f, 1.0f)
                anim.duration = 500 //You can manage the blinking time with this parameter
                anim.startOffset = 0
                anim.repeatMode = Animation.REVERSE
                anim.repeatCount = Animation.INFINITE
                timerTextView.startAnimation(anim)
            }
        }.start()
        btnDismiss.setOnClickListener { dismiss() }
        showAsDropDown(anchor, 50, -30)
        popupView.setOnTouchListener(object : OnTouchListener {
            var orgX = 0
            var orgY = 0
            var offsetX = 0
            var offsetY = 0
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        orgX = event.x.toInt()
                        orgY = event.y.toInt()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        offsetX = event.rawX.toInt() - orgX
                        offsetY = event.rawY.toInt() - orgY
                        update(offsetX, offsetY, -1, -1, true)
                    }
                }
                return true
            }
        })
    }
}