package com.example.carguide;

import android.content.SharedPreferences;

public class PreferencesManager {
    public static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;
    public static String sharedAccountID;

    public static String CLIENT_NAME = "clientname";
    public static String CLIENT_ADDR = "clientaddress";
    public static String CLIENT_PHONE = "clientphonenumber";
    public static String UNIT_OF_MEASUREMENT = "unitofmeasurement";
    public static String VIN_NUM = "vin";
    public static String VEHICLE_NICKNAME = "nickname";

    public static void saveName(String name) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(CLIENT_NAME,name);
        sharedPreferencesEditor.apply();
    }

    public static void savePhoneNumber(String phoneNumber) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(CLIENT_PHONE, phoneNumber);
        sharedPreferencesEditor.apply();
    }

    public static void saveAddress(String address) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(CLIENT_ADDR, address);
        sharedPreferencesEditor.apply();
    }

    public static void saveVIN(String vin) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(VIN_NUM, vin);
        sharedPreferencesEditor.apply();
    }

    public static void saveVehicleNickName(String nickname) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(VEHICLE_NICKNAME, nickname);
        sharedPreferencesEditor.apply();
    }

    public static void saveUnitOfMeasurement(String unitOfMeasurement) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(UNIT_OF_MEASUREMENT, unitOfMeasurement);
        sharedPreferencesEditor.apply();
    }


    public static String getName() { return sharedPreferences.getString(CLIENT_NAME, ""); }
    public static String getAddress() { return sharedPreferences.getString(CLIENT_ADDR, ""); }
    public static String getPhone() { return sharedPreferences.getString(CLIENT_PHONE, ""); }
    public static String getVIN() { return sharedPreferences.getString(VIN_NUM, "" ); }
    public static String getVehicleNickname() { return sharedPreferences.getString(VEHICLE_NICKNAME, "" ); }

}
