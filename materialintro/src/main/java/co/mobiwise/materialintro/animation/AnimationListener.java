package co.mobiwise.materialintro.animation;

/**
 * Created by mertsimsek on 25/01/16.
 */
public interface AnimationListener {

    /**
     * We need to make MaterialIntroView visible
     * before fade in animation starts
     */
    interface OnAnimationStartListener{
        void onAnimationStart();
    }

    /**
     * We need to make MaterialIntroView invisible
     * after fade out animation ends.
     */
    interface OnAnimationEndListener{
        void onAnimationEnd();
    }

}
