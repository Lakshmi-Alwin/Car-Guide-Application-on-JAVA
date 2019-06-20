package com.example.carguide;

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
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset={"Unit of Measure","Terms & Privacy"};
    //private String[] settingslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Log.v(MainActivity.TAG,"IN settings");
        recyclerView = findViewById(R.id.recycler_settings);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Log.v(MainActivity.TAG,"before settings adapter");
        mAdapter = new SettingsListAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        Log.v(MainActivity.TAG,"after settings adapter");
    }

    public class SettingsListAdapter extends RecyclerView.Adapter<SettingsListAdapter.MyViewHolder> {
        private String[] mDataset;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView textView;
            Context context;
            MyViewHolder(View v) {
                super(v);
                context = v.getContext();
                textView = v.findViewById(R.id.faqQuestion);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public SettingsListAdapter(String[] myDataset) {
            this.mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public SettingsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.faq_list,parent,false);
            SettingsListAdapter.MyViewHolder viewHolder = new SettingsListAdapter.MyViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            Log.v(MainActivity.TAG,"inside bindviewholder");
            holder.textView.setText(this.mDataset[position]);
            final int p = position;
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    if(p==0)
                        intent = new Intent(v.getContext(), UnitofMeasureActivity.class);
                    if(p==1)
                        intent = new Intent(v.getContext(), TermsandPrivacyActivity.class);
                    startActivity(intent);
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
