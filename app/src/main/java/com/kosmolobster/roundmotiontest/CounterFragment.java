package com.kosmolobster.roundmotiontest;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CounterFragment extends Fragment {


    private OnFragmentAnimationListener mListener;



    public CounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bubbled, container, false);
        final TextView counterView = (TextView)view.findViewById(R.id.counter);

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, 3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                counterView.setText(String.valueOf(animation.getAnimatedValue()));
                if (animation.getAnimatedValue().equals(3000)) {
                    if (mListener != null) {
                        mListener.onStopNumbersInteraction();
                    }
                }
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round((endValue - startValue) * fraction);
            }
        });

        animator.setDuration(2000);
        animator.start();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentAnimationListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentAnimationListener {
        public void onStopNumbersInteraction();
    }
}
