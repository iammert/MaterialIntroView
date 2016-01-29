package co.mobiwise.materialintro.utils;

import android.content.res.Resources;

/**
 * Created by mertsimsek on 29/01/16.
 */
public class Utils {

    public static int pxToDp(int px){
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp){
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
