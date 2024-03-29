package com.example.carguide;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.carguide.support.QUESTIONSNO;

public class FAQListAdapter extends RecyclerView.Adapter<FAQListAdapter.ViewHolder> {

    private String[] faqList;
    public static Context context;


    FAQListAdapter(String[] faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.faq_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    //@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String question = faqList[position];
            final int p = position;
            holder.faqTextView.setText(question);
            holder.linearLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, FAQActivity.class);
                intent.putExtra(QUESTIONSNO, p);
                context.startActivity(intent);
            });
        }



    @Override
    public int getItemCount() {
        return faqList.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView faqTextView;
        LinearLayout linearLayout;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.faqTextView=itemView.findViewById(R.id.faqQuestion);
            this.linearLayout=itemView.findViewById(R.id.faqLinearLayout);
        }
    }
}
