package co.mobiwise.materialintro.shape;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import co.mobiwise.materialintro.target.Target;

/**
 * Target with fixed bounds so shape math can be tested without a window.
 */
class FakeTarget implements Target {

    private final Rect rect;

    FakeTarget(int left, int top, int right, int bottom) {
        rect = new Rect(left, top, right, bottom);
    }

    void moveTo(int left, int top) {
        rect.offsetTo(left, top);
    }

    @Override
    public Point getPoint() {
        return new Point(rect.centerX(), rect.centerY());
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    @Override
    public View getView() {
        return null;
    }
}
