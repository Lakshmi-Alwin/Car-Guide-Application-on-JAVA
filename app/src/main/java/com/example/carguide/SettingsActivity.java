package com.example.carguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private String[] myDataset={"Unit of Measure","Terms & Privacy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Log.v(MainActivity.TAG,"IN settings");
        RecyclerView recyclerView = findViewById(R.id.recycler_settings);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Log.v(MainActivity.TAG,"before settings adapter");
        RecyclerView.Adapter mAdapter = new SettingsListAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        Log.v(MainActivity.TAG,"after settings adapter");
    }

    public class SettingsListAdapter extends RecyclerView.Adapter<SettingsListAdapter.MyViewHolder> {
        private String[] mDataset;

        class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView textView;
            LinearLayout linearLayout;
            Context context;
            MyViewHolder(View v) {
                super(v);
                context = v.getContext();
                textView = v.findViewById(R.id.faqQuestion);
                linearLayout = v.findViewById(R.id.faqLinearLayout);
            }
        }
        SettingsListAdapter(String[] myDataset) {
            this.mDataset = myDataset;
        }

        @NonNull
        @Override
        public SettingsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.faq_list,parent,false);
            return new MyViewHolder(listItem);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.v(MainActivity.TAG,"inside bindviewholder");
            holder.textView.setText(this.mDataset[position]);
            final int p = position;
            holder.linearLayout.setOnClickListener(v -> {
                Intent intent = null;
                if(p==0)
                    intent = new Intent(v.getContext(), UnitofMeasureActivity.class);
                if(p==1)
                    intent = new Intent(v.getContext(), TermsandPrivacyActivity.class);
                startActivity(intent);
            });

        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
