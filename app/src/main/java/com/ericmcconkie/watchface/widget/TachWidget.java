package com.ericmcconkie.watchface.widget;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.dinodevs.greatfitwatchface.R;
import com.dinodevs.greatfitwatchface.data.Battery;
import com.dinodevs.greatfitwatchface.data.DataType;
import com.dinodevs.greatfitwatchface.widget.AbstractWidget;
import com.ericmcconkie.utils.RotatedBitmap;
import com.ericmcconkie.utils.ValueForDataType;
import com.ericmcconkie.watchface.settings.Settings;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.analog.SlptAnalogMinuteView;
import com.ingenic.iwds.slpt.view.analog.SlptRotatePictureView;
import com.ingenic.iwds.slpt.view.analog.SlptRotateView;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TachWidget extends AbstractWidget {
    static final String TAG = "Tachwidget";
    SharedPreferences sharedPreferences;
    private DataType dataType = DataType.BATTERY;
    private Settings settings;
    private Bitmap tachHand;
    private Matrix matrix;
    Service service;
    private float tachVal = 1f;//starting percent of value (eg: battery remaining)
    float scale = 0.75f;
    private int theta = 0;
    private float angle = 360;
    public TachWidget(Settings settings){
        this.settings = settings;
    }
    @Override
    public void init(Service service) {
        super.init(service);
        this.service = service;

//        String settingsName = service.getApplication().getPackageName() +"_settings";
//        this.sharedPreferences = service.getApplication().getSharedPreferences(settingsName,Context.MODE_PRIVATE);
//        if(sharedPreferences.getInt("tach_meter", -1) == -1) {
//            sharedPreferences.edit().putInt("tach_meter", this.dataType.getDataType()).apply();
//        }
//        int tachDataTypePref = sharedPreferences.getInt("tach_meter",DataType.BATTERY.getDataType());
//        this.dataType = DataType.fromValue(tachDataTypePref);
//        Log.d(TAG, String.format("init: data type for TACH  = %d ",this.dataType.getDataType()));

        this.dataType = this.settings.dataTypeForItem(service.getString(R.string.category_tach));
        this.tachHand = BitmapFactory.decodeResource(service.getResources(),
                R.drawable.tach);
        matrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {

        Bitmap bmpOriginal = this.tachHand;// BitmapFactory.decodeResource(this.service.getResources(), R.drawable.tach);
        // Draw and roate tach
        canvas.save();

        float scaledSize = (float)(bmpOriginal.getWidth() / 2) * scale;
        int x = (int) (width/2 - (int)scaledSize);
        canvas.translate(x,height / 2 + 40  );
        canvas.scale(scale,scale);
        canvas.rotate(angle, bmpOriginal.getWidth()/2, bmpOriginal.getHeight()/2);
        canvas.drawBitmap(bmpOriginal, 0, 0, settings.paint);
        canvas.restore();
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        return buildSlptViewComponent(service,false);
    }

    /**/
    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service, boolean better_resolution) {

        List<SlptViewComponent> list = new ArrayList<>();
        Bitmap curHand = BitmapFactory.decodeResource(service.getResources(), R.drawable.tach);
        int scaleW = (int)((float)curHand.getWidth() * scale);
        Bitmap tach = Bitmap.createScaledBitmap(curHand,scaleW,scaleW,false);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        tach.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();

        SlptRotateView tachView = new SlptRotateView();
        tachView.setImagePicture(bitmapdata);
        tachView.alignX = (byte) 2;
        tachView.alignY = (byte) 2;
        float angle = 360 * tachVal;
        tachView.start_angle = (int)angle ;
        tachView.y = 90;
        tachView.setRect(320 ,320);
        list.add(tachView);

        return list;
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        tachVal = ValueForDataType.getValue(type,value);
        angle = (float)360 * tachVal;
        theta = (int)angle;
    }
    public List<DataType> getDataTypes() {
        return Collections.singletonList(this.dataType);
    }


}
