package com.coderz.f1.activityresultsmemorytest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class RecyclerViewDomainAdapter extends RecyclerView.Adapter {
    public interface OnItemClickedListener{
        void onItemClicked(int position);
    }

    private OnItemClickedListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    ArrayList<String> domains;
    LayoutInflater inflater;

    public RecyclerViewDomainAdapter(LayoutInflater inflater, ArrayList<String>domains, OnItemClickedListener itemClickedListener){
        this.listener = itemClickedListener;
        this.inflater = inflater;
        this.domains = domains;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView itemText = holder.itemView.findViewById(R.id.textview_domain);
        itemText.setText(domains.get(position));
        itemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return domains.size();
    }
}
