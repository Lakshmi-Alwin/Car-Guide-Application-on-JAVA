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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


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

        ListView listView = v.findViewById(R.id.questionList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FAQActivity.class);
                intent.putExtra(QUESTIONSNO, position);
                startActivity(intent);
            }
        });

        return  v;
    }
}
