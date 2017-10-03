package co.mobiwise.materialintro;


import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.utils.Constants;

public class MaterialIntroConfiguration {

    private int maskColor;

    private long delayMillis;

    private boolean isFadeAnimationEnabled;

    private Focus focusType;

    private FocusGravity focusGravity;

    private int padding;

    private boolean dismissOnTouch;

    private int colorTextViewInfo;

    private @DrawableRes int gestureDrawableResId;

    private @AnimatorRes int gestureAnimatorResId;

    private boolean isImageViewEnabled;

    private @DrawableRes int iconDrawableResId;

    public MaterialIntroConfiguration() {
        maskColor = Constants.DEFAULT_MASK_COLOR;
        delayMillis = Constants.DEFAULT_DELAY_MILLIS;
        padding = Constants.DEFAULT_TARGET_PADDING;
        colorTextViewInfo = Constants.DEFAULT_COLOR_TEXTVIEW_INFO;
        focusType = Focus.ALL;
        focusGravity = FocusGravity.CENTER;
        isFadeAnimationEnabled = false;
        dismissOnTouch = false;
        isImageViewEnabled = true;
        iconDrawableResId = Constants.DEFAULT_ICON_DRAWABLE;
    }

    public int getMaskColor() {
        return maskColor;
    }

    public void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public boolean isFadeAnimationEnabled() {
        return isFadeAnimationEnabled;
    }

    public void setFadeAnimationEnabled(boolean fadeAnimationEnabled) {
        isFadeAnimationEnabled = fadeAnimationEnabled;
    }

    public Focus getFocusType() {
        return focusType;
    }

    public void setFocusType(Focus focusType) {
        this.focusType = focusType;
    }

    public FocusGravity getFocusGravity() {
        return focusGravity;
    }

    public void setFocusGravity(FocusGravity focusGravity) {
        this.focusGravity = focusGravity;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public boolean isDismissOnTouch() {
        return dismissOnTouch;
    }

    public void setDismissOnTouch(boolean dismissOnTouch) {
        this.dismissOnTouch = dismissOnTouch;
    }

    public int getColorTextViewInfo() {
        return colorTextViewInfo;
    }

    public void setColorTextViewInfo(int colorTextViewInfo) {
        this.colorTextViewInfo = colorTextViewInfo;
    }

    public boolean isImageViewEnabled(){
        return isImageViewEnabled;
    }

    public void setIconDrawableResId(int iconDrawableResId) {
        this.iconDrawableResId = iconDrawableResId;
    }

    public int getIconDrawableResId() {
        return this.iconDrawableResId;
    }

    public int getGestureDrawableResId() {
        return gestureDrawableResId;
    }

    public void setGestureDrawableResId(int gestureDrawableResId) {
        this.gestureDrawableResId = gestureDrawableResId;
    }

    public int getGestureAnimatorResId() {
        return gestureAnimatorResId;
    }

    public void setGestureAnimatorResId(int gestureAnimatorResId) {
        this.gestureAnimatorResId = gestureAnimatorResId;
    }
}