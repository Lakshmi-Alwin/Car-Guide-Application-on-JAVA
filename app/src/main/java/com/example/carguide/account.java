package com.example.carguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

    private TextView signout_account;
    private GoogleSignInClient googleSignInClient;
    public static final String GOOGLE_ACCOUNT = "google_account";
    private TextView profileName, profileEmail;
    private CircleImageView profileImage;
    private View v;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account,container,false);

        Log.v(MainActivity.TAG, "in on create view");
        signout_account=v.findViewById(R.id.signout_account);
        Log.v(MainActivity.TAG, "after signout");
        profileName = v.findViewById(R.id.profile_text_account);
        Log.v(MainActivity.TAG, "after profile text");
        //profileEmail = v.findViewById(R.id.profile_email_account);
        profileImage = v.findViewById(R.id.profile_image_account);
        setDataOnView();

        signout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
          /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */
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
        return v;
    }

    private void setDataOnView() {
        Log.v(MainActivity.TAG, "SetData");
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        Log.v(MainActivity.TAG,"After getting account");
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
        SharedPreferences preferences = getActivity().getSharedPreferences(MainActivity.SHAREDPREFERENCES, Context.MODE_PRIVATE);
        if(!preferences.contains("clientname"))
            profileName.setText(googleSignInAccount.getDisplayName());
        else
            profileName.setText(preferences.getString("clientname", ""));
        //profileEmail.setText(googleSignInAccount.getEmail());
        Log.v(MainActivity.TAG,"exiting setData");
    }
}
