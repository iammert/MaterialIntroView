package co.mobiwise.materialintro;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by mertsimsek on 22/01/16.
 */
public class MaterialIntroView extends FrameLayout{

    private int maskColor;

    private long delayMillis;

    private boolean isReady;

    private boolean isFadeAnimationEnabled;

    private long fadeAnimationDuration;

    private Handler handler;

    public MaterialIntroView(Context context) {
        super(context);
        init(context);
    }

    public MaterialIntroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialIntroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialIntroView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        setWillNotDraw(false);
        setVisibility(INVISIBLE);

        /**
         * set default values
         */
        maskColor = Constants.DEFAULT_MASK_COLOR;
        delayMillis = Constants.DEFAULT_DELAY_MILLIS;
        fadeAnimationDuration = Constants.DEFAULT_FADE_DURATION;
        isReady = false;
        isFadeAnimationEnabled = false;

        /**
         * initialize objects
         */
        handler = new Handler();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isReady) return;

        canvas.drawColor(maskColor);
    }

    private void show(Activity activity){

        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);

        setReady(true);

        handler.postDelayed(() -> {
            if(isFadeAnimationEnabled)
                AnimationFactory.animateFadeIn(this, fadeAnimationDuration, () -> setVisibility(VISIBLE));
            else
                setVisibility(VISIBLE);

        },delayMillis);

    }

    /**
     *
     *
     * SETTERS
     *
     *
     */

    private void setMaskColor(int maskColor){
        this.maskColor = maskColor;
    }

    private void setDelay(int delayMillis){
        this.delayMillis = delayMillis;
    }

    private void enableFadeAnimation(boolean isFadeAnimationEnabled){
        this.isFadeAnimationEnabled = isFadeAnimationEnabled;
    }

    private void setReady(boolean isReady){
        this.isReady = isReady;
    }


    /**
     *
     *
     * Builder Class
     *
     *
     */
    public static class Builder{

        private MaterialIntroView materialIntroView;

        private Activity activity;

        public Builder(Activity activity) {
            this.activity = activity;
            materialIntroView = new MaterialIntroView(activity);
        }

        public Builder setMaskColor(int maskColor){
            materialIntroView.setMaskColor(maskColor);
            return this;
        }

        public Builder setDelayMillis(int delayMillis){
            materialIntroView.setDelay(delayMillis);
            return this;
        }

        public Builder enableFadeAnimation(boolean isFadeAnimationEnabled){
            materialIntroView.enableFadeAnimation(isFadeAnimationEnabled);
            return this;
        }


        public MaterialIntroView show(){
            materialIntroView.show(activity);
            return materialIntroView;
        }

    }

}
