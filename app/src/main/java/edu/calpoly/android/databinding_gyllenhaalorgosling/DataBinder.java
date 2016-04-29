package edu.calpoly.android.databinding_gyllenhaalorgosling;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DataBinder {

    @BindingAdapter("bind:timerText")
    public static void setTimerText(TextView tv, long time) {
        tv.setText(String.format("%.3f", ((float) time) / 1000));
    }


    @BindingAdapter("bind:roundImage")
    public static void setTimerText(ImageView iv, @DrawableRes int drawable) {
        Glide.with(iv.getContext())
                .load(drawable)
                .bitmapTransform(new RoundedCornersTransformation(iv.getContext(), 15, 15))
                .into(iv);
    }
}
