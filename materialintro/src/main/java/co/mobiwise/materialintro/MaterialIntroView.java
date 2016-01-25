package co.mobiwise.materialintro;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import co.mobiwise.materialintro.shape.Circle;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.target.Target;
import co.mobiwise.materialintro.target.ViewTarget;

/**
 * Created by mertsimsek on 22/01/16.
 */
public class MaterialIntroView extends FrameLayout{

    private int maskColor;

    private long delayMillis;

    private boolean isReady;

    private boolean isFadeAnimationEnabled;

    private long fadeAnimationDuration;

    private Circle circleShape;

    private Focus focusType;

    private Target targetView;

    private Paint eraser;

    private Handler handler;

    private Bitmap bitmap;

    private Canvas canvas;

    private int padding;

    private int width;

    private int height;

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
        padding = Constants.DEFAULT_TARGET_PADDING;
        isReady = false;
        isFadeAnimationEnabled = false;

        /**
         * initialize objects
         */
        handler = new Handler();

        eraser = new Paint();
        eraser.setColor(0xFFFFFFFF);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isReady) return;

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        if(bitmap == null || canvas == null){
            if(bitmap != null) bitmap.recycle();

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(bitmap);
        }

        /**
         * Draw mask
         */
        this.canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        this.canvas.drawColor(maskColor);

        /**
         * Clear focus area
         */
        circleShape.draw(this.canvas, eraser, padding);

        canvas.drawBitmap(bitmap, 0, 0, null);
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

    private void setTarget(Target target){
        targetView = target;
    }

    private void setFocusType(Focus focusType){
        this.focusType = focusType;
    }

    private void setCircle(Circle circleShape){
        this.circleShape = circleShape;
    }

    private void setPadding(int padding){
        this.padding = padding;
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

        private Focus focusType = Focus.MINIMUM;

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

        public Builder setFocusType(Focus focusType){
            materialIntroView.setFocusType(focusType);
            return this;
        }

        public Builder setTarget(View view){
            materialIntroView.setTarget(new ViewTarget(view));
            return this;
        }

        public Builder setTargetPadding(int padding){
            materialIntroView.setPadding(padding);
            return this;
        }

        public MaterialIntroView build(){
            materialIntroView.setCircle(new Circle(materialIntroView.targetView, materialIntroView.focusType));
            return materialIntroView;
        }

        public MaterialIntroView show(){
            build().show(activity);
            return materialIntroView;
        }

    }

}
