package co.mobiwise.materialintro.prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PreferencesManagerTest {

    private PreferencesManager preferences;

    @Before
    public void setUp() {
        preferences = new PreferencesManager(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void newId_isNotDisplayed() {
        assertFalse(preferences.isDisplayed("intro"));
    }

    @Test
    public void setDisplayed_marksOnlyThatId() {
        preferences.setDisplayed("intro");

        assertTrue(preferences.isDisplayed("intro"));
        assertFalse(preferences.isDisplayed("other"));
    }

    @Test
    public void reset_clearsSingleId() {
        preferences.setDisplayed("intro");
        preferences.setDisplayed("other");

        preferences.reset("intro");

        assertFalse(preferences.isDisplayed("intro"));
        assertTrue(preferences.isDisplayed("other"));
    }

    @Test
    public void resetAll_clearsEveryId() {
        preferences.setDisplayed("intro");
        preferences.setDisplayed("other");

        preferences.resetAll();

        assertFalse(preferences.isDisplayed("intro"));
        assertFalse(preferences.isDisplayed("other"));
    }

    @Test
    public void nullId_isNeverPersisted() {
        preferences.setDisplayed(null);

        assertFalse(preferences.isDisplayed(null));
        preferences.reset(null);
    }
}
