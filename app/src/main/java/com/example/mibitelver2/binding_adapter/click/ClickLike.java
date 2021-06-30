package com.example.mibitelver2.binding_adapter.click;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.mibitelver2.R;

public class ClickLike {


    @BindingAdapter({"clickLike"})
    public static void setImage(ImageView view1, int a) {

        final Boolean[] click = {true};
        if (a == 0) {
            view1.setImageResource(R.drawable.group_15);
            click[0] = false;
        } else {
            view1.setImageResource(R.drawable.group_15checked);
            click[0] = true;
        }


    }


}
