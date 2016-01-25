package co.mobiwise.materialintro.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import co.mobiwise.materialintro.target.Target;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class Circle {

    private Target target;

    private Focus focus = Focus.MINIMUM;

    private int radius;

    public Circle(Target target) {
        this(target, Focus.MINIMUM);
    }

    public Circle(Target target,Focus focus) {
        this.focus = focus;
        this.target = target;
    }

    public void draw(Canvas canvas, Paint eraser, int padding){
        calculateRadius(padding);
        Point point = target.getPoint();
        canvas.drawCircle(point.x, point.y, radius, eraser);
    }

    private void calculateRadius(int padding){
        int side;

        if(focus == Focus.MINIMUM)
            side = Math.min(target.getRect().width() / 2, target.getRect().height() / 2);
        else if(focus == Focus.ALL)
            side = Math.max(target.getRect().width() / 2, target.getRect().height() / 2);
        else{
            int minSide = Math.min(target.getRect().width() / 2, target.getRect().height() / 2);
            int maxSide = Math.max(target.getRect().width() / 2, target.getRect().height() / 2);
            side = (minSide + maxSide) / 2;
        }

        radius = side + padding;

    }

}
