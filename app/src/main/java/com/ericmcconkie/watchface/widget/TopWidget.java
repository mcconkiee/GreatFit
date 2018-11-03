package com.ericmcconkie.watchface.widget;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.data.Battery;
import com.dinodevs.greatfitwatchface.data.DataType;
import com.dinodevs.greatfitwatchface.data.HeartRate;
import com.dinodevs.greatfitwatchface.resource.ResourceManager;
import com.dinodevs.greatfitwatchface.widget.AbstractWidget;
import com.ericmcconkie.utils.RotatedBitmap;
import com.ericmcconkie.utils.StringsHelper;
import com.ericmcconkie.utils.ValueForDataType;
import com.ericmcconkie.watchface.settings.Settings;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.analog.SlptRotateView;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopWidget extends AbstractWidget {
    static final String TAG = "TopWidget";

    SharedPreferences sharedPreferences;
    private DataType dataType = DataType.HEART_RATE;
    private Settings settings;
    private Bitmap image;
    Service service;
    private float widgetVal = 0f;
    private Paint textPaint;
    private Paint iconPaint;
    public TopWidget(Settings settings){
        this.settings = settings;
    }
    @Override
    public void init(Service service) {
        super.init(service);
        this.service = service;
//        String settingsName = service.getApplication().getPackageName() +"_settings";
//        this.sharedPreferences = service.getApplication().getSharedPreferences(settingsName,Context.MODE_PRIVATE);
//        if(sharedPreferences.getInt(TAG, -1) == -1) {
//            sharedPreferences.edit().putInt(TAG, this.dataType.getDataType()).apply();
//        }
//        int tachDataTypePref = sharedPreferences.getInt(TAG,DataType.HEART_RATE.getDataType());
//        this.dataType = DataType.fromValue(tachDataTypePref);
//        Log.d(TAG, String.format("init: data type for TACH  = %d ",this.dataType.getDataType()));
//        image = Util.decodeImage(service.getResources(), "icons/heart_rate.png");


        this.dataType = this.settings.dataTypeForItem(service.getString(R.string.category_top));

        // Font
        this.textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textPaint.setColor(this.service.getResources().getColor(R.color.white));
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONT_FILE));
        this.textPaint.setTextSize(50);
//        this.textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.textPaint.setTextAlign( Paint.Align.CENTER);

        // icon
        this.iconPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.iconPaint.setColor(this.service.getResources().getColor(R.color.white));
        //FIXME - heart vs heartbeat use different variants of fontawesome....set up a way to handle this from settings
        this.iconPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.FONTAWESOMESOLID));
        this.iconPaint.setTextSize(25);
        this.iconPaint.setTextAlign( Paint.Align.CENTER);

    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        //value
        canvas.drawText(String.format("%d",(int)this.widgetVal),centerX,centerY - 88,this.textPaint);

        //icon
        String icon = service.getString(R.string.heartbeat);
        canvas.drawText(String.format("%s --",icon),centerX,centerY - 55,this.iconPaint);

//        Bitmap tempImage = StringsHelper.textAsBitmap(icon,this.iconPaint);
//        this.iconPaint.setColor(service.getResources().getColor(R.color.primary));
//        canvas.drawBitmap(tempImage,centerX,centerY,iconPaint);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        return buildSlptViewComponent(service,false);
    }

    /**/
    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service, boolean better_resolution) {

        List<SlptViewComponent> list = new ArrayList<>();
        String icon = service.getString(R.string.heartbeat);
        Bitmap tempImage = StringsHelper.textAsBitmap(icon,this.iconPaint);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        tempImage.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] bitmapdata = stream.toByteArray();

        SlptPictureView pictView = new SlptPictureView();
        pictView.setImagePicture(bitmapdata);
        pictView.alignX = (byte) 2;
        pictView.alignY = (byte) 2;
        pictView.y = -80;
        pictView.setRect(320,320);
        list.add(pictView);

        return list;
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        widgetVal = ValueForDataType.getValue(type,value);
    }
    public List<DataType> getDataTypes() {
        return Collections.singletonList(this.dataType);
    }
}
