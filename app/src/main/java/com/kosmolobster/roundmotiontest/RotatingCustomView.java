package com.kosmolobster.roundmotiontest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import java.util.ArrayList;


public class RotatingCustomView extends FrameLayout {
    public View centerView;
    private int CHILDREN_COUNT = 0;
    private ArrayList<View> children;
    private Animator animator;
    private Path circle;

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
//
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

//        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setDuration(3000);
//        animation.setRepeatCount(Animation.INFINITE);
//
//        this.startAnimation(animation);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

//        Animation animation = new CirclePathAnimation(getContext(), 300);
//        animation.setDuration(5000);
//        animation.setFillEnabled(true);
//        animation.setFillAfter(true);
//        animation.setStartOffset(100);
//        animation.setRepeatCount(Animation.INFINITE);


        for (int i = 0; i < children.size(); ++i) {
            final int next = i;

            float angleDeg = i * 360.0f / CHILDREN_COUNT - 90.0f;
            final float angleRad = (float)(angleDeg * Math.PI / 180.0f);

            ValueAnimator animator = ValueAnimator.ofFloat(-1, 1); // values from 0 to 1
            animator.setDuration(5000); // 5 seconds duration from 0 to 1
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = ((Float) (animation.getAnimatedValue()))
                            .floatValue();
                    // Set translation of your view here. Position can be calculated
                    // out of value. This code should move the view in a half circle.
                    children.get(next).setTranslationX((float)(300.0 * Math.sin(value*Math.PI + angleRad)));
                    children.get(next).setTranslationY((float)(300.0 * Math.cos(value*Math.PI + angleRad)));
                }
            });
            //children.get(i).startAnimation(animator);
            animator.setRepeatCount(Animation.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setStartDelay(200);
            animator.start();
        }

//        LayoutAnimationController controller = new LayoutAnimationController(animator);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        controller.setDelay(0.0f);
//        this.setLayoutAnimation(controller);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
