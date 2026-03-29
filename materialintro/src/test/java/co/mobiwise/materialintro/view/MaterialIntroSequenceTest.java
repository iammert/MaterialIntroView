package co.mobiwise.materialintro.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;

import android.app.Activity;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.mobiwise.materialintro.prefs.PreferencesManager;

@RunWith(RobolectricTestRunner.class)
public class MaterialIntroSequenceTest {

    private Activity activity;
    private View first;
    private View second;
    private final List<String> events = new ArrayList<>();

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(Activity.class).setup().get();
        LinearLayout root = new LinearLayout(activity);
        first = new View(activity);
        second = new View(activity);
        root.addView(first);
        root.addView(second);
        activity.setContentView(root);
    }

    private MaterialIntroView.Builder step(View target, String id) {
        return new MaterialIntroView.Builder(activity)
                .setTarget(target)
                .enableFadeAnimation(false)
                .setListener(events::add)
                .setUsageId(id);
    }

    private static void idle() {
        shadowOf(Looper.getMainLooper()).idle();
    }

    private MaterialIntroView currentIntro() {
        android.view.ViewGroup decor = (android.view.ViewGroup) activity.getWindow().getDecorView();
        for (int i = decor.getChildCount() - 1; i >= 0; i--) {
            if (decor.getChildAt(i) instanceof MaterialIntroView) {
                return (MaterialIntroView) decor.getChildAt(i);
            }
        }
        return null;
    }

    @Test
    public void showsStepsInOrderAndCompletes() {
        new MaterialIntroSequence()
                .add(step(first, "first"))
                .add(step(second, "second"))
                .setOnCompleteListener(() -> events.add("complete"))
                .start();
        idle();

        MaterialIntroView intro = currentIntro();
        assertNotNull(intro);
        intro.dismiss();
        idle();

        MaterialIntroView next = currentIntro();
        assertNotNull(next);
        next.dismiss();
        idle();

        assertNull(currentIntro());
        assertEquals(Arrays.asList("first", "second", "complete"), events);
    }

    @Test
    public void skipsStepsThatWereAlreadyDisplayed() {
        new PreferencesManager(activity).setDisplayed("first");

        new MaterialIntroSequence()
                .add(step(first, "first"))
                .add(step(second, "second"))
                .setOnCompleteListener(() -> events.add("complete"))
                .start();
        idle();

        currentIntro().dismiss();
        idle();

        assertEquals(Arrays.asList("second", "complete"), events);
    }

    @Test
    public void completesImmediatelyWhenEverythingWasDisplayed() {
        PreferencesManager preferences = new PreferencesManager(activity);
        preferences.setDisplayed("first");
        preferences.setDisplayed("second");

        new MaterialIntroSequence()
                .add(step(first, "first"))
                .add(step(second, "second"))
                .setOnCompleteListener(() -> events.add("complete"))
                .start();
        idle();

        assertNull(currentIntro());
        assertEquals(Arrays.asList("complete"), events);
    }
}
