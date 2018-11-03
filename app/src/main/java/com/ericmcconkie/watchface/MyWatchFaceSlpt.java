package com.ericmcconkie.watchface;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.dinodevs.greatfitwatchface.AbstractWatchFaceSlpt;
import com.dinodevs.greatfitwatchface.widget.Widget;
import com.ericmcconkie.watchface.settings.Settings;
import com.ericmcconkie.watchface.widget.MainClock;
import com.ericmcconkie.watchface.widget.TachWidget;
import com.ericmcconkie.watchface.widget.TopWidget;
import com.huami.watch.watchface.util.Util;
import com.huami.watch.watchface.util.WatchFaceConfigTemplate;
import com.ingenic.iwds.slpt.view.core.PreDrawedPictureGroup;
import com.ingenic.iwds.slpt.view.core.SlptAbsoluteLayout;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

public class MyWatchFaceSlpt extends AbstractWatchFaceSlpt {
    Context context;

    @Override
    public void addDrawedPictureGroup26WC(PreDrawedPictureGroup preDrawedPictureGroup) {
        super.addDrawedPictureGroup26WC(preDrawedPictureGroup);
    }

    @Override
    public void addDrawedPictureGroup8C(PreDrawedPictureGroup preDrawedPictureGroup) {
        super.addDrawedPictureGroup8C(preDrawedPictureGroup);
    }

    @Override
    public void clearDrawdPictureGroup() {
        super.clearDrawdPictureGroup();
    }

    @Override
    protected SlptLayout createClockLayout26WC() {
        SlptAbsoluteLayout result = new SlptAbsoluteLayout();
        for (SlptViewComponent component : clock.buildSlptViewComponent(this, true)) {
            result.add(component);
        }
        for (Widget widget : widgets) {
            for (SlptViewComponent component : widget.buildSlptViewComponent(this, true)) {
                result.add(component);
            }
        }

        return result;
    }

    @Override
    protected SlptLayout createClockLayout8C() {
        SlptAbsoluteLayout result = new SlptAbsoluteLayout();
        for (SlptViewComponent component : clock.buildSlptViewComponent(this)) {
            result.add(component);
        }
        for (Widget widget : widgets) {
            for (SlptViewComponent component : widget.buildSlptViewComponent(this)) {
                result.add(component);
            }
        }

        return result;
    }

    @Override
    protected SlptViewComponent get26WCDataWidget(WatchFaceConfigTemplate.DataWidgetConfig dataWidgetConfig) {
        return super.get26WCDataWidget(dataWidgetConfig);
    }

    @Override
    protected SlptViewComponent get8cDataWidget(WatchFaceConfigTemplate.DataWidgetConfig dataWidgetConfig) {
        return super.get8cDataWidget(dataWidgetConfig);
    }

    protected void initWatchFaceConfig() {
        Log.w("DinoDevs-GreatFit", "Initiating watchface");

        //this.getResources().getBoolean(R.bool.seconds)

        Context context = this.getApplicationContext();
        boolean needRefreshSecond = Util.needSlptRefreshSecond(context);
        if (needRefreshSecond) {
            this.setClockPeriodSecond(true);
        }
    }

    @Override
    public boolean isClockPeriodSecond() {
        return super.isClockPeriodSecond();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = this.getApplicationContext();

        // Load settings
        Settings settings = Settings.getInstance();
        settings.setService(this);

        this.clock = new MainClock(settings);
        this.widgets.add(new TachWidget(settings));
        this.widgets.add(new TopWidget(settings));
//        if(settings.isHeartRate()) {
//            this.widgets.add(new HeartRateWidget(settings));
//        }
//        if(settings.isStepsRate()) {
//            this.widgets.add(new StepsWidget(settings));
//        }
//        if(settings.isTodayDistanceRate()) {
//            this.widgets.add(new SportTodayDistanceWidget(settings));
//        }
//        if(settings.isTotalDistanceRate()) {
//            this.widgets.add(new SportTotalDistanceWidget(settings));
//        }
//        if(settings.isCalories()) {
//            this.widgets.add(new CaloriesWidget(settings));
//        }
//        if(settings.isFloor()) {
//            this.widgets.add(new FloorWidget(settings));
//        }
//        if(settings.isBattery()) {
//            this.widgets.add(new BatteryWidget(settings));
//        }
//        if(settings.isWeather()) {
//            this.widgets.add(new WeatherWidget(settings));
//        }
//        if(settings.isGreat()) {
//            this.widgets.add(new GreatWidget(settings));
//        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setClockPeriodSecond(boolean b) {
        super.setClockPeriodSecond(b);
    }

    @Override
    public void setHourFormat(int i) {
        super.setHourFormat(i);
    }
}
