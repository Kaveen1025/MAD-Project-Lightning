package com.example.mad_project_design_phase;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter {
    public PurchaseHistoryAdapter(FirebaseRecyclerOptions<PurchaseHistoryModel> options) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void startListening() {
    }

    public void stopListening() {
    }
}
