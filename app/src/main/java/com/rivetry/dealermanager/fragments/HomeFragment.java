package com.rivetry.dealermanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rivetry.dealermanager.R;
import com.rivetry.dealermanager.custom.IconButton;

public class HomeFragment extends BaseFragment {

    private Button btnGameControls;
    private TextView btnCloseGameControls;
    private LinearLayout viewGameControls;
    private IconButton barCallButton;
    private IconButton chipsCallButton;
    private IconButton fsrCallButton;
    private IconButton securityCallButton;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewGameControls = (LinearLayout) view.findViewById(R.id.viewGameControls);
        btnGameControls = (Button) view.findViewById(R.id.btnGameControls);
        btnCloseGameControls = (TextView) view.findViewById(R.id.btnCloseGameControls);
        btnGameControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameControlsPanel();
            }
        });
        barCallButton = (IconButton) view.findViewById(R.id.barCallButton);
        chipsCallButton = (IconButton) view.findViewById(R.id.chipsCallButton);
        fsrCallButton = (IconButton) view.findViewById(R.id.fsrCallButton);
        securityCallButton = (IconButton) view.findViewById(R.id.securityCallButton);

        btnCloseGameControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideGameControlsPanel();
            }
        });

        barCallButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        chipsCallButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        fsrCallButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        securityCallButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showGameControlsPanel() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_up);
        anim.setDuration(500);
        viewGameControls.startAnimation(anim);
        btnGameControls.setVisibility(View.GONE);
        viewGameControls.setVisibility(View.VISIBLE);
    }

    private void hideGameControlsPanel() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_down);
        anim.setDuration(500);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewGameControls.setVisibility(View.INVISIBLE);
                btnGameControls.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewGameControls.startAnimation(anim);
    }
}
