package com.ericmcconkie.watchface.widget;

import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.dinodevs.greatfitwatchface.settings.LoadSettings;
import com.dinodevs.greatfitwatchface.widget.AnalogClockWidget;
import com.ericmcconkie.utils.AppPaint;
import com.ericmcconkie.utils.RotatedBitmap;
import com.ericmcconkie.watchface.settings.Settings;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.analog.SlptAnalogHourView;
import com.ingenic.iwds.slpt.view.analog.SlptAnalogMinuteView;
import com.ingenic.iwds.slpt.view.analog.SlptRotateView;
import com.ingenic.iwds.slpt.view.arc.SlptArcAnglePicView;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;

import java.util.ArrayList;
import java.util.List;

public class MainClock extends AnalogClockWidget {
    final static String TAG = "ericmcconkie.MainClock";
    Settings settings;
    // private Drawable background;
    private Bitmap background;
    private Bitmap hourHand;
    private Bitmap minuteHand;
    private Matrix matrix;
    private  Paint paint = AppPaint.getFacePaint();
    public MainClock(Settings settings) {
        this.settings = settings;
    }
    @Override
    public void init(Service service) {
        super.init(service);
        this.background = Util.decodeImage(service.getResources(), "withings/white/background.png");
        this.hourHand = Util.decodeImage(service.getResources(), "withings/white/hour.png");
        this.minuteHand = Util.decodeImage(service.getResources(), "withings/white/minute.png");

        this.matrix = new Matrix();

    }


    @Override
    public void onDrawAnalog(Canvas canvas, float width, float height, float centerX, float centerY, float secRot, float minRot, float hrRot) {
        canvas.drawBitmap(this.background,0,0,this.paint);
        RotatedBitmap.drawRotatedImage(this.hourHand,hrRot,canvas,matrix,this.paint);
        RotatedBitmap.drawRotatedImage(this.minuteHand,minRot,canvas,matrix,this.paint);


    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        return buildSlptViewComponent(service, false);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service, boolean better_resolution) {

        List<SlptViewComponent> slpt_objects = new ArrayList<>();
        // Draw background image
        SlptPictureView background = new SlptPictureView();
        background.setImagePicture(SimpleFile.readFileFromAssets(service, "withings/white/background.png"));
        slpt_objects.add(background);

        //Draw hour
        SlptAnalogHourView hourView = new SlptAnalogHourView();
        hourView.setImagePicture(SimpleFile.readFileFromAssets(service, "withings/white/hour.png"));
        hourView.alignX = (byte) 2;
        hourView.alignY = (byte) 2;
        hourView.setRect(320,320);
        slpt_objects.add(hourView);

        //Draw min
        SlptAnalogMinuteView minuteView = new SlptAnalogMinuteView();
        minuteView.setImagePicture(SimpleFile.readFileFromAssets(service, "withings/white/minute.png"));
        minuteView.alignX = (byte) 2;
        minuteView.alignY = (byte) 2;
        minuteView.setRect(320,320);
        slpt_objects.add(minuteView);


        return slpt_objects;
    }
}
