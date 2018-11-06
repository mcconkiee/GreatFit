package com.ericmcconkie.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class StringsHelper {
    static public Bitmap textAsBitmap(String text, Paint paint) {
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }
    static public Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        return textAsBitmap(text,paint);
    }
    static public String widgetValAsString(int widgetVal){
        return String.format("%d",(int)widgetVal);
    }
}
