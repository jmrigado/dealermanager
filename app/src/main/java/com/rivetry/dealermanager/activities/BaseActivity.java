package com.rivetry.dealermanager.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.rivetry.dealermanager.R;

public abstract class BaseActivity extends AppCompatActivity {


    public void navigateTo(int menuItemId){
        Intent intent;

        //Check to see which item was being clicked and perform appropriate action
        switch (menuItemId){

            case R.id.nav_home:
                intent = new Intent(getBaseContext(), HomeActivity.class);
                break;
            case R.id.nav_login:
                intent = new Intent(getBaseContext(), LoginActivity.class);
                break;
            default:
                intent = null;

        }

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void setTootlbarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}
