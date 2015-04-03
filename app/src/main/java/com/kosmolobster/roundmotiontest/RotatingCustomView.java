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
    }

    public void addChild(View view, int angelCount, int generalCount) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 150);
        lp.gravity = Gravity.CENTER;

        view.setLayoutParams(lp);

        float angleDeg = angelCount * 360.0f / generalCount - 90.0f;
        float angleRad = (float)(angleDeg * Math.PI / 180.0f);

        view.setTranslationX(300 * (float)Math.cos(angleRad));
        view.setTranslationY(300 * (float)Math.sin(angleRad));

        this.addView(view);

        invalidate();
    }

    public void addCenterView(View view) {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 150);
        lp.gravity = Gravity.CENTER;

        centerView = view;
        centerView.setLayoutParams(lp);
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
