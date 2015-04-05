package com.kosmolobster.roundmotiontest;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CirclePathAnimation extends Animation {
    //private View view;
    private float cx, cy;           // center x,y position of circular path
    private float prevX, prevY;     // previous x,y position of image during animation
    private float r;                // radius of circle
    private Context context;


    public CirclePathAnimation(Context context, float radius){
        this.context = context;
        this.r = radius;
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
//        // calculate position of image center
//        int cxImage = width / 2;
//        int cyImage = height / 2;

        cx = Animation.RELATIVE_TO_PARENT;
        cy = Animation.RELATIVE_TO_PARENT;

        // set previous position to center
        prevX = cx;
        prevY = cy;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if(interpolatedTime == 0){
            // I ran into some issue where interpolated would be
            return;
        }

        float angleDeg = (interpolatedTime * 360f + 90) % 360;
        float angleRad = (float) Math.toRadians(angleDeg);

        // r = radius, cx and cy = center point, a = angle (radians)
        float x = (float) (cx + r * Math.cos(angleRad));
        float y = (float) (cy + r * Math.sin(angleRad));


        float dx = prevX - x;
        float dy = prevY - y;

        prevX = x;
        prevY = y;

        t.getMatrix().setTranslate(dx, dy);
    }
}
