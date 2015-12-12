package com.rivetry.dealermanager.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.rivetry.dealermanager.activities.BaseActivity;

public abstract class BaseFragment extends Fragment {

    protected String fragmentTitle = "No title";
    protected BaseActivity baseActivity;
    protected boolean isRepairMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = ((BaseActivity)getActivity());
    }

    public String getFragmentTitle(){
        return fragmentTitle;
    }

}
