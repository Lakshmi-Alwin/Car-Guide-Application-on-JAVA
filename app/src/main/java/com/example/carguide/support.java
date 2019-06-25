package com.example.carguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class support extends Fragment implements SupportContract.View {

    static final String QUESTIONSNO = "Question NO";
    private SupportContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_support,container,false);

        presenter = new Supportpresenter(this);
        ImageView callLogo = v.findViewById(R.id.callLogo);
        ImageView emailLogo = v.findViewById(R.id.emailLogo);

        callLogo.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9409261943"));
            presenter.onClickSupport(getActivity());
            startActivity(intent);
        });

        emailLogo.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@xyz.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Support Needed");
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose your email client"));
        });

        String []questions = getResources().getStringArray(R.array.questions);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(new FAQListAdapter(questions));
        return  v;
    }
}
