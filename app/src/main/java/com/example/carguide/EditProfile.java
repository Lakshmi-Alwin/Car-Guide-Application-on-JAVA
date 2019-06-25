package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private EditText phoneNumber, name, address;
    private TextView email;
    private CircleImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button saveButton = findViewById(R.id.edit_profile_save);
        phoneNumber = findViewById(R.id.edit_profile_phone_number);
        name = findViewById(R.id.edit_profile_name);
        address = findViewById(R.id.edit_profile_address);
        email = findViewById(R.id.edit_profile_email);
        profilePic = findViewById(R.id.edit_profile_pic);
        Button cancelButton = findViewById(R.id.edit_profile_cancel);

        setData();

        saveButton.setOnClickListener(v -> {
            if(name.getText().toString().isEmpty() || address.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()) {
                onBackPressed();
                return;
        }
        PreferencesManager.saveAddress(address.getText().toString());
        PreferencesManager.savePhoneNumber(phoneNumber.getText().toString());
        PreferencesManager.saveName(name.getText().toString());
        onBackPressed();
    });

        cancelButton.setOnClickListener((v) -> onBackPressed());
    }

    private void setData() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        email.setText(account.getEmail());
        Picasso.get().load(account.getPhotoUrl()).centerInside().fit().into(profilePic);
        if(PreferencesManager.getName().equals(""))
            name.setText(account.getDisplayName());
        else
            name.setText(PreferencesManager.getName());
        address.setText(PreferencesManager.getAddress());
        phoneNumber.setText(PreferencesManager.getPhone());
    }

}
