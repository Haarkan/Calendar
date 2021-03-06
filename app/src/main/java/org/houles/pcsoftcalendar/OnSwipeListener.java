package org.houles.pcsoftcalendar;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;

/**
 * Custom listener
 * Listen to user swiping
 */
class OnSwipeListener implements View.OnTouchListener {
    private GestureDetector gestureDetector;
    protected Context context;
    OnSwipeListener(Context c) {
        context = c;
        gestureDetector = new GestureDetector(c, new GestureListener());
    }
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }


    private final class GestureListener extends
            GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                }
                else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeDown();
                        } else {
                            onSwipeUp();
                        }
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }
    public void onSwipeRight() {
    }
    public void onSwipeLeft() {
    }
    public void onSwipeUp() {
    }
    public void onSwipeDown() {
    }



}
