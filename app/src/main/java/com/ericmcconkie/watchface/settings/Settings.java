package com.ericmcconkie.watchface.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;

public class Settings {
    public Paint paint;

    public Settings() {
        super();

        this.paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);

    }

}
