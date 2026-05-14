package co.mobiwise.materialintro.animation;

/**
 * Created by mertsimsek on 30/01/16.
 */
public interface MaterialIntroListener {

    /**
     * Called when the intro is dismissed, either by the user or by
     * {@link co.mobiwise.materialintro.view.MaterialIntroView#dismiss()}.
     */
    void onUserClicked(String materialIntroViewId);

    /**
     * Called when the intro becomes visible, after the configured delay.
     * Useful for analytics or pausing other UI while the intro is shown.
     */
    default void onIntroShown(String materialIntroViewId) {
    }
}
