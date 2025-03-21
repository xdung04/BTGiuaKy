//22110346 Trần Tuấn Kha
package com.example.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.R;
import com.example.project.models.Category;
import com.example.project.models.CategoryResponse;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<CategoryResponse> categoryList;

    public CategoryAdapter(Context context, List<CategoryResponse> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    // Phương thức cập nhật dữ liệu cho Adapter
    public void updateData(List<CategoryResponse> newList) {
        this.categoryList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoryResponse category = categoryList.get(position);
        holder.tenSp.setText(category.getCategoryName());
        Glide.with(context)
                .load(category.getAvatar())
                .into(holder.images);
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView tenSp;

        public MyViewHolder(View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_cate);
            tenSp = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}