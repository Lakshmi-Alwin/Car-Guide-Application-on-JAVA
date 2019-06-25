package com.example.carguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class account extends Fragment {

    private GoogleSignInClient googleSignInClient;
    private TextView profileName;
    private CircleImageView profileImage;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        Log.v(MainActivity.TAG, "in on create view");
        TextView signout_account = v.findViewById(R.id.signout_account);
        Log.v(MainActivity.TAG, "after signout");
        profileName = v.findViewById(R.id.profile_text_account);
        Log.v(MainActivity.TAG, "after profile text");
        profileImage = v.findViewById(R.id.profile_image_account);
        setDataOnView();

        signout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
                builder.requestEmail();
                GoogleSignInOptions gso = builder.build();
                googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent intent=new Intent(getActivity(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            }
        });

        RelativeLayout editProfile = v.findViewById(R.id.edit_profile_account);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditProfile.class);
                startActivity(intent);
            }
        });

        RelativeLayout settings = v.findViewById(R.id.settings_account);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SettingsActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void setDataOnView() {
        Log.v(MainActivity.TAG, "SetData");
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        Log.v(MainActivity.TAG,"After getting account");
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
        if(!PreferencesManager.sharedPreferences.contains(PreferencesManager.CLIENT_NAME))
            profileName.setText(googleSignInAccount.getDisplayName());
        else
            profileName.setText(PreferencesManager.getName());
        Log.v(MainActivity.TAG,"exiting setData");
    }
}
