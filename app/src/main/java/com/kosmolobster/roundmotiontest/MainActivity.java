package com.kosmolobster.roundmotiontest;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;

import com.plattysoft.leonids.ParticleSystem;


public class MainActivity extends ActionBarActivity implements
        RoundMotionFragment.OnFragmentInteractionListener,
        CounterFragment.OnFragmentAnimationListener {

    RoundMotionFragment rmFragment;
    ParticleSystem ps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            rmFragment = new RoundMotionFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.fragmentLayout, rmFragment).commit();
        }
    }

    private void setBubbles() {
        ps = new ParticleSystem(this, 700, R.drawable.circle, 4000, R.id.bgLayout);
        ps.setScaleRange(0.5f, 2f);
        ps.setSpeedByComponentsRange(-0.01f, 0.01f, -0.01f, -1f);
        ps.setFadeOut(1000, new LinearInterpolator());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if( ps == null) {
            setBubbles();
        }
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
    public void onFragmentInteraction() {


        ps.emitWithGravity(findViewById(R.id.emiter_bottom), Gravity.TOP, 1000);
        rmFragment.startSlideAnimation();


    }

    @Override
    public void onEndAnimation() {
        getSupportFragmentManager()
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragmentLayout, new CounterFragment()).commit();
    }

    @Override
    public void onStopNumbersInteraction() {
        ps.stopEmitting();
    }
}
