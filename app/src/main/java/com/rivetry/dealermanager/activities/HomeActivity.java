package com.rivetry.dealermanager.activities;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
                showLoginActivity();
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

        User user;
        if (!userAddedToMenu && null!= (user = ((DealerManager)getApplication()).getUser())){
            final MenuItem mi = menu.add(user.getName());
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            userAddedToMenu = true;
        }

        return super.onPrepareOptionsMenu(menu);
    }

    public void setTootlbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showLoginActivity(){
        navigateTo(R.id.nav_login);
    }

    private void logout() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(getLayoutInflater().inflate(R.layout.dialog_logout, null))
                .setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((DealerManager) getApplication()).setUser(null);
                                invalidateOptionsMenu();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
        dialog.create().show();
    }

    private void showResetFragment(){
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
