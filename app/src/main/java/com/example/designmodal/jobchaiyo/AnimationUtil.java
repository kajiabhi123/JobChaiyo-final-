package com.example.designmodal.jobchaiyo;

/**
 * Created by compware on 5/31/2018.
 */
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

    /**
     * Created by Kuldip on 5/30/2018.
     */

    public class AnimationUtil {
        public  static  void animate(RecyclerView.ViewHolder holder, boolean goesDown)
        {
            AnimatorSet animatorSet = new AnimatorSet();

//        ObjectAnimator rotation = ObjectAnimator.ofFloat(holder.itemView,"rotation",goesDown==true ? 360:-360,0);
            ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView,"translationY",goesDown==true ? 100:-100,0);

//        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(holder.itemView, "ScaleX", -0.5f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 2f);
//        ObjectAnimator pivotY = ObjectAnimator.ofFloat(holder.itemView, "pivotY", 0);
//        animatorSet.playTogether(scaleY,pivotY);

            animatorSet.setDuration(1000);

            animatorSet.playTogether(animatorTranslateY);
            animatorSet.start();

        }
    }
