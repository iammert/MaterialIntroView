package co.mobiwise.materialintro;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class AnimationFactory {

    /**
     * MaterialIntroView will appear on screen with
     * fade in animation. Notifies onAnimationStartListener
     * when fade in animation is about to start.
     *
     * @param view
     * @param duration
     * @param onAnimationStartListener
     */
    public static void animateFadeIn(View view, long duration, AnimationListener.OnAnimationStartListener onAnimationStartListener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (onAnimationStartListener != null)
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
     *
     * @param view
     * @param duration
     * @param onAnimationEndListener
     */
    public static void animateFadeOut(View view, long duration, AnimationListener.OnAnimationEndListener onAnimationEndListener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onAnimationEndListener != null)
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

    public static void performAnimation(View view) {

        final AnimatorSet animatorSet = new AnimatorSet();

        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animatorSet.isRunning()) {
                    view.setScaleX(1f);
                    view.setScaleY(1f);
                    view.setTranslationX(0);
                    animatorSet.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        final ValueAnimator fadeInAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        fadeInAnim.setDuration(800);
        final ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.50f);
        scaleDownX.setDuration(800);
        final ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.50f);
        scaleDownY.setDuration(800);
        final ValueAnimator fadeOutAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);
        fadeOutAnim.setDuration(800);

        animatorSet.play(fadeInAnim);
        animatorSet.play(scaleDownX).with(scaleDownY).after(fadeInAnim);
        animatorSet.addListener(animatorListener);
        animatorSet.start();

    }

}
