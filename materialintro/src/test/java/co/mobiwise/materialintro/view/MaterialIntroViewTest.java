package co.mobiwise.materialintro.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;

import android.app.Activity;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class MaterialIntroViewTest {

    private Activity activity;
    private View target;
    private final List<String> dismissedIds = new ArrayList<>();

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).setup().get();
        target = new View(activity);
        activity.setContentView(target);
    }

    private MaterialIntroView.Builder builder() {
        return new MaterialIntroView.Builder(activity)
                .setTarget(target)
                .enableFadeAnimation(false)
                .setListener(id -> dismissedIds.add(id))
                .setUsageId("intro");
    }

    private static void idle() {
        shadowOf(Looper.getMainLooper()).idle();
    }

    private static void pressBack(View view) {
        view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        view.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }

    @Test
    public void show_attachesToDecorView() {
        MaterialIntroView intro = builder().show();
        idle();

        assertNotNull(intro.getParent());
        assertEquals(View.VISIBLE, intro.getVisibility());
    }

    @Test
    public void dismiss_calledTwice_notifiesListenerOnce() {
        MaterialIntroView intro = builder().show();
        idle();

        intro.dismiss();
        intro.dismiss();
        idle();

        assertEquals(1, dismissedIds.size());
        assertEquals("intro", dismissedIds.get(0));
        assertNull(intro.getParent());
    }

    @Test
    public void backKey_dismissesIntro() {
        MaterialIntroView intro = builder().show();
        idle();

        pressBack(intro);
        idle();

        assertEquals(1, dismissedIds.size());
        assertNull(intro.getParent());
    }

    @Test
    public void backKey_isIgnoredWhenDisabled() {
        MaterialIntroView intro = builder().dismissOnBackPress(false).show();
        idle();

        pressBack(intro);
        idle();

        assertFalse(dismissedIds.contains("intro"));
        assertNotNull(intro.getParent());
    }
}
