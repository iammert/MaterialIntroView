package co.mobiwise.materialintro.shape;

import android.graphics.Path;
import android.graphics.Point;

import co.mobiwise.materialintro.target.Target;

/**
 * Created by mertsimsek on 25/01/16.
 */
public class Circle extends Shape {

    private int radius;

    private Point circlePoint;

    public Circle(Target target, Focus focus, FocusGravity focusGravity, int padding) {
        super(target, focus, focusGravity, padding);
        circlePoint = getFocusPoint();
        calculateRadius(padding);
    }

    @Override
    public Path getPath(int padding) {
        if (this.padding != padding || cachedPath == null) {
            this.padding = padding;
            Point center = getFocusPoint();
            cachedPath = new Path();
            cachedPath.addCircle((float) center.x, (float) center.y, getRadius() + padding, Path.Direction.CW);
        }

        return cachedPath;
    }

    @Override
    public void reCalculateAll(){
        calculateRadius(padding);
        circlePoint = getFocusPoint();
    }

    protected void calculateRadius(int padding){
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

    protected void setRadius(int radius) {
        this.radius = radius;
    }

    protected int getRadius(){
        return radius;
    }

    @Override
    public Point getPoint(){
        return circlePoint;
    }

    @Override
    public int getHeight() {
        return 2 * getRadius();
    }

    @Override
    public boolean isTouchOnFocus(double x, double y) {
        int xV = getPoint().x;
        int yV = getPoint().y;

        double dx = Math.pow(x - xV, 2);
        double dy = Math.pow(y - yV, 2);
        return (dx + dy) <= Math.pow(radius, 2);
    }

}
