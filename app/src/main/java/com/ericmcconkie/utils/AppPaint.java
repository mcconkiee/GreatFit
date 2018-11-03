package com.ericmcconkie.utils;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.resource.ResourceManager;

public class AppPaint {
    static public Paint getIconPaint(Context context){
        Paint iconPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        iconPaint.setColor(context.getResources().getColor(R.color.white));
        //FIXME - heart vs heartbeat use different variants of fontawesome....set up a way to handle this from settings
        iconPaint.setTypeface(ResourceManager.getTypeFace(context.getResources(), ResourceManager.Font.FONTAWESOMESOLID));
        iconPaint.setTextSize(24);
        iconPaint.setTextAlign( Paint.Align.LEFT);
        return iconPaint;
    }
    static public  Paint getFacePaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);
        return  paint;
    }
}
