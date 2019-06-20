package com.example.carguide;

import android.content.SharedPreferences;

public class EditProfilePresenter implements EditProfileContract.Presenter {
    private EditProfileContract.View view;
    private SharedPreferences preferences;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
        preferences = view.getSharedPrefernces();
    }

    @Override
    public void putData(String phoneNumber, String address, String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("clientname",name);
        editor.putString("clientaddress", address);
        editor.putString("clientphonenumber",phoneNumber);
        editor.apply();
    }

    @Override
    public String getName() {
        return preferences.getString("clientname","");
    }

    @Override
    public String getAddress() {
        return preferences.getString("clientaddress","");
    }

    @Override
    public String getPhoneNumber() {
        return preferences.getString("clientphonenumber","");
    }
}
