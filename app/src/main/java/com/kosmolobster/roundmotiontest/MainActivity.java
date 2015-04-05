package com.kosmolobster.roundmotiontest;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private int ROTATING_CHILDS_COUNT = 3;
    private int UPDATE_ANIMATIONS_INTERVAL = 600;
    int itCount = 0;

    EmittingItemView[] rotatingItems = new EmittingItemView[ROTATING_CHILDS_COUNT];
    Handler hand = new Handler();

    //runnable for delay between rotating views animations
    final Runnable runnable = new Runnable() {
        public void run() {
            if(itCount <=  ROTATING_CHILDS_COUNT - 1) {
                rotatingItems[itCount].startDrawableAnimation();
                hand.postDelayed(runnable, UPDATE_ANIMATIONS_INTERVAL);
                ++itCount;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RotatingCustomView rotatingCustomView = (RotatingCustomView)findViewById(R.id.rotatingView);
        for (int i = 0; i < ROTATING_CHILDS_COUNT; ++i) {
            final EmittingItemView view = new EmittingItemView(this);

            view.setAnimation(R.drawable.anim_emit, 150);
            view.setCentralImage(R.drawable.bg0, 80);

            view.setText("View #" + String.valueOf(i));
            rotatingItems[i] = view;
            rotatingCustomView.addChild(view, i, ROTATING_CHILDS_COUNT);
        }

        hand.postDelayed(runnable, UPDATE_ANIMATIONS_INTERVAL);

        EmittingItemView center = new EmittingItemView(this);
        center.setCentralImage(R.drawable.bg0, 80);
        center.setText("Hey, you");
        rotatingCustomView.addCenterView(center);
        rotatingCustomView.startRotation();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
}
