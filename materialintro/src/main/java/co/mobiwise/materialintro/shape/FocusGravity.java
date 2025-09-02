package co.mobiwise.materialintro.shape;

/**
 * Created by mertsimsek on 26/01/16.
 */
public enum FocusGravity {

    /**
     * Gravity for focus circle
     * ie. We may want to focus on
     * left of recyclerview item
     */
    LEFT, CENTER, RIGHT,

    /**
     * Same as LEFT for left-to-right layouts and
     * RIGHT for right-to-left layouts.
     */
    START,

    /**
     * Same as RIGHT for left-to-right layouts and
     * LEFT for right-to-left layouts.
     */
    END
}
