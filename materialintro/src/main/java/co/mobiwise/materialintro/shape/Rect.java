package co.mobiwise.materialintro.shape;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

import co.mobiwise.materialintro.target.Target;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class Rect extends Shape {

    RectF adjustedRect;

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
    public Path getPath(int padding) {
        if (this.padding != padding || cachedPath == null) {
            this.padding = padding;
            calculateAdjustedRect();
            cachedPath = new Path();
            cachedPath.addRoundRect(adjustedRect, padding, padding, Path.Direction.CW);
        }

        return cachedPath;
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
