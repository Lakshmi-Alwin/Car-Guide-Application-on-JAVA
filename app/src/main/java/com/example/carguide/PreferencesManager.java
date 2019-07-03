package com.example.carguide;

import android.content.SharedPreferences;

class PreferencesManager {
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor sharedPreferencesEditor;
    static String sharedAccountID;

    static String CLIENT_NAME = "clientname";
    private static String CLIENT_ADDR = "clientaddress";
    private static String CLIENT_PHONE = "clientphonenumber";
    private static String UNIT_OF_MEASUREMENT = "unitofmeasurement";
    private static String VIN_NUM = "vin";
    private static String VEHICLE_NICKNAME = "nickname";
    private static String CLIENT_EMAIL = "clientemail";

    static void saveEmail(String email) {
        sharedPreferencesEditor.putString(CLIENT_EMAIL, email);
        sharedPreferencesEditor.apply();
    }

    static void saveName(String name) {
        sharedPreferencesEditor.putString(CLIENT_NAME,name);
        sharedPreferencesEditor.apply();
    }

    static void savePhoneNumber(String phoneNumber) {
        sharedPreferencesEditor.putString(CLIENT_PHONE, phoneNumber);
        sharedPreferencesEditor.apply();
    }

    static void saveAddress(String address) {
        sharedPreferencesEditor.putString(CLIENT_ADDR, address);
        sharedPreferencesEditor.apply();
    }

    static void saveVIN(String vin) {
        sharedPreferencesEditor.putString(VIN_NUM, vin);
        sharedPreferencesEditor.apply();
    }

    static void saveVehicleNickName(String nickname) {
        sharedPreferencesEditor.putString(VEHICLE_NICKNAME, nickname);
        sharedPreferencesEditor.apply();
    }

    static void saveUnitOfMeasurement(String unitOfMeasurement) {
        sharedPreferencesEditor.putString(UNIT_OF_MEASUREMENT, unitOfMeasurement);
        sharedPreferencesEditor.apply();
    }
  
    static void saveEmpty(String string_to_save_in) {
        sharedPreferencesEditor.putString(string_to_save_in, "");
        sharedPreferencesEditor.apply();
    }

    static String getEmail() { return sharedPreferences.getString(CLIENT_EMAIL, ""); }
    static String getName() { return sharedPreferences.getString(CLIENT_NAME, ""); }
    static String getAddress() { return sharedPreferences.getString(CLIENT_ADDR, ""); }
    static String getPhoneNumber() { return sharedPreferences.getString(CLIENT_PHONE, ""); }
    static String getVIN() { return sharedPreferences.getString(VIN_NUM, "" ); }
    static String getVehicleNickname() { return sharedPreferences.getString(VEHICLE_NICKNAME, "" ); }
    static String getUnitOfMeasurement() { return sharedPreferences.getString(UNIT_OF_MEASUREMENT, ""); }

    static void deleteVIN(){
        sharedPreferencesEditor.remove(VIN_NUM);
        sharedPreferencesEditor.apply();
    }

    static void deleteVehicleNickname(){
        sharedPreferencesEditor.remove(VEHICLE_NICKNAME);
        sharedPreferencesEditor.apply();
    }
}
