package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
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

public class EditProfile extends AppCompatActivity implements EditProfileContract.View {

    private EditProfileContract.Presenter presenter;
    private EditText phoneNumber, name, address;
    private TextView email;
    private CircleImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        presenter = new EditProfilePresenter(this);
        Button saveButton = findViewById(R.id.edit_profile_save);
        phoneNumber = findViewById(R.id.edit_profile_phone_number);
        name = findViewById(R.id.edit_profile_name);
        address = findViewById(R.id.edit_profile_address);
        email = findViewById(R.id.edit_profile_email);
        profilePic = findViewById(R.id.edit_profile_pic);
        Button cancelButton = findViewById(R.id.edit_profile_cancel);

        setData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || address.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()) {
                    onBackPressed();
                    return;
                }
                presenter.putData(phoneNumber.getText().toString(),address.getText().toString(),name.getText().toString());
                onBackPressed();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setData() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        email.setText(account.getEmail());
        Picasso.get().load(account.getPhotoUrl()).centerInside().fit().into(profilePic);
        if(presenter.getName().equals(""))
            name.setText(account.getDisplayName());
        else
            name.setText(presenter.getName());
        address.setText(presenter.getAddress());
        phoneNumber.setText(presenter.getPhoneNumber());
    }

    @Override
    public SharedPreferences getSharedPrefernces() {
        return getSharedPreferences(MainActivity.SHAREDPREFERENCES, Context.MODE_PRIVATE);
    }
}
