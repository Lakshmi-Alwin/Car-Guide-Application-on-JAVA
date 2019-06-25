package com.example.carguide;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
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


    @Test
    public void saveClientNameInSharedPreferences() {
        PreferencesManager.sharedAccountID = sharedPreferencesAccountID;
        PreferencesManager.sharedPreferences = context.getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
        PreferencesManager.saveName(name);
        String result = PreferencesManager.getName();

        // ...then the result should be the expected one.
        assertEquals(name, result);
    }

    @Test
    public void saveClientPhoneNumberInSharedPreferences() {
        PreferencesManager.sharedAccountID = sharedPreferencesAccountID;
        PreferencesManager.sharedPreferences = context.getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
        PreferencesManager.savePhoneNumber(phone);
        String result = PreferencesManager.getPhoneNumber();

        // ...then the result should be the expected one.
        assertEquals(phone, result);
    }

    @Test
    public void saveClientAddressInSharedPreferences() {
        PreferencesManager.sharedAccountID = sharedPreferencesAccountID;
        PreferencesManager.sharedPreferences = context.getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
        PreferencesManager.saveAddress(address);
        String result = PreferencesManager.getAddress();

        // ...then the result should be the expected one.
        assertEquals(address, result);
    }

    @Test
    public void saveVehicleVINInSharedPreferences() {
        PreferencesManager.sharedAccountID = sharedPreferencesAccountID;
        PreferencesManager.sharedPreferences = context.getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
        PreferencesManager.saveVIN(vin);
        String result = PreferencesManager.getVIN();

        // ...then the result should be the expected one.
        assertEquals(vin, result);
    }

    @Test
    public void saveVehicleNickNameInSharedPreferences() {
        PreferencesManager.sharedAccountID = sharedPreferencesAccountID;
        PreferencesManager.sharedPreferences = context.getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
        PreferencesManager.saveVehicleNickName(vehicle_nickname);
        String result = PreferencesManager.getVehicleNickname();

        // ...then the result should be the expected one.
        assertEquals(vehicle_nickname, result);
    }
}
