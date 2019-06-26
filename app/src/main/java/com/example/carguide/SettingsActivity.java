package com.example.carguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private String[] myDataset={"Unit of Measure","Terms & Privacy", "Remove Vehicle"};

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
                switch (p){
                    case 0:
                        startActivity(new Intent(SettingsActivity.this, UnitofMeasureActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(SettingsActivity.this, TermsandPrivacyActivity.class));
                        break;
                    case 2:
                        if(PreferencesManager.getVIN().equals("")) {
                            Toast.makeText(SettingsActivity.this, "Vehicle does not exist", Toast.LENGTH_LONG).show();
                            return;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                        builder.setTitle("Remove Vehicle");
                        builder.setMessage("Do you really want to remove vehicle").setCancelable(false).setPositiveButton("YES", (dialog, which) -> {
                            finish();
                            PreferencesManager.deleteVehicleNickname();
                            PreferencesManager.deleteVIN();
                        }).setNegativeButton("No", (dialog, which) -> {
                            dialog.cancel();
                        });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Remove Vehicle");
                        alert.show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
