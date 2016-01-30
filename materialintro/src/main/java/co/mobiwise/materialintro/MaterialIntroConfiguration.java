package co.mobiwise.materialintro;

import android.util.TypedValue;
import android.widget.TextView;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.utils.Constants;

public class MaterialIntroConfiguration {

    /**
     * Mask color
     */
    private int maskColor;

    /**
     * MaterialIntroView will start
     * showing after delayMillis seconds
     * passed
     */
    private long delayMillis;

    /**
     * Show/Dismiss MaterialIntroView
     * with fade in/out animation if
     * this is enabled.
     */
    private boolean isFadeAnimationEnabled;

    /**
     * Animation duration
     */
    private long fadeAnimationDuration;

    /**
     * Focus Type
     */
    private Focus focusType;

    /**
     * FocusGravity type
     */
    private FocusGravity focusGravity;

    /**
     * Circle padding
     */
    private int padding;

    /**
     * Dismiss on touch any position
     */
    private boolean dismissOnTouch;

    /**
     * Info Dialog Text
     */
    private TextView textViewInfo;

    /**
     * Info dialog text color
     */
    private int colorTextViewInfo;

    /**
     * Info dialog will be shown
     * If this value true
     */
    private boolean isInfoEnabled;


    private boolean isDotViewEnabled;

    public MaterialIntroConfiguration() {
        maskColor = Constants.DEFAULT_MASK_COLOR;
        delayMillis = Constants.DEFAULT_DELAY_MILLIS;
        fadeAnimationDuration = Constants.DEFAULT_FADE_DURATION;
        padding = Constants.DEFAULT_TARGET_PADDING;
        colorTextViewInfo = Constants.DEFAULT_COLOR_TEXTVIEW_INFO;
        focusType = Focus.ALL;
        focusGravity = FocusGravity.CENTER;
        isFadeAnimationEnabled = false;
        dismissOnTouch = false;
        isInfoEnabled = false;
        isDotViewEnabled = false;
    }

    /**
     * GETTERS && SETTERS
     */

    public int getPadding() {
        return padding;
    }

    private void setPadding(int padding) {
        this.padding = padding;
    }

    public FocusGravity getFocusGravity() {

        return focusGravity;
    }

    private void setFocusGravity(FocusGravity focusGravity) {
        this.focusGravity = focusGravity;
    }

    public TextView getTextViewInfo() {
        return textViewInfo;
    }

    private void setTextViewInfo(String textViewInfo) {
        this.textViewInfo.setText(textViewInfo);
    }

    public int getMaskColor() {
        return maskColor;
    }

    private void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    public Focus getFocusType() {
        return focusType;
    }

    private void setFocusType(Focus focusType) {
        this.focusType = focusType;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public boolean isFadeAnimationEnabled() {
        return isFadeAnimationEnabled;
    }

    public long getFadeAnimationDuration() {
        return fadeAnimationDuration;
    }

    public int getColorTextViewInfo() {
        return colorTextViewInfo;
    }

    private void setColorTextViewInfo(int colorTextViewInfo) {
        this.colorTextViewInfo = colorTextViewInfo;
        textViewInfo.setTextColor(this.colorTextViewInfo);
    }

    public boolean isDotViewEnabled() {
        return isDotViewEnabled;
    }

    public boolean isInfoEnabled() {
        return isInfoEnabled;
    }

    public boolean isDismissOnTouch() {
        return dismissOnTouch;
    }

    private void setDismissOnTouch(boolean dismissOnTouch) {
        this.dismissOnTouch = dismissOnTouch;
    }

    private void setDelay(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    private void enableFadeAnimation(boolean isFadeAnimationEnabled) {
        this.isFadeAnimationEnabled = isFadeAnimationEnabled;
    }

    private void setTextViewInfoSize(int textViewInfoSize) {
        this.textViewInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, textViewInfoSize);
    }

    private void enableInfoDialog(boolean isInfoEnabled) {
        this.isInfoEnabled = isInfoEnabled;
    }

    private void enableDotView(boolean isDotViewEnabled) {
        this.isDotViewEnabled = isDotViewEnabled;
    }

    /**
     * Builder Class
     */
    public static class Builder {

        private MaterialIntroConfiguration materialIntroConfiguration;

        private Focus focusType = Focus.MINIMUM;

        public Builder() {
            materialIntroConfiguration = new MaterialIntroConfiguration();
        }

        public Builder setMaskColor(int maskColor) {
            materialIntroConfiguration.setMaskColor(maskColor);
            return this;
        }

        public Builder setDelayMillis(int delayMillis) {
            materialIntroConfiguration.setDelay(delayMillis);
            return this;
        }

        public Builder enableFadeAnimation(boolean isFadeAnimationEnabled) {
            materialIntroConfiguration.enableFadeAnimation(isFadeAnimationEnabled);
            return this;
        }

        public Builder setFocusType(Focus focusType) {
            materialIntroConfiguration.setFocusType(focusType);
            return this;
        }

        public Builder setFocusGravity(FocusGravity focusGravity) {
            materialIntroConfiguration.setFocusGravity(focusGravity);
            return this;
        }

        public Builder setTargetPadding(int padding) {
            materialIntroConfiguration.setPadding(padding);
            return this;
        }

        public Builder setTextColor(int textColor) {
            materialIntroConfiguration.setColorTextViewInfo(textColor);
            return this;
        }

        public Builder setInfoText(String infoText) {
            materialIntroConfiguration.enableInfoDialog(true);
            materialIntroConfiguration.setTextViewInfo(infoText);
            return this;
        }

        public Builder setInfoTextSize(int textSize) {
            materialIntroConfiguration.setTextViewInfoSize(textSize);
            return this;
        }

        public Builder dismissOnTouch(boolean dismissOnTouch) {
            materialIntroConfiguration.setDismissOnTouch(dismissOnTouch);
            return this;
        }

        public Builder enableDotAnimation(boolean isDotAnimationEnabled) {
            materialIntroConfiguration.enableDotView(isDotAnimationEnabled);
            return this;
        }

        public MaterialIntroConfiguration build() {
            return materialIntroConfiguration;
        }
    }
}