package com.kosmolobster.roundmotiontest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;


public class RotatingCustomView extends FrameLayout {
    public View centerView;
    private int childsCount;
    private int angelCount;

    public RotatingCustomView(Context context) {
        super(context);
        init(null, 0);
    }

    public RotatingCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RotatingCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        childsCount = 0;
        angelCount = 0;
//        int numViews = 3;
//
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 150);
//
//        for(int i = 0; i < numViews; i++)
//        {
//            // Create some quick TextViews that can be placed.
//            EmittingItemView v = new EmittingItemView(getContext());
//            v.setGravity(Gravity.CENTER);
//            // Force the views to a nice size (150x100 px) that fits my display.
//            // This should of course be done in a display size independent way.
//
//            // Place all views in the center of the layout. We'll transform them
//            // away from there in the code below.
//            lp.gravity = Gravity.CENTER;
//            // Set layout params on view.
//            v.setLayoutParams(lp);
//
//            // Calculate the angle of the current view. Adjust by 90 degrees to
//            // get View 0 at the top. We need the angle in degrees and radians.
//            float angleDeg = i * 360.0f / numViews - 90.0f;
//            float angleRad = (float)(angleDeg * Math.PI / 180.0f);
//            // Calculate the position of the view, offset from center (300 px from
//            // center). Again, this should be done in a display size independent way.
//            v.setTranslationX(300 * (float)Math.cos(angleRad));
//            v.setTranslationY(300 * (float)Math.sin(angleRad));
//            // Set the rotation of the view.
//            //v.setRotation(angleDeg + 90.0f);
//            this.addView(v);
//        }
//
//        EmittingItemView center = new EmittingItemView(getContext());
//        center.setLayoutParams(lp);
//        center.setGravity(Gravity.CENTER);
//        this.addView(center);

//        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setDuration(3000);
//        animation.setRepeatCount(Animation.INFINITE);
//
//        this.startAnimation(animation);
    }

    public void addChild(View view) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 150);
        lp.gravity = Gravity.CENTER;

        //for(int i = 0; i < count; i++)
        //{
            view.setLayoutParams(lp);

            float angleDeg = angelCount * 360.0f / childsCount - 90.0f;
            float angleRad = (float)(angleDeg * Math.PI / 180.0f);

            view.setTranslationX(300 * (float)Math.cos(angleRad));
            view.setTranslationY(300 * (float)Math.sin(angleRad));

            this.addView(view);
        //}

        ++angelCount;
        ++childsCount;

        invalidate();
    }

    public void addCenterView(View view) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 150);
        lp.gravity = Gravity.CENTER;

        centerView = view;
        centerView.setLayoutParams(lp);
        //view.setGravity(Gravity.CENTER);
        this.addView(centerView);
    }

    public void startRotation() {
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setRepeatCount(Animation.INFINITE);

        this.startAnimation(animation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
