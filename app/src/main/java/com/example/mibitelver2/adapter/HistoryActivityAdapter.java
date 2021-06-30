package com.example.mibitelver2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;

import org.jetbrains.annotations.NotNull;

public class HistoryActivityAdapter extends RecyclerView.Adapter<HistoryActivityAdapter.HistoryActivityHolder> {

    @NonNull
    @NotNull
    @Override
    public HistoryActivityHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_item, parent, false);
        return new HistoryActivityHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HistoryActivityAdapter.HistoryActivityHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class HistoryActivityHolder extends RecyclerView.ViewHolder {
        public HistoryActivityHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
