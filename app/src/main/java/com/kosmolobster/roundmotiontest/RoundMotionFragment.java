package com.kosmolobster.roundmotiontest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class RoundMotionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int ROTATING_CHILDS_COUNT = 3;
    private int UPDATE_ANIMATIONS_INTERVAL = 600;
    private int itCount = 0;
    public View mainView;
    public Button nextBtn;
    private int[] userImagesIds = new int[] {
            R.drawable.user0,
            R.drawable.user1,
            R.drawable.user3
    };

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


    public RoundMotionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round_motion, container, false);

        nextBtn = (Button) view.findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction();
                }
            }
        });

        mainView = view.findViewById(R.id.mainView);

        RotatingCustomView rotatingCustomView = (RotatingCustomView)view.findViewById(R.id.rotatingView);
        for (int i = 0; i < ROTATING_CHILDS_COUNT; ++i) {
            final EmittingItemView emittingItemView = new EmittingItemView(getActivity());

            emittingItemView.setAnimation(R.drawable.anim_emit, 150);
            emittingItemView.setCentralImage(userImagesIds[i], 80);

            emittingItemView.setText("View #" + String.valueOf(i));
            rotatingItems[i] = emittingItemView;
            rotatingCustomView.addChild(emittingItemView, i, ROTATING_CHILDS_COUNT);
        }

        hand.postDelayed(runnable, UPDATE_ANIMATIONS_INTERVAL);

        EmittingItemView center = new EmittingItemView(getActivity());
        center.setCentralImage(R.drawable.user2, 80);
        center.setText("Hey, you");
        rotatingCustomView.addCenterView(center);
        rotatingCustomView.startRotation();


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    public void startSlideAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_top);

        mainView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mListener != null) {
                    mListener.onEndAnimation();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        nextBtn.setVisibility(View.GONE);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction();
        public void onEndAnimation();
    }

}
