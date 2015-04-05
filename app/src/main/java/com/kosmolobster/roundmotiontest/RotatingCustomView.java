package com.kosmolobster.roundmotiontest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;


public class RotatingCustomView extends FrameLayout {
    public View centerView;
    private int CHILDREN_COUNT = 0;
    private int ROTATION_RADIUS = 300;
    private int ROTATION_ANIMATION_DURATION = 5000;
    private ArrayList<View> children;

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
        children = new ArrayList<>();

    }

    public void addChild(View view, int angelCount, int generalCount) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;

        view.setLayoutParams(lp);

        if(CHILDREN_COUNT == 0)
            CHILDREN_COUNT = generalCount;

//        put this code here if you don't want rotate animations, but just place it around the center view

//        float angleDeg = angelCount * 360.0f / generalCount - 90.0f;
//        float angleRad = (float)(angleDeg * Math.PI / 180.0f);
//
//        view.setTranslationX(300 * (float)Math.cos(angleRad));
//        view.setTranslationY(300 * (float)Math.sin(angleRad));

        this.addView(view);

        children.add(view);

        invalidate();
    }

    public void addCenterView(View view) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;

        centerView = view;
        centerView.setLayoutParams(lp);
        this.addView(centerView);
    }

    public void startRotation() {

        for (int i = 0; i < children.size(); ++i) {
            final int next = i;

            float angleDeg = i * 360.0f / CHILDREN_COUNT - 90.0f;
            final float angleRad = (float)(angleDeg * Math.PI / 180.0f);

            ValueAnimator animator = ValueAnimator.ofFloat(-1, 1);
            animator.setDuration(ROTATION_ANIMATION_DURATION);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = ((Float) (animation.getAnimatedValue()))
                            .floatValue();

                    children.get(next).setTranslationX((float)(ROTATION_RADIUS * Math.sin(value*Math.PI + angleRad)));
                    children.get(next).setTranslationY((float)(ROTATION_RADIUS * Math.cos(value*Math.PI + angleRad)));
                }
            });
            animator.setRepeatCount(Animation.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
