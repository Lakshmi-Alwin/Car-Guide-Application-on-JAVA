package com.example.carguide;

import android.content.SharedPreferences;

public interface EditProfileContract {
    interface View {
        SharedPreferences getSharedPrefernces();
    }

    interface Presenter {
        void putName(String name);
        void putPhoneNumber(String phoneNumber);
        void putAddress(String address);
        String getName();
        String getAddress();
        String getPhoneNumber();
    }
}
