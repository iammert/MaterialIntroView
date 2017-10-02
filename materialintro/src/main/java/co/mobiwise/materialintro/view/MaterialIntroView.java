package co.mobiwise.materialintro.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import co.mobiwise.materialintro.MaterialIntroConfiguration;
import co.mobiwise.materialintro.R;
import co.mobiwise.materialintro.animation.AnimationFactory;
import co.mobiwise.materialintro.animation.AnimationListener;
import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.prefs.PreferencesManager;
import co.mobiwise.materialintro.shape.Circle;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.Rect;
import co.mobiwise.materialintro.shape.Shape;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.target.Target;
import co.mobiwise.materialintro.target.ViewTarget;
import co.mobiwise.materialintro.utils.Constants;
import co.mobiwise.materialintro.utils.Utils;

/**
 * Created by mertsimsek on 22/01/16.
 */
public class MaterialIntroView extends RelativeLayout {

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
     * We don't draw MaterialIntroView
     * until isReady field set to true
     */
    private boolean isReady;

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
     * targetShape focus on target
     * and clear circle to focus
     */
    private Shape targetShape;

    /**
     * Focus Type
     */
    private Focus focusType;

    /**
     * FocusGravity type
     */
    private FocusGravity focusGravity;

    /**
     * Target View
     */
    private Target targetView;

    private Path targetShapePath;

    /**
     * Handler will be used to
     * delay MaterialIntroView
     */
    private Handler handler;

    /**
     * Circle padding
     */
    private int padding;

    /**
     * Layout width/height
     */
    private int width;
    private int height;

    /**
     * Dismiss on touch any position
     */
    private boolean dismissOnTouch;

    /**
     * Info dialog view
     */
    private View infoView;

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

    /**
     * Gesture view will appear center of
     * cleared target area
     */
    private ImageView gestureView;

    private boolean gestureDrawableEnabled;

    private @DrawableRes int gestureDrawableResId;

    private @AnimatorRes int gestureAnimatorResId;

    private Animator gestureAnimator;

    /**
     * Info Dialog Icon
     */
    private ImageView imageViewIcon;

    private int iconDrawableResId;

    /**
     * Image View will be shown if
     * this is true
     */
    private boolean isImageViewEnabled;

    /**
     * Save/Retrieve status of MaterialIntroView
     * If Intro is already learnt then don't show
     * it again.
     */
    private PreferencesManager preferencesManager;

    /**
     * Check using this Id whether user learned
     * or not.
     */
    private String materialIntroViewId;

    /**
     * When layout completed, we set this true
     * Otherwise onGlobalLayoutListener stuck on loop.
     */
    private boolean isLayoutCompleted;

    /**
     * Notify user when MaterialIntroView is dismissed
     */
    private MaterialIntroListener materialIntroListener;

    /**
     * Perform click operation to target
     * if this is true
     */
    private boolean isPerformClick;

    /**
     * Disallow this MaterialIntroView from showing up more than once at a time
     */
    private boolean isIdempotent;

    /**
     * Shape of target
     */
    private ShapeType shapeType;

    /**
     * Use custom shape
     */
    private boolean usesCustomShape = false;

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

    private void init(Context context) {
        setWillNotDraw(false);
        setVisibility(INVISIBLE);

        /**
         * set default values
         */
        maskColor = Constants.DEFAULT_MASK_COLOR;
        delayMillis = Constants.DEFAULT_DELAY_MILLIS;
        fadeAnimationDuration = Constants.DEFAULT_FADE_DURATION;
        padding = Constants.DEFAULT_TARGET_PADDING;
        colorTextViewInfo = Constants.DEFAULT_COLOR_TEXTVIEW_INFO;
        focusType = Focus.ALL;
        focusGravity = FocusGravity.CENTER;
        shapeType = ShapeType.CIRCLE;
        isReady = false;
        isFadeAnimationEnabled = true;
        dismissOnTouch = false;
        isLayoutCompleted = false;
        isInfoEnabled = false;
        isPerformClick = false;
        isImageViewEnabled = true;
        isIdempotent = false;
        gestureDrawableEnabled = true;
        gestureDrawableResId = Constants.DEFAULT_GESTURE_DRAWABLE;
        gestureAnimatorResId = Constants.DEFAULT_GESTURE_ANIMATOR;
        iconDrawableResId = Constants.DEFAULT_ICON_DRAWABLE;

        /**
         * initialize objects
         */
        handler = new Handler();

        preferencesManager = new PreferencesManager(context);

        View layoutInfo = LayoutInflater.from(getContext()).inflate(R.layout.material_intro_card, null);

        infoView = layoutInfo.findViewById(R.id.info_layout);
        textViewInfo = (TextView) layoutInfo.findViewById(R.id.textview_info);
        textViewInfo.setTextColor(colorTextViewInfo);
        imageViewIcon = (ImageView) layoutInfo.findViewById(R.id.imageview_icon);
        imageViewIcon.setImageResource(iconDrawableResId);

        gestureView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.gestureview, null);
        gestureView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                targetShape.reCalculateAll();
                if (targetShape != null && targetShape.getPoint().y != 0 && !isLayoutCompleted) {
                    if (isInfoEnabled)
                        setInfoLayout();
                    if(gestureDrawableEnabled && gestureDrawableResId != 0)
                        setGestureViewLayout();
                    removeOnGlobalLayoutListener(MaterialIntroView.this, this);
                }
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener){
        if (Build.VERSION.SDK_INT < 16) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isReady) return;

        canvas.save();

        if (targetShapePath == null) {
            targetShapePath = new Path();
            targetShapePath.addPath(targetShape.getPath(this.padding));
            targetShapePath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        }
        // TODO add antialiasing somehow, clipPath() does not support it
        canvas.clipPath(targetShapePath);
        canvas.drawColor(maskColor);

        canvas.restore();
    }

    /**
     * Perform click operation when user
     * touches on target circle.
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xT = event.getX();
        float yT = event.getY();

        boolean isTouchOnFocus = targetShape.isTouchOnFocus(xT, yT);
        // TODO extract the flag into MaterialIntroView method ?
        boolean passEventsThroughFocusShape = gestureDrawableResId != Constants.DEFAULT_GESTURE_DRAWABLE;

        if (passEventsThroughFocusShape) {
            if (!isTouchOnFocus)
                return true;

        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    if (isTouchOnFocus && isPerformClick) {
                        targetView.getView().setPressed(true);
                        targetView.getView().invalidate();

                        return true;
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    if (isTouchOnFocus || dismissOnTouch)
                        dismiss();

                    if (isTouchOnFocus && isPerformClick) {
                        targetView.getView().performClick();
                        targetView.getView().setPressed(true);
                        targetView.getView().invalidate();
                        targetView.getView().setPressed(false);
                        targetView.getView().invalidate();
                    }

                    if (isTouchOnFocus) {
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * Shows material view with fade in
     * animation
     *
     * @param activity
     */
    private void show(Activity activity) {

        if (preferencesManager.isDisplayed(materialIntroViewId))
            return;

        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);

        setReady(true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFadeAnimationEnabled)
                    AnimationFactory.animateFadeIn(MaterialIntroView.this, fadeAnimationDuration, new AnimationListener.OnAnimationStartListener() {
                        @Override
                        public void onAnimationStart() {
                            setVisibility(VISIBLE);
                        }
                    });
                else
                    setVisibility(VISIBLE);
            }
        }, delayMillis);

        if(isIdempotent) {
            preferencesManager.setDisplayed(materialIntroViewId);
        }
    }

    /**
     * Dismiss Material Intro View
     */
    public void dismiss() {
        if(!isIdempotent) {
            preferencesManager.setDisplayed(materialIntroViewId);
        }

        AnimationFactory.animateFadeOut(this, fadeAnimationDuration, new AnimationListener.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                setVisibility(GONE);
                removeMaterialView();

                if (materialIntroListener != null)
                    materialIntroListener.onUserClicked(materialIntroViewId);
            }
        });
    }

    private void removeMaterialView(){
        if(getParent() != null )
            ((ViewGroup) getParent()).removeView(this);
    }

    /**
     * locate info card view above/below the
     * circle. If circle's Y coordiante is bigger than
     * Y coordinate of root view, then locate cardview
     * above the circle. Otherwise locate below.
     */
    private void setInfoLayout() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                isLayoutCompleted = true;

                if (infoView.getParent() != null)
                    ((ViewGroup) infoView.getParent()).removeView(infoView);

                RelativeLayout.LayoutParams infoDialogParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT);

                if (targetShape.getPoint().y < height / 2) {
                    ((RelativeLayout) infoView).setGravity(Gravity.TOP);
                    infoDialogParams.setMargins(
                            0,
                            targetShape.getPoint().y + targetShape.getHeight() / 2,
                            0,
                            0);
                } else {
                    ((RelativeLayout) infoView).setGravity(Gravity.BOTTOM);
                    infoDialogParams.setMargins(
                            0,
                            0,
                            0,
                            height - (targetShape.getPoint().y + targetShape.getHeight() / 2) + 2 * targetShape.getHeight() / 2);
                }

                infoView.setLayoutParams(infoDialogParams);
                infoView.postInvalidate();

                addView(infoView);

                imageViewIcon.setVisibility(isImageViewEnabled ? VISIBLE : GONE);
                imageViewIcon.setImageResource(iconDrawableResId);
                if (iconDrawableResId != Constants.DEFAULT_ICON_DRAWABLE) {
                    LayoutParams lp = (LayoutParams) imageViewIcon.getLayoutParams();
                    lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    imageViewIcon.setLayoutParams(lp);
                }


                infoView.setVisibility(VISIBLE);
            }
        });
    }

    private void setGestureViewLayout() {

        handler.post(new Runnable() {
            @Override
            public void run() {

                if (gestureView.getParent() != null)
                    ((ViewGroup) gestureView.getParent()).removeView(gestureView);

                RelativeLayout.LayoutParams gestureViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                gestureViewLayoutParams.height = Utils.dpToPx(Constants.DEFAULT_DOT_SIZE);
                gestureViewLayoutParams.width = Utils.dpToPx(Constants.DEFAULT_DOT_SIZE);
                gestureViewLayoutParams.setMargins(
                        targetShape.getPoint().x - (gestureViewLayoutParams.width / 2),
                        targetShape.getPoint().y - (gestureViewLayoutParams.height / 2),
                        0,
                        0);
                gestureView.setLayoutParams(gestureViewLayoutParams);
                gestureView.postInvalidate();
                addView(gestureView);
                gestureView.setVisibility(VISIBLE);

                if (gestureDrawableResId != 0) {
                    gestureView.setImageResource(gestureDrawableResId);
                }

                if (gestureAnimatorResId != 0) {
                    if (gestureAnimator == null) {
                        gestureAnimator = AnimatorInflater.loadAnimator(getContext(), gestureAnimatorResId);
                    }
                    gestureAnimator.setTarget(gestureView);
                    gestureAnimator.start();
                }
            }
        });
    }

    /**
     * SETTERS
     */

    private void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
    }

    private void setDelay(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    private void enableFadeAnimation(boolean isFadeAnimationEnabled) {
        this.isFadeAnimationEnabled = isFadeAnimationEnabled;
    }

    private void setFadeAnimationDuration(long fadeAnimationDuration) {
        this.fadeAnimationDuration = fadeAnimationDuration;
    }

    private void setShapeType(ShapeType shape) {
        this.shapeType = shape;
    }

    private void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    private void setTarget(Target target) {
        targetView = target;
    }

    private void setFocusType(Focus focusType) {
        this.focusType = focusType;
    }

    private void setShape(Shape shape) {
        this.targetShape = shape;
    }

    private void setPadding(int padding) {
        this.padding = padding;
        this.targetShapePath = null;
    }

    private void setDismissOnTouch(boolean dismissOnTouch) {
        this.dismissOnTouch = dismissOnTouch;
    }

    private void setFocusGravity(FocusGravity focusGravity) {
        this.focusGravity = focusGravity;
    }

    private void setColorTextViewInfo(int colorTextViewInfo) {
        this.colorTextViewInfo = colorTextViewInfo;
        textViewInfo.setTextColor(this.colorTextViewInfo);
    }

    private void setTextViewInfo(CharSequence textViewInfo) {
        this.textViewInfo.setText(textViewInfo);
    }

    private void setTextViewInfoSize(int textViewInfoSize) {
        this.textViewInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, textViewInfoSize);
    }

    private void enableInfoDialog(boolean isInfoEnabled) {
        this.isInfoEnabled = isInfoEnabled;
    }

    private void enableImageViewIcon(boolean isImageViewEnabled){
        this.isImageViewEnabled = isImageViewEnabled;
    }

    public int getIconDrawableResId() {
        return iconDrawableResId;
    }

    public void setIconDrawableResId(int iconDrawableResId) {
        this.iconDrawableResId = iconDrawableResId;
    }

    private void setIdempotent(boolean idempotent){
        this.isIdempotent = idempotent;
    }

    public boolean isGestureDrawableEnabled() {
        return gestureDrawableEnabled;
    }

    public void enableGestureDrawable(boolean enabled) {
        this.gestureDrawableEnabled = enabled;
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

    public void setConfiguration(MaterialIntroConfiguration configuration) {

        if (configuration != null) {
            this.maskColor = configuration.getMaskColor();
            this.delayMillis = configuration.getDelayMillis();
            this.isFadeAnimationEnabled = configuration.isFadeAnimationEnabled();
            this.colorTextViewInfo = configuration.getColorTextViewInfo();
            this.gestureDrawableResId = configuration.getGestureDrawableResId();
            this.gestureAnimatorResId = configuration.getGestureAnimatorResId();
            this.dismissOnTouch = configuration.isDismissOnTouch();
            this.colorTextViewInfo = configuration.getColorTextViewInfo();
            this.focusType = configuration.getFocusType();
            this.focusGravity = configuration.getFocusGravity();
            this.isImageViewEnabled = configuration.isImageViewEnabled();
            this.iconDrawableResId = configuration.getIconDrawableResId();
        }
    }

    private void setUsageId(String materialIntroViewId) {
        this.materialIntroViewId = materialIntroViewId;
    }

    private void setListener(MaterialIntroListener materialIntroListener) {
        this.materialIntroListener = materialIntroListener;
    }

    private void setPerformClick(boolean isPerformClick){
        this.isPerformClick = isPerformClick;
    }

    /**
     * Builder Class
     */
    public static class Builder {

        private MaterialIntroView materialIntroView;

        private Activity activity;

        private Focus focusType = Focus.MINIMUM;

        public Builder(Activity activity) {
            this.activity = activity;
            materialIntroView = new MaterialIntroView(activity);
        }

        public Builder setMaskColor(int maskColor) {
            materialIntroView.setMaskColor(maskColor);
            return this;
        }

        public Builder setDelayMillis(int delayMillis) {
            materialIntroView.setDelay(delayMillis);
            return this;
        }

        public Builder enableFadeAnimation(boolean isFadeAnimationEnabled) {
            materialIntroView.enableFadeAnimation(isFadeAnimationEnabled);
            return this;
        }

        public Builder setFadeAnimationDuration(long fadeAnimationDuration) {
            materialIntroView.setFadeAnimationDuration(fadeAnimationDuration);
            return this;
        }

        public Builder setShape(ShapeType shape) {
            materialIntroView.setShapeType(shape);
            return this;
        }

        public Builder setFocusType(Focus focusType) {
            materialIntroView.setFocusType(focusType);
            return this;
        }

        public Builder setFocusGravity(FocusGravity focusGravity) {
            materialIntroView.setFocusGravity(focusGravity);
            return this;
        }

        public Builder setTarget(View view) {
            materialIntroView.setTarget(new ViewTarget(view));
            return this;
        }

        public Builder setTargetPadding(int padding) {
            materialIntroView.setPadding(padding);
            return this;
        }

        public Builder setTextColor(int textColor) {
            materialIntroView.setColorTextViewInfo(textColor);
            return this;
        }

        public Builder setInfoText(CharSequence infoText) {
            materialIntroView.enableInfoDialog(true);
            materialIntroView.setTextViewInfo(infoText);
            return this;
        }

        public Builder setInfoTextSize(int textSize) {
            materialIntroView.setTextViewInfoSize(textSize);
            return this;
        }

        public Builder dismissOnTouch(boolean dismissOnTouch) {
            materialIntroView.setDismissOnTouch(dismissOnTouch);
            return this;
        }

        public Builder setUsageId(String materialIntroViewId) {
            materialIntroView.setUsageId(materialIntroViewId);
            return this;
        }

        public Builder enableGestureDrawable(boolean enabled) {
            materialIntroView.enableGestureDrawable(enabled);
            return this;
        }

        public Builder setGestureDrawableResId(@DrawableRes int gestureDrawableResId) {
            materialIntroView.setGestureDrawableResId(gestureDrawableResId);
            return this;
        }

        public Builder setGestureAnimatorResId(@AnimatorRes int gestureAnimatorResId) {
            materialIntroView.setGestureAnimatorResId(gestureAnimatorResId);
            return this;
        }

        public Builder enableIcon(boolean isImageViewIconEnabled) {
            materialIntroView.enableImageViewIcon(isImageViewIconEnabled);
            return this;
        }

        public Builder setIconDrawableResId(@DrawableRes int drawableResId) {
            materialIntroView.setIconDrawableResId(drawableResId);
            return this;
        }

        public Builder setIdempotent(boolean idempotent) {
            materialIntroView.setIdempotent(idempotent);
            return this;
        }

        public Builder setConfiguration(MaterialIntroConfiguration configuration) {
            materialIntroView.setConfiguration(configuration);
            return this;
        }

        public Builder setListener(MaterialIntroListener materialIntroListener) {
            materialIntroView.setListener(materialIntroListener);
            return this;
        }

        public Builder setCustomShape(Shape shape) {
            materialIntroView.usesCustomShape = true;
            materialIntroView.setShape(shape);
            return this;
        }

        public Builder performClick(boolean isPerformClick){
            materialIntroView.setPerformClick(isPerformClick);
            return this;
        }

        public MaterialIntroView build() {
            if(materialIntroView.usesCustomShape) {
                return materialIntroView;
            }

            // no custom shape supplied, build our own
            Shape shape;

            if(materialIntroView.shapeType == ShapeType.CIRCLE) {
                shape = new Circle(
                        materialIntroView.targetView,
                        materialIntroView.focusType,
                        materialIntroView.focusGravity,
                        materialIntroView.padding);
            } else {
                shape = new Rect(
                        materialIntroView.targetView,
                        materialIntroView.focusType,
                        materialIntroView.focusGravity,
                        materialIntroView.padding);
            }

            materialIntroView.setShape(shape);
            return materialIntroView;
        }

        public MaterialIntroView show() {
            build().show(activity);
            return materialIntroView;
        }

    }

}
