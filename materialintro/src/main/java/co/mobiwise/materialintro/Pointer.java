package co.mobiwise.materialintro;

import android.graphics.Color;
import android.view.Gravity;

public class Pointer {

    private int gravity = Gravity.CENTER;
    private int color = Color.WHITE;

    public Pointer(int gravity, int color) {
        this.gravity = gravity;
        this.color = color;
    }
}