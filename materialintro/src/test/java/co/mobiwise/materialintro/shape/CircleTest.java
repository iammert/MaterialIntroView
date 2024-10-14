package co.mobiwise.materialintro.shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.graphics.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CircleTest {

    private final FakeTarget target = new FakeTarget(0, 0, 100, 40);

    @Test
    public void minimumFocus_usesShortestSide() {
        Circle circle = new Circle(target, Focus.MINIMUM, FocusGravity.CENTER, 10);
        assertEquals(2 * (20 + 10), circle.getHeight());
    }

    @Test
    public void allFocus_usesLongestSide() {
        Circle circle = new Circle(target, Focus.ALL, FocusGravity.CENTER, 10);
        assertEquals(2 * (50 + 10), circle.getHeight());
    }

    @Test
    public void normalFocus_usesAverageOfSides() {
        Circle circle = new Circle(target, Focus.NORMAL, FocusGravity.CENTER, 10);
        assertEquals(2 * (35 + 10), circle.getHeight());
    }

    @Test
    public void centerGravity_focusesTargetCenter() {
        Circle circle = new Circle(target, Focus.MINIMUM, FocusGravity.CENTER, 0);
        assertEquals(new Point(50, 20), circle.getPoint());
    }

    @Test
    public void leftGravity_focusesLeftHalf() {
        Circle circle = new Circle(target, Focus.MINIMUM, FocusGravity.LEFT, 0);
        assertEquals(new Point(25, 20), circle.getPoint());
    }

    @Test
    public void rightGravity_focusesRightHalf() {
        Circle circle = new Circle(target, Focus.MINIMUM, FocusGravity.RIGHT, 0);
        assertEquals(new Point(75, 20), circle.getPoint());
    }

    @Test
    public void touchInsideRadius_isOnFocus() {
        Circle circle = new Circle(target, Focus.MINIMUM, FocusGravity.CENTER, 10);
        assertTrue(circle.isTouchOnFocus(50, 20));
        assertTrue(circle.isTouchOnFocus(50 + 30, 20));
    }

    @Test
    public void touchOutsideRadius_isNotOnFocus() {
        Circle circle = new Circle(target, Focus.MINIMUM, FocusGravity.CENTER, 10);
        assertFalse(circle.isTouchOnFocus(50 + 31, 20));
        assertFalse(circle.isTouchOnFocus(0, 0));
    }
}
