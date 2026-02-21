package co.mobiwise.sample;

import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * Closes the navigation drawer on back. Uses OnBackPressedDispatcher
 * because onBackPressed() is not called for back gestures when
 * targeting Android 16.
 */
final class DrawerBackHandler {

    private DrawerBackHandler() {
    }

    static void install(@NonNull AppCompatActivity activity, @NonNull final DrawerLayout drawer) {
        final OnBackPressedCallback callback = new OnBackPressedCallback(drawer.isDrawerOpen(GravityCompat.START)) {
            @Override
            public void handleOnBackPressed() {
                drawer.closeDrawer(GravityCompat.START);
            }
        };
        activity.getOnBackPressedDispatcher().addCallback(activity, callback);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                callback.setEnabled(true);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                callback.setEnabled(false);
            }
        });
    }
}
