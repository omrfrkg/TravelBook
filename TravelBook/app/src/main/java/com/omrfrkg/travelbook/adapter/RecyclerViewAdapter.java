package com.omrfrkg.travelbook.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omrfrkg.travelbook.databinding.RecyclerRowBinding;
import com.omrfrkg.travelbook.model.Place;
import com.omrfrkg.travelbook.view.MapsActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    List<Place> placeList;

    public RecyclerViewAdapter(List<Place> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RecyclerViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        holder.binding.recyclerViewText.setText(placeList.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MapsActivity.class);
                intent.putExtra("info","old");
                intent.putExtra("place",placeList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding binding;
        public RecyclerViewHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
