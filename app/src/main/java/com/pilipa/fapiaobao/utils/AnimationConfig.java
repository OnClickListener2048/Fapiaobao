package com.pilipa.fapiaobao.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.pilipa.fapiaobao.R;


public class AnimationConfig {

    public static void shake(Context context,View view) {
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
        view.startAnimation(shake);
    }

}
