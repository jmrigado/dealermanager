package com.rivetry.dealermanager.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rivetry.dealermanager.R;

public class HomeFragment extends BaseFragment {

    private Button btnGameControls;
    private TextView btnCloseGameControls;
    private LinearLayout viewGameControls;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewGameControls = (LinearLayout)view.findViewById(R.id.viewGameControls);
        btnGameControls = (Button)view.findViewById(R.id.btnGameControls);
        btnCloseGameControls = (TextView)view.findViewById(R.id.btnCloseGameControls);
        btnGameControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameControlsPanel();
            }
        });
        btnCloseGameControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideGameControlsPanel();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showGameControlsPanel() {
        final int cx = (viewGameControls.getLeft() + viewGameControls.getRight()) / 2;
        final int cy = viewGameControls.getBottom();

        final int finalRadius = Math.max(viewGameControls.getWidth(), viewGameControls.getHeight());

        final Animator anim = ViewAnimationUtils.createCircularReveal(viewGameControls, cx, cy, 0, finalRadius);
        anim.setDuration(500);
        btnGameControls.setVisibility(View.GONE);
        viewGameControls.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void hideGameControlsPanel() {
        final int cx = (viewGameControls.getLeft() + viewGameControls.getRight()) / 2;
        final int cy = viewGameControls.getBottom();

        final int initialRadius = viewGameControls.getWidth();

        final Animator anim = ViewAnimationUtils.createCircularReveal(viewGameControls, cx, cy,initialRadius, 0);
        anim.setDuration(getResources().getInteger(R.integer.circular_reveal_time));

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewGameControls.setVisibility(View.INVISIBLE);
                btnGameControls.setVisibility(View.VISIBLE);
            }
        });
        anim.start();
    }




}
