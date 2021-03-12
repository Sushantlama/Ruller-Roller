package com.rull2rr1ller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.MotionEventCompat;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rull2rr1ller.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    int ScreenWidth;
    int ScreenHeight;


    ViewGroup mainLayout;

    float mLastTouchX;
    float mLastTouchY;
    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;

    ImageView unit;
    int uNowX;
    int uNowY;
    int uHeight;
    ImageView unit2;
    int uNowY2;

    ImageView Head;
    int headHeight;
    int density;

    TextView number;
    float numberY;
    ImageView background1;
    float backY;
    TextView number2;
    float number2Y;
    ImageView background2;
    float backY2;

    ImageView base;
    TextView ShowHighScore;

    List<ImageView> images = new ArrayList<>();
    List<ImageView> backGrounds = new ArrayList<>();
    List<TextView> numbers = new ArrayList<>();


    int border;
    int noOfItems = 0;

    ImageView darkLine;
    int text1Height;
    int number1height;
    int text2Height;
    int number2height;

    ImageView HighScore;
    ImageView Button;
    TextView margin;
    int marginHeight;

    int Points = 0;
    int MYHighScore;

    ImageView crown;
    int restart;


    int startingpoint;


    int startingpoint2;



    MediaPlayer clickOnTape;
    MediaPlayer clickOnTape2;
    MediaPlayer clickOnTap3;
    MediaPlayer clickOnTap4;
    MediaPlayer clickOnTap5;
    MediaPlayer scrollTape;
    MediaPlayer swipeTape2;
    MediaPlayer swipeTape;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main_layout);
        init_View();
        init_values();
        setHighScore();
        WindowManager w = getWindowManager();
        Display dp = w.getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        ScreenHeight = size.y;
        ScreenWidth = size.x;

        clickOnTape= MediaPlayer.create(getApplicationContext(), R.raw.click_on_tape);
        scrollTape=  MediaPlayer.create(getApplicationContext(), R.raw.scroll_tape_off_finger_of_screen);
        clickOnTape2=MediaPlayer.create(getApplicationContext(),R.raw.click_on_tape);
        clickOnTap3=MediaPlayer.create(getApplicationContext(),R.raw.click_on_tape);
        clickOnTap4=MediaPlayer.create(getApplicationContext(),R.raw.click_on_tape);
        clickOnTap5=MediaPlayer.create(getApplicationContext(),R.raw.click_on_tape);
        swipeTape=  MediaPlayer.create(getApplicationContext(), R.raw.swipe_tape);
        swipeTape2=  MediaPlayer.create(getApplicationContext(), R.raw.swipe_tape);

        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
    }


    public void setHighScore() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", Points);
        editor.apply();

    }

    public int getHighscore() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        MYHighScore = sharedPreferences.getInt("key", 0);
        return MYHighScore;
    }


    public void init_View() {
        unit = findViewById(R.id.unit);
        unit2 = findViewById(R.id.unit2);
        Head = findViewById(R.id.head);
        number = findViewById(R.id.numbers1);
        number2 = findViewById(R.id.numbers2);
        background1 = findViewById(R.id.backGround1);
        background2 = findViewById(R.id.backGround2);
        base = findViewById(R.id.base);
        darkLine = findViewById(R.id.dark);
        HighScore = findViewById(R.id.point_box);
        Button = findViewById(R.id.black);
        ShowHighScore = findViewById(R.id.highscore);
        margin = findViewById(R.id.margin);
        ShowHighScore.setText(String.valueOf(getHighscore()));

        crown = findViewById(R.id.crown);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            init_values();
        }
    }

    int fixedY2;
    int fixedY;
    public void init_values() {









        text1Height = number.getMeasuredHeight();
        uNowY = unit.getTop();
        fixedY=uNowY;
        headHeight = Head.getMeasuredHeight();
        uHeight = unit.getMeasuredHeight();
        density = (int) getResources().getDisplayMetrics().density * 160;
        numberY = number.getY();
        uNowY2 = unit2.getTop();
        fixedY2=uNowY2;

        number2Y = number2.getTop();
        backY = background1.getTop();
        backY2 = background2.getTop();
        border = unit2.getTop();

        startingpoint=uNowY;
        startingpoint2=uNowY2;

        text2Height = number2.getMeasuredHeight();
        number1height = background1.getMeasuredHeight();
        number2height = background2.getMeasuredHeight();


        marginHeight = margin.getTop();
        restart = uNowY;

    }


    int i=0;
    int mynumber=1;
    int uNowZ2=0;


    int lastY=uNowY;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                mLastTouchX = x;
                mLastTouchY = y;

                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                uNowY2 += dy;
                uNowX += dx;
                uNowY += dy;
                uNowZ2+=dy;



                if (noOfItems >= 100*mynumber) {
                    mynumber++;
                }

                unit.invalidate();

                mLastTouchX = x;
                mLastTouchY = y;
                break;
            }
            case MotionEvent.ACTION_UP: {
                scrollTape.start();
                restart();
                mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);

                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
                    mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                break;
            }
        }
        unit2.setY(uNowY2);
        Head.setY(uNowY + uHeight);
        unit.setY(uNowY);
        number.setY(uNowY + uHeight - number1height + 25);
        background1.setY(uNowY + uHeight - number1height + 10);
        number2.setY(uNowY - number2height + 25);
        background2.setY(uNowY - number2height + 10);

        if(lastY<=uNowY&&Points%2==0)
        { { swipeTape2.start();}
            lastY=uNowY;}
        else if(lastY<=uNowY&&Points%2!=0){
            { swipeTape.start();}
            lastY=uNowY;
        }

        Move();
        if (uNowY2 > border + uHeight * noOfItems) {
            addNewImagesAndText();
            noOfItems++;
        }
        ;
        if(i==0){
            check_points();
            if (uNowY + uHeight - number1height + 25 > marginHeight + uHeight * Points&&Points%2==0) {
                margin.setText(String.valueOf(Points + 1));
                clickOnTape.start();
                Points++;
                i=1;
                System.out.println(1);

            }
            else if (uNowY + uHeight - number1height + 25 > marginHeight + uHeight * Points&&Points%2!=0) {
                margin.setText(String.valueOf(Points + 1));
                clickOnTape2.start();
                Points++;
                i=1;
                System.out.println(2);
            }}
        else if(i==1){
            check_points();
            if (uNowY + uHeight - number1height + 25 > marginHeight + uHeight * Points&&Points%2==0) {
                margin.setText(String.valueOf(Points + 1));
                clickOnTap3.start();
                Points++;
                i=2;
                System.out.println(3);
            }
            else if (uNowY + uHeight - number1height + 25 > marginHeight + uHeight * Points&&Points%2!=0) {
                margin.setText(String.valueOf(Points + 1));
                clickOnTap4.start();
                Points++;
                i=2;
                System.out.println(4);
            }
        }
        else if(i==2){
            check_points();
            if (uNowY + uHeight - number1height + 25 > marginHeight + uHeight * Points&&Points%2==0) {
                margin.setText(String.valueOf(Points + 1));
                clickOnTap5.start();
                Points++;
                i=0;
                System.out.println(5);
            }
            else if (uNowY + uHeight - number1height + 25 > marginHeight + uHeight * Points&&Points%2!=0) {
                margin.setText(String.valueOf(Points + 1));
                clickOnTap5.start();
                Points++;
                i=0;
                System.out.println(5);
            }
        }
        else{
            check_points();
        }

        if (Points >= MYHighScore) {
            crown.setVisibility(View.VISIBLE);
            setHighScore();
            ShowHighScore.setText(String.valueOf(getHighscore()));
        } else {
            crown.setVisibility(View.INVISIBLE);
        }
        return true;
    }





    private void Move() {

        if(images.size()!=0) {
            for (int i = 0; i < images.size(); i++) {
                ImageView im=images.get(i);
                mainLayout.removeView(images.get(i));
                im.setY(uNowY2-uHeight*(i+1));
                mainLayout.addView(im);
                images.set(i,im);
                mainLayout.bringChildToFront(base);
                mainLayout.bringChildToFront(darkLine);

                ImageView backG=backGrounds.get(i);
                mainLayout.removeView(backGrounds.get(i));
                backG.setY(uNowY-(uHeight*(i+1))-number1height+10);
                mainLayout.addView(backG);
                backGrounds.set(i,backG);

                TextView num=numbers.get(i);
                mainLayout.removeView(numbers.get(i));
                num.setY(uNowY-(uHeight*(i+1))-number1height+25);
                if(noOfItems+3>=100 ){
                    num.setY(uNowY-(uHeight*(i+1))-number1height+25);
                }
                if(noOfItems+3>=1000){
                    num.setY(uNowY-(uHeight*(i+1))-number1height+35);
                }
                if(noOfItems+3>=10000){
                    num.setY(uNowY-(uHeight*(i+1))-number1height+35);
                }
                if(noOfItems+3>=10000){
                    num.setY(uNowY-(uHeight*(i+1))-number1height+40);
                }
                if(noOfItems+3>=100000){
                    num.setY(uNowY-(uHeight*(i+1))-number1height+40);
                }
                mainLayout.addView(num);
                numbers.set(i,num);

                mainLayout.bringChildToFront(base);
                mainLayout.bringChildToFront(darkLine);
                mainLayout.bringChildToFront(HighScore);
                mainLayout.bringChildToFront(Button);
                mainLayout.bringChildToFront(ShowHighScore);
                mainLayout.bringChildToFront(crown);


            }
        }
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int currentNightMode = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                Toast.makeText(this, "light mode", Toast.LENGTH_SHORT).show();
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                Toast.makeText(this, "dark mode", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addNewImagesAndText() {

        ImageView image =new ImageView(this);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        image.setImageResource(R.drawable.unit);
        image.setY(uNowY2-uHeight);
        image.setLayoutParams(layoutParams);
        mainLayout.addView(image);

        ImageView background=new ImageView(this);
        RelativeLayout.LayoutParams lParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        background.setImageResource(R.drawable.background);
        background.setY(uNowY+(uHeight*(noOfItems-2))-number1height+10);
        background.setLayoutParams(lParams);
        background.setVisibility(View.INVISIBLE);

        mainLayout.addView(background);



        TextView num=new TextView(this);
        RelativeLayout.LayoutParams nParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        num.setText(String.valueOf(noOfItems+3));
        num.setTextColor(Color.BLACK);

        if((noOfItems+3)%10==0){
            num.setTextColor(Color.WHITE);
            background.setVisibility(View.VISIBLE);
        }
        num.setRotation(270);
        num.setTextSize(20);
        num.setY(uNowY+(uHeight*(noOfItems-2))-number1height+25);
        if(noOfItems+3>=100){
            num.setY(uNowY+(uHeight*(noOfItems-2))-number1height+30);
            num.setTextSize(15);
        }
        if(noOfItems+3>=1000){
            num.setTextSize(13);
            num.setY(uNowY+(uHeight*(noOfItems-2))-number1height+35);
        }
        if(noOfItems+3>=10000){
            num.setTextSize(10);
            num.setY(uNowY+(uHeight*(noOfItems-2))-number1height+35);
        }
        if(noOfItems+3>=100000){
            num.setTextSize(8);
            num.setY(uNowY+(uHeight*(noOfItems-2))-number1height+40);
        }
        if(noOfItems+3>=1000000){
            num.setTextSize(7);
            num.setY(uNowY+(uHeight*(noOfItems-2))-number1height+40);
        }

        num.setLayoutParams(nParams);
        mainLayout.addView(num);


        backGrounds.add(background);
        images.add(image);
        numbers.add(num);

        mainLayout.bringChildToFront(base);
        mainLayout.bringChildToFront(darkLine);
        mainLayout.bringChildToFront(HighScore);
        mainLayout.bringChildToFront(Button);
        mainLayout.bringChildToFront(ShowHighScore);
        mainLayout.bringChildToFront(crown);
    }

    public int convertDpToPix(int dp){
        int px = (dp * (density/ 160));
        return px;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void restart(){
        uNowY=fixedY;
        uNowY2=fixedY2;

        unit2.setY(uNowY2);
        Head.setY(uNowY + uHeight);
        unit.setY(uNowY);
        number.setY(uNowY + uHeight - number1height + 25);
        background1.setY(uNowY + uHeight - number1height + 10);
        number2.setY(uNowY - number2height + 25);
        background2.setY(uNowY - number2height + 10);
        Move();
        Points=0;
        margin.setText(String.valueOf(Points));
        lastY=uNowY;

    }

    public void check_points(){
        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) margin.getLayoutParams();
        if (Points<10){
            layoutParams.leftMargin = convertDpToPix(60);
            layoutParams.bottomMargin = convertDpToPix(30);
        }
        if (Points>=10) {
            layoutParams.leftMargin = convertDpToPix(55);
            layoutParams.bottomMargin = convertDpToPix(30);
        }
        if(Points>=100){
            layoutParams.leftMargin=convertDpToPix(50);
            layoutParams.bottomMargin=convertDpToPix(30);
        }
        if(Points>=1000){
            layoutParams.leftMargin=convertDpToPix(43);
            layoutParams.bottomMargin=convertDpToPix(30);
        }
        if    (Points>=10000){
            layoutParams.leftMargin=convertDpToPix(36);
            layoutParams.bottomMargin=convertDpToPix(30);
        }
        if(Points>=1000000){
            layoutParams.leftMargin=convertDpToPix(36);
            margin.setTextSize(18);
            layoutParams.bottomMargin=convertDpToPix(30);
        }
        if(Points>=10000000) {
            layoutParams.leftMargin = convertDpToPix(35);
            margin.setTextSize(15);
            layoutParams.bottomMargin = convertDpToPix(33);
        } if(Points>=10000000){
            layoutParams.leftMargin=convertDpToPix(36);
            margin.setTextSize(18);
            layoutParams.bottomMargin=convertDpToPix(33);
        }



        margin.setLayoutParams(layoutParams);


    }


}
