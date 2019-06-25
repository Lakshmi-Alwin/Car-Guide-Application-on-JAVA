package com.example.carguide;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import static android.content.Context.MODE_PRIVATE;
import static org.junit.Assert.assertEquals;


public class PreferencesManagerTest {
    private Context context = InstrumentationRegistry.getInstrumentation().getContext();
    private static String sharedPreferencesAccountID = "debugSharedAccountID";

    private static String vin = "1234567890ABCDEFG";
    private static String vehicle_nickname = "MyVehicle";
    private static String name = "ClientName";
    private static String phone = "12345";
    private static String address = "MyAddress";
    private static String unit_of_measurement = "Miles";

    @Before
    public void prepareSharedPreferences() {
        PreferencesManager.sharedAccountID = sharedPreferencesAccountID;
        PreferencesManager.sharedPreferences = context.getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
    }

    @Test
    public void saveClientNameInSharedPreferences() {
        PreferencesManager.saveName(name);
        String result = PreferencesManager.getName();

        assertEquals(name, result);
    }

    @Test
    public void saveClientPhoneNumberInSharedPreferences() {
        PreferencesManager.savePhoneNumber(phone);
        String result = PreferencesManager.getPhoneNumber();

        assertEquals(phone, result);
    }

    @Test
    public void saveClientAddressInSharedPreferences() {
        PreferencesManager.saveAddress(address);
        String result = PreferencesManager.getAddress();

        assertEquals(address, result);
    }

    @Test
    public void saveVehicleVINInSharedPreferences() {
        PreferencesManager.saveVIN(vin);
        String result = PreferencesManager.getVIN();

        assertEquals(vin, result);
    }

    @Test
    public void saveVehicleNickNameInSharedPreferences() {
        PreferencesManager.saveVehicleNickName(vehicle_nickname);
        String result = PreferencesManager.getVehicleNickname();

        assertEquals(vehicle_nickname, result);
    }

    @Test
    public void saveUnitOfMeasurementInSharedPreferences() {
        PreferencesManager.saveUnitOfMeasurement(unit_of_measurement);
        String result = PreferencesManager.getUnitOfMeasurement();

        assertEquals(unit_of_measurement, result);
    }
}
