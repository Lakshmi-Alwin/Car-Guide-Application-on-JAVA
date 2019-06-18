package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private Button saveButton;
    private EditText phoneNumber, name, address;
    private TextView email;
    private CircleImageView profilePic;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferences = getSharedPreferences(MainActivity.SHAREDPREFERENCES, Context.MODE_PRIVATE);
        editor = preferences.edit();
        saveButton = findViewById(R.id.edit_profile_save);
        phoneNumber = findViewById(R.id.edit_profile_phone_number);
        name = findViewById(R.id.edit_profile_name);
        address = findViewById(R.id.edit_profile_address);
        email = findViewById(R.id.edit_profile_email);
        profilePic = findViewById(R.id.edit_profile_pic);

        setData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText() == null || address.getText() == null || phoneNumber.getText() == null)
                    onBackPressed();
                editor.putString("clientphonenumber",phoneNumber.getText().toString());
                editor.putString("clientaddress",address.getText().toString());
                editor.putString("clientname",name.getText().toString());
                editor.apply();
                onBackPressed();
            }
        });
    }

    private void setData() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        email.setText(account.getEmail());
        Picasso.get().load(account.getPhotoUrl()).centerInside().fit().into(profilePic);
        if(!preferences.contains("clientname"))
            name.setText(account.getDisplayName());
        else
            name.setText(preferences.getString("clientname", ""));
        if(preferences.contains("clientaddress"))
            address.setText(preferences.getString("clientaddress", ""));
        if(preferences.contains("clientphonenumber"))
            phoneNumber.setText(preferences.getString("clientphonenumber", ""));
    }
}
