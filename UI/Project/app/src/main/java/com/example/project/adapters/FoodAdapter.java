package com.example.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;

import com.example.project.models.FoodReponse;

import java.util.List;
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private Context context;
    private List<FoodReponse> foodlist;

    public FoodAdapter(Context context, List<FoodReponse> foodlist) {
        this.context = context;
        this.foodlist = foodlist;
    }
    public void updateData(List<FoodReponse> newList) {
        this.foodlist = newList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FoodAdapter.MyViewHolder holder, int position) {
        FoodReponse food = foodlist.get(position);
        Glide.with(context)
                .load(food.getAvatar())
                .into(holder.images);
    }

    @Override
    public int getItemCount() {
        return foodlist == null ? 0 : foodlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public MyViewHolder(View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_food);
        }
    }
}
