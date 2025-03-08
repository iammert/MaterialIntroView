package co.mobiwise.materialintro.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import co.mobiwise.materialintro.target.Target;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class Rect extends Shape {

    RectF adjustedRect;

    /**
     * Corner radius of the focus rectangle in pixels.
     * A negative value keeps the legacy behaviour of
     * using the target padding as radius.
     */
    private float cornerRadius = -1;

    public Rect(Target target) {
        super(target);
        calculateAdjustedRect();
    }

    public Rect(Target target, Focus focus) {
        super(target, focus);
        calculateAdjustedRect();
    }

    public Rect(Target target, Focus focus, FocusGravity focusGravity, int padding) {
        super(target, focus, focusGravity, padding);
        calculateAdjustedRect();
    }

    @Override
    public void draw(Canvas canvas, Paint eraser, int padding) {
        // The target may have moved since the last frame (scrolling,
        // delayed show, collapsing toolbar), keep the focus on it.
        calculateAdjustedRect();
        float radius = getCornerRadius(padding);
        canvas.drawRoundRect(adjustedRect, radius, radius, eraser);
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    float getCornerRadius(int padding) {
        return cornerRadius >= 0 ? cornerRadius : padding;
    }

    private void calculateAdjustedRect() {
        RectF rect = new RectF();
        rect.set(target.getRect());

        rect.left -= padding;
        rect.top -= padding;
        rect.right += padding;
        rect.bottom += padding;

        adjustedRect = rect;
    }

    @Override
    public void reCalculateAll(){
        calculateAdjustedRect();
    }

    @Override
    public Point getPoint(){
        return target.getPoint();
    }

    @Override
    public int getHeight() {
        return (int) adjustedRect.height();
    }

    @Override
    public boolean isTouchOnFocus(double x, double y) {
        return adjustedRect.contains((float) x, (float) y);
    }

}
