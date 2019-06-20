package com.example.carguide;

import android.content.SharedPreferences;

public class EditProfilePresenter implements EditProfileContract.Presenter {
    private EditProfileContract.View view;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
        preferences = view.getSharedPrefernces();
        editor = preferences.edit();
    }

    @Override
    public void putName(String name) {
        editor.putString("clientname",name);
        editor.apply();
    }

    @Override
    public void putPhoneNumber(String phoneNumber) {
        editor.putString("clientphonenumber",phoneNumber);
        editor.apply();
    }

    @Override
    public void putAddress(String address) {
        editor.putString("clientaddress", address);
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
