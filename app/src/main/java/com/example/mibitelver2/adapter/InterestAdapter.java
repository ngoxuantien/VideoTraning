package com.example.mibitelver2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.view.InterestActivity;
import com.example.mibitelver2.model.tumodel.Category;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder>{
    private List<Category> categories;
    private Context context;

    public InterestAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.interest_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category category = categories.get(position);
        holder.textView.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, category.getImage(), Toast.LENGTH_SHORT).show();
                if (holder.textView.getCurrentTextColor() == Color.parseColor("#5b6b7b")){
                    holder.layout.setBackgroundResource(R.drawable.bg_interest_item_click);
                    holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
                    ((InterestActivity)(context)).setChosen(category.getId());
                }else{
                    holder.layout.setBackgroundResource(R.drawable.bg_interest_item);
                    holder.textView.setTextColor(Color.parseColor("#5b6b7b"));
                    ((InterestActivity)(context)).removeChosen(category.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.r005_item_text);
            layout = itemView.findViewById(R.id.r005_layout_item);
        }
    }
}
