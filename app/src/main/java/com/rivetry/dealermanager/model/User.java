package com.rivetry.dealermanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name = "Jane Dealer";
    private int idNumber = 123456789;

    public User() {}

    public User(Parcel in) {
        name = in.readString();
        idNumber = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(idNumber);
    }
}
