package br.com.fischborn.classificados.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Karen on 26/01/2018.
 */

public class AnimUtil {

    public static void replaceView(Context context, View oldView, View newView) {
        Animation animOut = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_out);
        Animation animIn = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_in);

        oldView.setVisibility(View.INVISIBLE);
        oldView.startAnimation(animOut);

        newView.setVisibility(View.VISIBLE);
        newView.startAnimation(animIn);
    }
}
