package com.example.carguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class support extends Fragment {
    public static final String QUESTIONSNO = "Question NO";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_support,container,false);
        ImageView callLogo = v.findViewById(R.id.callLogo);
        ImageView emailLogo = v.findViewById(R.id.emailLogo);

        callLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:9409261943"));
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                    Log.v(MainActivity.TAG, "No permission to call");
                    return;
                }
                startActivity(intent);
            }
        });

        emailLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@xyz.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Support Needed");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose your email client"));
            }
        });

        String []questions = getResources().getStringArray(R.array.questions);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        FAQListAdaper adaper = new FAQListAdaper(questions);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adaper);
        return  v;
    }
}
