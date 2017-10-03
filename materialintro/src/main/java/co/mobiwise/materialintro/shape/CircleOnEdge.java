package co.mobiwise.materialintro.shape;

import android.graphics.Point;

import co.mobiwise.materialintro.R;
import co.mobiwise.materialintro.target.Target;

public class CircleOnEdge extends Circle {

    protected int radius = 0;

    public CircleOnEdge(Target target, FocusGravity focusGravity, int padding) {
        this(target, Focus.MINIMUM, focusGravity, padding, 0);
    }

    public CircleOnEdge(Target target, Focus focus, FocusGravity focusGravity, int padding, int radius) {
        super(target, focus, focusGravity, padding);
        if (radius <= 0) {
            this.radius = this.target.getView().getContext().getResources().getDimensionPixelSize(R.dimen.gesture_circle_radius);
        } else {
            this.radius = radius;
        }
        calculateRadius(padding);
    }

    @Override
    protected Point getFocusPoint() {
        if (focusGravity == FocusGravity.LEFT) {
            int xLeft = target.getRect().left + this.radius / 2;
            return new Point(xLeft, target.getPoint().y);
        } else if (focusGravity == FocusGravity.RIGHT) {
            int xRight = target.getRect().right - this.radius / 2;
            return new Point(xRight, target.getPoint().y);
        } else if (focusGravity == FocusGravity.TOP) {
            int yTop = target.getRect().top + this.radius / 2;
            return new Point(target.getPoint().x, yTop);
        } else if (focusGravity == FocusGravity.BOTTOM) {
            int yBottom = target.getRect().bottom - this.radius / 2;
            return new Point(target.getPoint().x, yBottom);
        } else
            return target.getPoint();
    }

    @Override
    protected void calculateRadius(int padding) {
        setRadius(radius);
    }
}