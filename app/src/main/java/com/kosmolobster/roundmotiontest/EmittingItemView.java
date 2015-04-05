package com.kosmolobster.roundmotiontest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class EmittingItemView extends LinearLayout {
    public ImageView animationView;
    public ImageView centralImage;
    public TextView textView;
    public AnimationDrawable animationDrawable;

    public EmittingItemView(Context context) {
        super(context);
        init(null, 0);
    }

    public EmittingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EmittingItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.emitting_view, this);

        centralImage = (ImageView)this.findViewById(R.id.centerImg);
        animationView = (ImageView)this.findViewById(R.id.animationImg);
        textView = (TextView)this.findViewById(R.id.textView);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setCentralImage(int drawableId, int side) {
        centralImage.setBackground(getResources().getDrawable(drawableId));

        centralImage.getLayoutParams().height = side;
        centralImage.getLayoutParams().width = side;

        //invalidate();
    }

    public void startDrawableAnimation() {
        if(!animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }

    public void setAnimation(int animationId, int side) {
        animationDrawable = (AnimationDrawable) getResources().getDrawable(animationId);

        animationDrawable.setExitFadeDuration(400);
        animationDrawable.setEnterFadeDuration(400);

        animationView.setBackground(animationDrawable);

        animationView.getLayoutParams().height = side;
        animationView.getLayoutParams().width = side;

        invalidate();
    }

    public void setText(String text) {
        textView.setText(text);
        //requestLayout();

        //invalidate();
    }

//    public float dipToPixels(Context context, float dipValue) {
//        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
//    }
//
//    public int getAndroidAttrValue(AttributeSet attrs, String needed) {
//        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", needed);
//        String newStr = height.replaceAll("[^\\d.]", "");
//        return (int)dipToPixels(getContext(), Float.parseFloat(newStr));
//    }
}
