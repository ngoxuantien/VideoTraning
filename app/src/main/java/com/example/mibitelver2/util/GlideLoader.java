package com.example.mibitelver2.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mibitelver2.R;

public class GlideLoader {

    public void loadPicture(String image, ImageView imageView, Context context) {
        Glide
                .with(context)
                .load(Uri.parse(image))
                .centerCrop()
                .placeholder(R.drawable.splash)
                .into(imageView);
    }
}