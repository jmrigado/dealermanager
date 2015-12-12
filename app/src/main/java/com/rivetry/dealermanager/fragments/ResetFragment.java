package com.rivetry.dealermanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rivetry.dealermanager.R;
import com.rivetry.dealermanager.activities.HomeActivity;

public class ResetFragment extends BaseFragment {


    private Button btnCancel;
    private Button btnReset;

    public ResetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_reset, container, false);

        btnCancel = (Button)view.findViewById(R.id.btnCancel);
        btnReset = (Button)view.findViewById(R.id.btnReset);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).hideResetFragment();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }





}
