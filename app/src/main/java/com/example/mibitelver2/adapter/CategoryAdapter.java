package com.example.mibitelver2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mibitelver2.R;
import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.util.OnClickInterface;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categories;
    private Context context;
    private OnClickInterface onClick;

    public CategoryAdapter(List<Category> categories, Context context, OnClickInterface onClick) {
        this.categories = categories;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.home_category,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        Glide.with(context).load(category.getImage()).placeholder(R.drawable.favorite_type_video).into(holder.imgCategory);
        holder.txtCategory.setText(category.getName());
        RecyclerView.LayoutParams params =
                (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (position != (categories.size() - 1)) {
            params.rightMargin = holder.itemView.getResources().getDimensionPixelOffset(R.dimen._25sdp);
        } else {
            params.rightMargin = 0;
        }
        holder.txtCategory.setTextColor(Color.parseColor(category.getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCategory;
        TextView txtCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.home_item_category_image);
            txtCategory = itemView.findViewById(R.id.home_item_category_name);
        }
    }
}
