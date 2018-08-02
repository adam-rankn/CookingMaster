package com.rankin.adam.cookingmaster.dialog;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class RecipeTimerPopup extends PopupWindow {

    int pos;
    String time;
    Integer timerMinutes;
    Integer timerSeconds;
    View popupView;
    View anchor;


    public RecipeTimerPopup(View contentView, int width, int height, String time,View anchor) {
        super(contentView, width, height);
        this.time = time;
        this.popupView = contentView;
        this.anchor = anchor;

        initialize();
    }

    public void initialize(){
        Button btnDismiss = popupView.findViewById(R.id.cookTimerPopup_btn_close);
        TextView timerTitleTextView = popupView.findViewById(R.id.cookTimerPopup_txt_title);
        final TextView timerTextView = popupView.findViewById(R.id.cookTimerPopu_txt_timer);

        //TODO extract all this into a custom popup class
        timerTitleTextView.setText(recipeController.getName());
        timerTextView.setText(time);
        timerMinutes = Integer.parseInt(time);
        timerSeconds = timerMinutes * 60;

        new CountDownTimer(timerSeconds*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText((millisUntilFinished / 1000)/60 +  ":" + (millisUntilFinished / 1000)%60);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                timerTextView.setText("00:00");
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                timerTextView.startAnimation(anim);
            }

        }.start();

        btnDismiss.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                dismiss();
            }});

        showAsDropDown(anchor, 50, -30);


        popupView.setOnTouchListener(new View.OnTouchListener() {
            int orgX, orgY;
            int offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        orgX = (int) event.getX();
                        orgY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        offsetX = (int)event.getRawX() - orgX;
                        offsetY = (int)event.getRawY() - orgY;
                        update(offsetX, offsetY, -1, -1, true);
                        break;
                }
                return true;
            }});
    }



}
