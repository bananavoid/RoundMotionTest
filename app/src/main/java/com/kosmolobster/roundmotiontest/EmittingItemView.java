package com.kosmolobster.roundmotiontest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class EmittingItemView extends RelativeLayout {

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
        RelativeLayout.LayoutParams params = new LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        this.setLayoutParams(params);
        this.setClipChildren(true);
        this.setAlpha(0.6f);

        ImageView centralImage = new ImageView(getContext());

        AnimationDrawable animationDrawable = (AnimationDrawable)
                getResources().getDrawable(R.drawable.anim_emit);

        centralImage.setBackground(getResources().getDrawable(R.drawable.bg0));

        int thisHeight;
        int thisWidth;

        if (attrs != null ) {
            thisHeight  = getAndroidAttrValue(attrs, "layout_height");
            thisWidth = getAndroidAttrValue(attrs, "layout_width");
        } else {
            thisHeight = 100;
            thisWidth = 100;
        }


        RelativeLayout.LayoutParams layoutParams = new LayoutParams(thisHeight/3, thisWidth/3);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        centralImage.setLayoutParams(layoutParams);

        animationDrawable.setExitFadeDuration(400);
        animationDrawable.setEnterFadeDuration(400);

        this.setBackground(animationDrawable);

        this.addView(centralImage);

        animationDrawable.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public int getAndroidAttrValue(AttributeSet attrs, String needed) {
        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", needed);
        String newStr = height.replaceAll("[^\\d.]", "");
        return (int)dipToPixels(getContext(), Float.parseFloat(newStr));
    }
}
