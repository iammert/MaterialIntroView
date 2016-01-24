package co.mobiwise.materialintro;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by mertsimsek on 22/01/16.
 */
public class MaterialIntroView extends FrameLayout{

    private int maskColor;

    private boolean isReady;

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
        isReady = false;
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
        setVisibility(VISIBLE);
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
            materialIntroView.maskColor = maskColor;
            return this;
        }

        public MaterialIntroView show(){
            materialIntroView.show(activity);
            return materialIntroView;
        }

    }

}
