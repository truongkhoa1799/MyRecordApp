package com.example.dailyapp.ui.dashboard.History;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyapp.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] myString;
    public MyAdapter(String[] str)
    {
        myString = str;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_history_date , parent , false);
        MyViewHolder mv = new MyViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_str.setText(myString[position]);
    }

    @Override
    public int getItemCount() {
        return myString.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text_str;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_str = itemView.findViewById(R.id.Key);
        }
    }

}
