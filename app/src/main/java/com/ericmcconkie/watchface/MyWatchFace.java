package com.ericmcconkie.watchface;

import android.content.Context;
import android.content.Intent;

import com.dinodevs.greatfitwatchface.AbstractWatchFace;
import com.ericmcconkie.watchface.settings.Settings;
import com.ericmcconkie.watchface.widget.MainClock;
import com.ericmcconkie.watchface.widget.TachWidget;
import com.ericmcconkie.watchface.widget.TopWidget;
import com.huami.watch.watchface.AbstractSlptClock;

public class MyWatchFace extends AbstractWatchFace {
    Context context;
    @Override
    protected void notifyStatusBarPosition(float v) {
        super.notifyStatusBarPosition(v);
    }

    @Override
    protected void notifyStatusBarPosition(float v, float v1) {
        super.notifyStatusBarPosition(v, v1);
    }

    @Override
    public void onCreate() {
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

        //status_bar(settings.status_bar, settings.status_barLeft, settings.status_barTop);

        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return MyWatchFaceSlpt.class;
    }

    @Override
    public void restartSlpt() {
        super.restartSlpt();
    }
}
