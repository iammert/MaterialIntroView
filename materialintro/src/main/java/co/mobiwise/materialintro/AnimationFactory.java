package co.mobiwise.materialintro;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class AnimationFactory {

    /**
     * MaterialIntroView will appear on screen with
     * fade in animation. Notifies onAnimationStartListener
     * when fade in animation is about to start.
     * @param view
     * @param duration
     * @param onAnimationStartListener
     */
    public static void animateFadeIn(View view, long duration, AnimationListener.OnAnimationStartListener onAnimationStartListener){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(onAnimationStartListener != null)
                    onAnimationStartListener.onAnimationStart();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    /**
     * MaterialIntroView will disappear from screen with
     * fade out animation. Notifies onAnimationEndListener
     * when fade out animation is ended.
     * @param view
     * @param duration
     * @param onAnimationEndListener
     */
    public static void animateFadeOut(View view, long duration, AnimationListener.OnAnimationEndListener onAnimationEndListener){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(onAnimationEndListener != null)
                    onAnimationEndListener.onAnimationEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }
}
