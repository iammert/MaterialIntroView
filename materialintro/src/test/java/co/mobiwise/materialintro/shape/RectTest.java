package co.mobiwise.materialintro.shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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

    @Test
    public void draw_followsMovedTarget() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        target.moveTo(400, 600);

        Canvas canvas = new Canvas(Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888));
        rect.draw(canvas, new Paint(), 10);

        assertTrue(rect.isTouchOnFocus(500, 630));
        assertFalse(rect.isTouchOnFocus(200, 230));
    }

    @Test
    public void cornerRadius_defaultsToPadding() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        assertEquals(10f, rect.getCornerRadius(10), 0f);
    }

    @Test
    public void cornerRadius_canBeOverridden() {
        Rect rect = new Rect(target, Focus.ALL, FocusGravity.CENTER, 10);
        rect.setCornerRadius(0f);
        assertEquals(0f, rect.getCornerRadius(10), 0f);
    }
}
