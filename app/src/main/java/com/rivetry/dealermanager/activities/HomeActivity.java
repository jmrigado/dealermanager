package com.rivetry.dealermanager.activities;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.rivetry.dealermanager.DealerManager;
import com.rivetry.dealermanager.R;
import com.rivetry.dealermanager.fragments.HomeFragment;
import com.rivetry.dealermanager.fragments.ResetFragment;
import com.rivetry.dealermanager.model.User;

public class HomeActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {

    private boolean userAddedToMenu = false;
    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null==((DealerManager) getApplication()).getUser()) {
                    showLoginActivity();
                }
            }
        });

        setTootlbarTitle("Table 12: Baccarat");

        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        // Monitor back stack changes to ensure the action bar shows the appropriate
        // button (either "photo" or "info").
        getFragmentManager().addOnBackStackChangedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_settings:
                break;
            case R.id.action_reset:
                showResetFragment();
                break;
            default:
                logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        final User user = ((DealerManager)getApplication()).getUser();
        if (!userAddedToMenu && null!=user){
            final MenuItem mi = menu.add(user.getName());
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            userAddedToMenu = true;
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private void showLoginActivity(){
        navigateTo(R.id.nav_login);
    }

    private void logout() {
        final Dialog dialog = new Dialog(this);
        final View view = getLayoutInflater().inflate(R.layout.dialog_logout, null);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        ((Button)view.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((Button)view.findViewById(R.id.btnConfirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DealerManager) getApplication()).setUser(null);
                invalidateOptionsMenu();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showResetFragment(){

        userAddedToMenu = false;

        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.
        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                        // Replace the default fragment animations with animator resources representing
                        // rotations when switching to the back of the card, as well as animator
                        // resources representing rotations when flipping back to the front (e.g. when
                        // the system Back button is pressed).
                .setCustomAnimations(
                        R.anim.card_flip_right_in, R.anim.card_flip_right_out)

                        // Replace any fragments currently in the container view with a fragment
                        // representing the next page (indicated by the just-incremented currentPage
                        // variable).
                .replace(R.id.container, new ResetFragment())

                        // Add this transaction to the back stack, allowing users to press Back
                        // to get to the front of the card.
                .addToBackStack(null)

                        // Commit the transaction.
                .commit();
    }

    public void hideResetFragment(){
        showResetFragment();
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }
}
