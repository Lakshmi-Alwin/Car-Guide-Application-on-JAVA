package com.example.carguide;

import android.content.SharedPreferences;

public interface EditProfileContract {
    interface View {
        SharedPreferences getSharedPrefernces();
    }

    interface Presenter {
        void putData(String phoneNumber, String address, String name);
        String getName();
        String getAddress();
        String getPhoneNumber();
    }
}
