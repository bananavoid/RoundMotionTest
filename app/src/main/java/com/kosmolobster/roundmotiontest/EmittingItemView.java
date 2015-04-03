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

        ImageView emittingAnimation = new ImageView(getContext());
        ImageView centralImage = new ImageView(getContext());

        AnimationDrawable animationDrawable = (AnimationDrawable)
                getResources().getDrawable(R.drawable.anim_emit);

        //emittingAnimation.setBackground(animationDrawable);
        centralImage.setBackground(getResources().getDrawable(R.drawable.bg0));

        RelativeLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        emittingAnimation.setLayoutParams(layoutParams);
        centralImage.setLayoutParams(layoutParams);

        centralImage.getLayoutParams().height = this.getLayoutParams().height - 40;
        centralImage.getLayoutParams().width = this.getLayoutParams().width - 40;

        centralImage.setAlpha(0.8f);

        emittingAnimation.setAlpha(0.7f);
        animationDrawable.setExitFadeDuration(400);

        this.setBackground(animationDrawable);

        this.addView(centralImage);

        animationDrawable.start();
        animationDrawable.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
