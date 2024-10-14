package co.mobiwise.materialintro.shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.graphics.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RectTest {

    private final FakeTarget target = new FakeTarget(100, 200, 300, 260);

    @Test
    public void height_includesPaddingOnBothSides() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        assertEquals(60 + 2 * 10, rect.getHeight());
    }

    @Test
    public void point_isTargetCenter() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        assertEquals(new Point(200, 230), rect.getPoint());
    }

    @Test
    public void touchInsidePaddedBounds_isOnFocus() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        assertTrue(rect.isTouchOnFocus(200, 230));
        assertTrue(rect.isTouchOnFocus(95, 195));
    }

    @Test
    public void touchOutsidePaddedBounds_isNotOnFocus() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        assertFalse(rect.isTouchOnFocus(85, 230));
        assertFalse(rect.isTouchOnFocus(200, 275));
    }
}
