package com.coolcats.randomusergeneratorproject.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coolcats.randomusergeneratorproject.databinding.UserItemLayoutBinding;
import com.coolcats.randomusergeneratorproject.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<UserResponse> responses;

    public UserAdapter(List<UserResponse> responses) {
        this.responses = responses;
    }


    public void setResponses(List<UserResponse> responses) {
        this.responses = responses;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserItemLayoutBinding binding = UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {

        UserResponse item = responses.get(position);

        Glide.with(holder.itemView.getRootView())
                .load(item.getPicture())
                .into(holder.binding.profileImageImageview);

        holder.binding.usernameTextview.setText(item.getLogin().getUsername());

    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        UserItemLayoutBinding binding;

        public UserViewHolder(UserItemLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
