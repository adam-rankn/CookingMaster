package com.rankin.adam.cookingmaster.dialog;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
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
    Integer time;
    View popupView;
    View anchor;
    String name;


    public RecipeTimerPopup(View contentView, int width, int height, Integer time, View anchor, String name) {
        super(contentView, width, height);
        this.time = time;
        this.popupView = contentView;
        this.anchor = anchor;
        this.name = name;

        initialize();
    }

    private void initialize(){
        Button btnDismiss = popupView.findViewById(R.id.cookTimerPopup_btn_close);
        TextView timerTitleTextView = popupView.findViewById(R.id.cookTimerPopup_txt_title);
        final TextView timerTextView = popupView.findViewById(R.id.cookTimerPopup_txt_timer);

        timerTitleTextView.setText(name);
        timerTextView.setText(Integer.toString(time));
        //timerSeconds = timerMinutes * 60;

        new CountDownTimer(time*1000, 1000) {

            // we are just doing a countdown timer, so default locale will work
            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                //format time to HH:MM:SS

                timerTextView.setText(String.format("%02d:%02d:%02d", seconds / 3600,
                        (seconds % 3600) / 60, (seconds % 60)));
            }
            public void onFinish() {
                timerTextView.setText("00:00");

                //add blinking animation to the textview when complete
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
