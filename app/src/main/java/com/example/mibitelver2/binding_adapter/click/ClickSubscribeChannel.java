package com.example.mibitelver2.binding_adapter.click;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.mibitelver2.R;

public class ClickSubscribeChannel {
    @BindingAdapter({"clicksubscribe"})
    public static void clickSubscribe(ImageView viewIm, int k) {
        final int[] a = {k};

        if(a[0] ==1){
            viewIm.setImageResource(R.drawable.check);
            a[0] =0;
        }else {
            viewIm.setImageResource(R.drawable.group);
            a[0]=1;
        }

    }
}
