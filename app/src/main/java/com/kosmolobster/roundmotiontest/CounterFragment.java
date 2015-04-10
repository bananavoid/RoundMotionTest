package com.kosmolobster.roundmotiontest;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CounterFragment extends Fragment {


    private OnFragmentAnimationListener listener;
    private View mainView;


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

        mainView = inflater.inflate(R.layout.fragment_counter, container, false);
        final TextView counterView = (TextView)mainView.findViewById(R.id.counter);
        final int positionY = (int)mainView.getY();
        final int offsetY = 80;

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, 3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int offsetCounter = 0;
            float alphaCounter = 0.0f;
            public void onAnimationUpdate(ValueAnimator animation) {
                counterView.setText(String.valueOf(animation.getAnimatedValue()));
                if (animation.getAnimatedValue().equals(3000)) {
                    if (listener != null) {
                        listener.onStopNumbersInteraction();
                    }
                }

                if (offsetCounter != offsetY) {
                    mainView.setTranslationY(positionY - offsetCounter);
                    mainView.setAlpha(alphaCounter);
                    offsetCounter+=2;
                    alphaCounter+=0.05f;
                }

            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round((endValue - startValue) * fraction);
            }
        });

        animator.setDuration(3000);
        animator.start();

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_out_translate);
        anim.setStartOffset(3000);
        LinearLayout lay = (LinearLayout)mainView.findViewById(R.id.contentLay);
        lay.startAnimation(anim);

        return mainView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentAnimationListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface OnFragmentAnimationListener {
        public void onStopNumbersInteraction();
    }
}
