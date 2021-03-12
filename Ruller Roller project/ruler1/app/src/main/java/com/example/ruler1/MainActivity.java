package com.example.ruler1;


import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.MotionEventCompat;

import com.example.ruler1.R;

public class MainActivity extends AppCompatActivity {

    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
    float mLastTouchX;
    float mLastTouchY;
    float mPosX ;
    float mPosY ;
    private FrameLayout mLayout;
   int ScreenHeight;
   int ScreenWidth;

    float lastx;
    float lasty;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mLayout.setOnTouchListener(mListener);
        WindowManager w = getWindowManager();
        Display dp = w.getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        ScreenHeight = size.y;
        ScreenWidth = size.x;

    }

    private View.OnTouchListener mListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent ev) {
            final int action = MotionEventCompat.getActionMasked(ev);

            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    final int pointerIndex = MotionEventCompat.getActionIndex(ev);

                    final float x = MotionEventCompat.getX(ev, pointerIndex);
                    final float y = MotionEventCompat.getY(ev, pointerIndex);

                    // Remember where we started (for dragging)
                    mLastTouchX = x;
                    mLastTouchY = y;
                    // Save the ID of this pointer (for dragging)

                    mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                    // Toast.makeText(this, "" + 1, Toast.LENGTH_SHORT).show();

                    break;
                }

                case MotionEvent.ACTION_MOVE: {

                    // Find the index of the active pointer and fetch its position

                    final int pointerIndex =
                            MotionEventCompat.findPointerIndex(ev, mActivePointerId);

                    final float x = MotionEventCompat.getX(ev, pointerIndex);
                    final float y = MotionEventCompat.getY(ev, pointerIndex);

                    // Calculate the distance moved
                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;

                    mPosX += dx;
                    mPosY += dy;

                    //recursion
                    if (mPosY > ScreenHeight) {
                        mPosY = imageTop - 78;
                        TposY = txtT;
                    }


                    clickOnTape.start();

                    image.invalidate();

                    // Remember this touch position for the next move event
                    mLastTouchX = x;
                    mLastTouchY = y;
                    break;
                }
                case MotionEvent.ACTION_UP: {
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
                        // This was our active pointer going up. Choose a new
                        // active pointer and adjust accordingly.
                        final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
                        mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
                        mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                    }
                    break;
                }
            }
            image.setY(mPosY);
            txt.setY(TposY);
            if (mPosY > j) {
                createNewImage();
                j = j + imageHeight;
                System.out.println(j);
                System.out.println(noOfUnit);
            }


            lastx = mPosX;
            lasty = mPosY;

            return true;


            //           switch (event.getAction()) {
//
//                case MotionEvent.ACTION_DOWN:
//                    return true;
//
//                case MotionEvent.ACTION_UP:
//                    // decode the resource to get width and height
//                    BitmapFactory.Options opts = new BitmapFactory.Options();
//                    opts.inJustDecodeBounds = true;
//                    BitmapFactory.decodeResource(getResources(), R.drawable.unit, opts);
//
//                    int imageWidth = opts.outWidth;
//                    int imageHeight = opts.outHeight;
//
//                    // set the imageview's top and left margins
//                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(imageWidth, imageHeight);
//                    lp.leftMargin = (int) (event.getX() - (imageWidth / 2));
//                    lp.topMargin = (int) (event.getY() - (imageHeight / 2));
//
//                    ImageView image = new ImageView(MainActivity.this);
//                    image.setImageResource(R.drawable.unit);
//                    mLayout.addView(image, lp);
//
//                    return false;
//
//            }
//            return false;
//        }
//    };
        }
};
}
