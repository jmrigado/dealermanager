package com.rivetry.dealermanager;

import android.app.Application;

import com.rivetry.dealermanager.model.User;

public class DealerManager extends Application {

    public static final String DATA_USER = "com.rivetry.data.user";

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
